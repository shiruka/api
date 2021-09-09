package io.github.shiruka.api.plugin;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;
import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.event.events.server.ServerExceptionEvent;
import io.github.shiruka.api.exception.ServerPluginEnableDisableException;
import io.github.shiruka.api.scheduler.Scheduler;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents a simple implementation for {@link Plugin.Manager}.
 */
@Log4j2
@Accessors(fluent = true)
@RequiredArgsConstructor
public final class PluginManager implements Plugin.Manager {

  /**
   * the plugin loaders.
   */
  private final Map<Pattern, Plugin.Loader> pluginLoaders = new ConcurrentHashMap<>();

  /**
   * the plugins.
   */
  private final Set<Plugin.Container> plugins = Sets.newConcurrentHashSet();

  /**
   * the plugins.
   */
  private final Map<String, Plugin.Container> pluginsByName = new ConcurrentHashMap<>();

  /**
   * the plugins directory.
   */
  @NotNull
  @Getter
  private final Path pluginsDirectory;

  /**
   * the dependency graph.
   */
  @NotNull
  private MutableGraph<String> dependencyGraph = GraphBuilder.directed()
    .build();

  /**
   * handles plugin exceptions.
   *
   * @param message the message to handle.
   * @param throwable the throwable to handle.
   * @param plugin the plugin to handle.
   */
  private static void handlePluginException(@NotNull final String message, @NotNull final Throwable throwable,
                                            @NotNull final Plugin.Container plugin) {
    Shiruka.logger().fatal(message, throwable);
    new ServerExceptionEvent(new ServerPluginEnableDisableException(message, throwable, plugin)).callEvent();
  }

  @Override
  public void clearPlugins() {
    synchronized (this) {
      this.disablePlugins(true);
      this.plugins.clear();
      this.pluginsByName.clear();
      this.dependencyGraph = GraphBuilder.directed().build();
      this.pluginLoaders.clear();
    }
  }

  @Override
  public synchronized void disablePlugin(@NotNull final Plugin.Container plugin, final boolean closeClassLoaders) {
    if (!plugin.enabled()) {
      return;
    }
    final var fullName = plugin.description().fullName();
    try {
      plugin.loader().disablePlugin(plugin, closeClassLoaders);
    } catch (final Throwable e) {
      PluginManager.handlePluginException("Error occurred (in the plugin loader) while disabling %s (Is it up to date?)".formatted(
        fullName), e, plugin);
    }
    try {
      Scheduler.sync().cancelTasks(plugin);
      Scheduler.async().cancelTasks(plugin);
    } catch (final Throwable e) {
      PluginManager.handlePluginException("Error occurred (in the plugin loader) while cancelling tasks for %s (Is it up to date?)".formatted(
        fullName), e, plugin);
    }
  }

  @Override
  public synchronized void enablePlugin(@NotNull final Plugin.Container plugin) {
    if (plugin.enabled()) {
      return;
    }
    try {
      plugin.loader().enablePlugin(plugin);
    } catch (final Throwable e) {
      PluginManager.handlePluginException("Error occurred (in the plugin loader) while enabling %s (Is it up to date?)".formatted(
        plugin.description().fullName()), e, plugin);
    }
  }

  @Override
  public synchronized boolean isPluginEnabled(@NotNull final Plugin.Container plugin) {
    return this.plugins.contains(plugin) && plugin.enabled();
  }

  @Override
  public boolean isTransitiveDepend(@NotNull final Plugin.Container plugin, @NotNull final Plugin.Container depend) {
    final var name = plugin.description().name();
    if (!this.dependencyGraph.nodes().contains(name)) {
      return false;
    }
    final var reachableNodes = Graphs.reachableNodes(this.dependencyGraph, name);
    final var dependDescription = depend.description();
    return reachableNodes.contains(dependDescription.name()) ||
      dependDescription.provides().stream().anyMatch(reachableNodes::contains);
  }

  @Nullable
  @Override
  public synchronized Plugin.Container loadPlugin(@NotNull final File file) throws InvalidPluginException,
    UnknownDependencyException {
    Plugin.Container result = null;
    for (final var filter : this.pluginLoaders.keySet()) {
      if (filter.matcher(file.getName()).find()) {
        result = this.pluginLoaders.get(filter).loadPlugin(file);
      }
    }
    if (result != null) {
      this.plugins.add(result);
      final var description = result.description();
      this.pluginsByName.put(description.name().toLowerCase(Locale.ROOT), result);
      for (final var provided : description.provides()) {
        this.pluginsByName.putIfAbsent(provided.toLowerCase(Locale.ROOT), result);
      }
    }
    return result;
  }

