package io.github.shiruka.api.plugin;

import com.google.common.base.Preconditions;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents a simple implementation for {@link Plugin.Manager}.
 */
@Log4j2
public final class PluginManager implements Plugin.Manager {

  /**
   * the dependency graph.
   */
  private final MutableGraph<String> dependencyGraph = GraphBuilder.directed()
    .build();

  /**
   * the plugin loaders.
   */
  private final Map<Pattern, Plugin.Loader> pluginLoaders = new HashMap<>();

  /**
   * the plugins.
   */
  private final List<Plugin> plugins = new ArrayList<>();

  @NotNull
  @Override
  public Map<Pattern, Plugin.Loader> getLoaders() {
    return Collections.unmodifiableMap(this.pluginLoaders);
  }

  @Override
  @NotNull
  public Collection<Plugin> loadPlugins(@NotNull final File folder) {
    Preconditions.checkState(folder.isDirectory(), "The folder must be a directory!");
    final var listFiles = Objects.requireNonNull(folder.listFiles(), "list files");
    final var patterns = this.pluginLoaders.keySet();
    final var plugins = new HashMap<String, File>();
    final var loadedPlugins = new HashSet<String>();
    final var pluginsProvided = new HashMap<String, String>();
    final var dependencies = new HashMap<String, Collection<String>>();
    final var softDependencies = new HashMap<String, Collection<String>>();
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
      try {
        description = loader.loadDescription(file);
        final var name = description.name();
        if (name.equalsIgnoreCase("shiruka") ||
          name.equalsIgnoreCase("minecraft") ||
          name.equalsIgnoreCase("mojang")) {
          PluginManager.log.fatal("Couldn't load '{}' in folder '{}': Restricted name!",
            file.getPath(), file.getParentFile().getPath());
          continue;
        }
        if (name.indexOf(' ') != -1) {
          PluginManager.log.fatal("Couldn't load '{}' in folder '{}': uses the space-character in its name!",
            file.getPath(), file.getParentFile().getPath());
          continue;
        }
      } catch (final InvalidDescriptionException e) {
        PluginManager.log.fatal("Couldn't load '{}' in folder '{}'!",
          file.getPath(), file.getParentFile().getPath(), e);
        continue;
      }
      final var pluginFile = plugins.put(description.name(), file);
      if (pluginFile != null) {
        PluginManager.log.fatal("Ambiguous plugin name `{}' for files `{}' and `{}' in `{}'",
          description.name(), file.getPath(), pluginFile.getPath(), file.getParentFile().getPath());
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
            file.getPath(), provide, providedFile.getPath(), file.getParentFile().getPath());
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
      final var iterator = plugins.entrySet().iterator();
      while (iterator.hasNext()) {
        final var next = iterator.next();
        final var plugin = next.getKey();
        final var dependenciesAsPlugin = dependencies.get(plugin);
        if (dependenciesAsPlugin != null) {
          final var dependencyIterator = dependenciesAsPlugin.iterator();
          final var missingHardDependencies = new HashSet<>(dependenciesAsPlugin.size());
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
          }
        }
      }
    }
    return Collections.emptySet();
  }

  @Override
  public void registerLoader(@NotNull final Pattern pattern, @NotNull final Plugin.Loader loader) {
    this.pluginLoaders.put(pattern, loader);
  }
}