  @Override
  @NotNull
  public Collection<Plugin.Container> loadPlugins(@NotNull final File folder) {
    Preconditions.checkState(folder.isDirectory(), "The folder must be a directory!");
    final var listFiles = Objects.requireNonNull(folder.listFiles(), "list files");
    final var patterns = this.pluginLoaders.keySet();
    final var plugins = new HashMap<String, File>();
    final var loadedPlugins = new HashSet<String>();
    final var pluginsProvided = new HashMap<String, String>();
    final var dependencies = new HashMap<String, Collection<String>>();
    final var softDependencies = new HashMap<String, Collection<String>>();
    final var result = new ArrayList<Plugin.Container>();
    for (final var file : listFiles) {
      @Nullable Plugin.Loader loader = null;
      for (final var pattern : patterns) {
        if (pattern.matcher(file.getName()).find()) {
          loader = this.pluginLoaders.get(pattern);
        }
      }
      if (loader == null) {
        continue;
      }
      final Plugin.Description description;
      final var filePath = file.getPath();
      final var parentFilePath = file.getParentFile().getPath();
      try {
        description = loader.loadDescription(file);
        final var name = description.name();
        if (name.equalsIgnoreCase("shiruka") ||
          name.equalsIgnoreCase("minecraft") ||
          name.equalsIgnoreCase("mojang")) {
          PluginManager.log.fatal("Couldn't load '{}' in folder '{}': Restricted name!",
            filePath, parentFilePath);
          continue;
        }
        if (name.indexOf(' ') != -1) {
          PluginManager.log.fatal("Couldn't load '{}' in folder '{}': Uses the space-character in its name!",
            filePath, parentFilePath);
          continue;
        }
      } catch (final InvalidDescriptionException e) {
        PluginManager.log.fatal("Couldn't load '{}' in folder '{}'!",
          filePath, parentFilePath, e);
        continue;
      }
      final var pluginFile = plugins.put(description.name(), file);
      if (pluginFile != null) {
        PluginManager.log.fatal("Ambiguous plugin name `{}' for files `{}' and `{}' in `{}'",
          description.name(), filePath, pluginFile.getPath(), parentFilePath);
      }
      final var removedProvided = pluginsProvided.remove(description.name());
      if (removedProvided != null) {
        PluginManager.log.error("Ambiguous plugin name `{}'. It is also provided by `{}'",
          description.name(), removedProvided);
      }
      for (final var provide : description.provides()) {
        final var providedFile = plugins.get(provide);
        if (providedFile != null) {
          PluginManager.log.error("`{} provides `{}' while this is also the name of `{}' in `{}'",
            filePath, provide, providedFile.getPath(), parentFilePath);
        } else {
          final var replacedPlugin = pluginsProvided.put(provide, description.name());
          if (replacedPlugin != null) {
            PluginManager.log.error("`{}' is provided by both `{}' and `{}'",
              provide, description.name(), replacedPlugin);
          }
        }
      }
      final var softDepends = description.softDepends();
      if (!softDepends.isEmpty()) {
        if (softDependencies.containsKey(description.name())) {
          softDependencies.get(description.name()).addAll(softDepends);
        } else {
          softDependencies.put(description.name(), new LinkedList<>(softDepends));
        }
        for (final var softDepend : softDepends) {
          this.dependencyGraph.putEdge(description.name(), softDepend);
        }
      }
      final var depends = description.depends();
      if (!depends.isEmpty()) {
        dependencies.put(description.name(), new LinkedList<>(depends));
        for (final var depend : depends) {
          this.dependencyGraph.putEdge(description.name(), depend);
        }
      }
      final var loadBefore = description.loadBefore();
      if (!loadBefore.isEmpty()) {
        for (final var target : loadBefore) {
          if (softDependencies.containsKey(target)) {
            softDependencies.get(target).add(description.name());
          } else {
            softDependencies.put(target, new LinkedList<>(Set.of(description.name())));
          }
          this.dependencyGraph.putEdge(target, description.name());
        }
      }
    }
    while (!plugins.isEmpty()) {
      var missingDependency = true;
      var iterator = plugins.entrySet().iterator();
      while (iterator.hasNext()) {
        final var next = iterator.next();
        final var plugin = next.getKey();
        final var dependenciesAsPlugin = dependencies.get(plugin);
        if (dependenciesAsPlugin != null) {
          final var dependencyIterator = dependenciesAsPlugin.iterator();
          final var missingHardDependencies = new HashSet<String>(dependenciesAsPlugin.size());
          while (dependencyIterator.hasNext()) {
            final var dependency = dependencyIterator.next();
            if (loadedPlugins.contains(dependency)) {
              dependencyIterator.remove();
            } else if (!plugins.containsKey(dependency) && !pluginsProvided.containsKey(dependency)) {
              missingHardDependencies.add(dependency);
            }
          }
          if (!missingHardDependencies.isEmpty()) {
            missingDependency = false;
            iterator.remove();
            softDependencies.remove(plugin);
            dependencies.remove(plugin);
            PluginManager.log.fatal("Could not load '{}' in folder '{}'",
              next.getValue().getPath(), next.getValue().getParentFile().getPath(),
              new UnknownDependencyException("Unknown/missing dependency plugins: [%s]. Please download and install these plugins to run '%s'.",
                String.join(", ", missingHardDependencies), plugin));
          }
          if (dependencies.containsKey(plugin) && dependencies.get(plugin).isEmpty()) {
            dependencies.remove(plugin);
          }
        }
        if (softDependencies.containsKey(plugin)) {
          softDependencies.get(plugin).removeIf(softDependency ->
            !plugins.containsKey(softDependency) && !pluginsProvided.containsKey(softDependency));
          if (softDependencies.get(plugin).isEmpty()) {
            softDependencies.remove(plugin);
          }
        }
        if (!(dependencies.containsKey(plugin) || softDependencies.containsKey(plugin)) && plugins.containsKey(plugin)) {
          final var file = plugins.get(plugin);
          iterator.remove();
          missingDependency = false;
          try {
            final var loadedPlugin = this.loadPlugin(file);
            if (loadedPlugin != null) {
              result.add(loadedPlugin);
              final var description = loadedPlugin.description();
              loadedPlugins.add(description.name());
              loadedPlugins.addAll(description.provides());
            } else {
              PluginManager.log.fatal("Could not load '{}' in folder '{}'", file.getPath(), file.getParentFile().getPath());
            }
          } catch (final InvalidPluginException e) {
            PluginManager.log.fatal("Could not load '%s' in folder '%s'".formatted(
              file.getPath(), file.getParentFile().getPath()), e);
          }
        }
      }
      if (missingDependency) {
        iterator = plugins.entrySet().iterator();
        while (iterator.hasNext()) {
          final var entry = iterator.next();
          final var plugin = entry.getKey();
          if (!dependencies.containsKey(plugin)) {
            softDependencies.remove(plugin);
            missingDependency = false;
            final var file = entry.getValue();
            iterator.remove();
            final var path = file.getPath();
            final var parentPath = file.getParentFile().getPath();
            try {
              final var loadedPlugin = this.loadPlugin(file);
              if (loadedPlugin != null) {
                result.add(loadedPlugin);
                final var description = loadedPlugin.description();
                loadedPlugins.add(description.name());
                loadedPlugins.addAll(description.provides());
              } else {
                PluginManager.log.fatal("Could not load '{}' in folder '{}'",
                  path, parentPath);
              }
              break;
            } catch (final InvalidPluginException ex) {
              PluginManager.log.fatal("Could not load '%s' in folder '%s'".formatted(
                path, parentPath), ex);
            }
          }
        }
        if (missingDependency) {
          softDependencies.clear();
          dependencies.clear();
          final var failedPluginIterator = plugins.values().iterator();
          while (failedPluginIterator.hasNext()) {
            final var file = failedPluginIterator.next();
            failedPluginIterator.remove();
            PluginManager.log.fatal("Could not load '{}' in folder '{}': Circular dependency detected",
              file.getPath(), file.getParentFile().getPath());
          }
        }
      }
    }
    return result;
  }

  @NotNull
  @Override
  public Map<Pattern, Plugin.Loader> loaders() {
    return Collections.unmodifiableMap(this.pluginLoaders);
  }

  @NotNull
  @Override
  public synchronized Optional<Plugin.Container> plugin(@NotNull final String plugin) {
    return Optional.ofNullable(this.pluginsByName.get(plugin.replace(' ', '_').toLowerCase(Locale.ROOT)));
  }

  @NotNull
  @Override
  public synchronized Collection<Plugin.Container> plugins() {
    return Collections.unmodifiableSet(this.plugins);
  }

  @Override
  public void registerLoader(@NotNull final Pattern pattern, @NotNull final Plugin.Loader loader) {
    this.pluginLoaders.put(pattern, loader);
  }
}
