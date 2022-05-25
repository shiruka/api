package io.github.shiruka.api.plugin.java;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.event.plugin.PluginDisableEvent;
import io.github.shiruka.api.event.plugin.PluginEnableEvent;
import io.github.shiruka.api.plugin.InvalidDescriptionException;
import io.github.shiruka.api.plugin.InvalidPluginException;
import io.github.shiruka.api.plugin.Plugin;
import io.github.shiruka.api.plugin.UnknownDependencyException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.jar.JarFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents java plugin loaders which is implementation of {@link Plugin.Loader}.
 */
public final class JavaPluginLoader implements Plugin.Loader {

  /**
   * the class load lock.
   */
  private final Map<String, ReentrantReadWriteLock> classLoadLock = new HashMap<>();

  /**
   * the class load lock count.
   */
  private final Map<String, Integer> classLoadLockCount = new HashMap<>();

  /**
   * the loaders.
   */
  private final List<PluginClassLoader> loaders = new CopyOnWriteArrayList<>();

  @Override
  public void disablePlugin(
    @NotNull final Plugin.Container plugin,
    final boolean closeClassLoaders
  ) {
    if (!plugin.enabled()) {
      return;
    }
    final var fullName = plugin.description().fullName();
    final var message = "Disabling %s".formatted(fullName);
    plugin.logger().info(message);
    new PluginDisableEvent(plugin).callEvent();
    try {
      plugin.enabled(false);
    } catch (final Throwable e) {
      Shiruka
        .logger()
        .fatal(
          "Error occurred while disabling %s (Is it up to date?)".formatted(
              fullName
            ),
          e
        );
    }
    if (plugin.classLoader() instanceof PluginClassLoader loader) {
      this.loaders.remove(loader);
      try {
        loader.close();
      } catch (final IOException ignored) {}
      try {
        if (closeClassLoaders) {
          loader.close();
        }
      } catch (final IOException e) {
        Shiruka
          .logger()
          .warn("Error closing the Plugin Class Loader for {}", fullName);
        e.printStackTrace();
      }
    }
  }

  @Override
  public void enablePlugin(@NotNull final Plugin.Container plugin) {
    if (plugin.enabled()) {
      return;
    }
    final var fullName = plugin.description().fullName();
    final var enableMsg = "Enabling " + fullName;
    plugin.logger().info(enableMsg);
    final var pluginLoader = (PluginClassLoader) plugin.classLoader();
    if (!this.loaders.contains(pluginLoader)) {
      this.loaders.add(pluginLoader);
      Shiruka
        .logger()
        .warn(
          "Enabled plugin with unregistered PluginClassLoader {}",
          fullName
        );
    }
    try {
      plugin.enabled(true);
    } catch (final Throwable e) {
      Shiruka
        .logger()
        .fatal(
          "Error occurred while enabling %s (Is it up to date?)".formatted(
              fullName
            ),
          e
        );
      Shiruka.pluginManager().disablePlugin(plugin, true);
      return;
    }
    new PluginEnableEvent(plugin).callEvent();
  }

  @NotNull
  @Override
  public Plugin.Description loadDescription(@NotNull final File file)
    throws InvalidDescriptionException {
    try (final var jar = new JarFile(file)) {
      final var entry = jar.getJarEntry("plugin.yml");
      if (entry == null) {
        throw new InvalidDescriptionException(
          new FileNotFoundException("Jar does not contain plugin.yml file!")
        );
      }
      return Plugin.Description.of(jar.getInputStream(entry));
    } catch (final IOException e) {
      throw new InvalidDescriptionException(e);
    }
  }

  @NotNull
  @Override
  public Plugin.Container loadPlugin(@NotNull final File file)
    throws InvalidPluginException, UnknownDependencyException {
    if (Files.notExists(file.toPath())) {
      throw new InvalidPluginException(
        new FileNotFoundException("%s does not exist".formatted(file.getPath()))
      );
    }
    final Plugin.Description description;
    try {
      description = this.loadDescription(file);
    } catch (final InvalidDescriptionException ex) {
      throw new InvalidPluginException(ex);
    }
    final var dataFolder = Shiruka
      .pluginManager()
      .pluginsDirectory()
      .resolve(description.name());
    if (Files.exists(dataFolder) && !Files.isDirectory(dataFolder)) {
      throw new InvalidPluginException(
        "Projected data folder: `%s' for %s (%s) exists and is not a directory",
        dataFolder,
        description.fullName(),
        file
      );
    }
    final var missingHardDependencies = new HashSet<>(
      description.depends().size()
    );
    for (final var pluginName : description.depends()) {
      if (Shiruka.pluginManager().plugin(pluginName).isEmpty()) {
        missingHardDependencies.add(pluginName);
      }
    }
    if (!missingHardDependencies.isEmpty()) {
      throw new UnknownDependencyException(
        "Unknown/missing dependency plugins: [%s]. Please download and install these plugins to run '%s'.",
        missingHardDependencies,
        description.fullName()
      );
    }
    final PluginClassLoader loader;
    try {
      loader =
        new PluginClassLoader(
          this,
          this.getClass().getClassLoader(),
          description,
          dataFolder,
          file
        );
    } catch (final InvalidPluginException ex) {
      throw ex;
    } catch (final Throwable ex) {
      throw new InvalidPluginException(ex);
    }
    this.loaders.add(loader);
    return loader.pluginContainer();
  }

  /**
   * gets the class by name.
   *
   * @param name the name to get.
   * @param resolve the resolve to get.
   * @param container the container to get.
   * @param requester the requester to get.
   *
   * @return class by name.
   */
  @Nullable
  Class<?> getClassByName(
    @NotNull final String name,
    final boolean resolve,
    @NotNull final Plugin.Container container,
    @NotNull final PluginClassLoader requester
  ) {
    final ReentrantReadWriteLock lock;
    synchronized (this.classLoadLock) {
      lock =
        this.classLoadLock.computeIfAbsent(
            name,
            x -> new ReentrantReadWriteLock()
          );
      this.classLoadLockCount.compute(
          name,
          (x, prev) -> prev != null ? prev + 1 : 1
        );
    }
    lock.writeLock().lock();
    try {
      final var pluginManager = Shiruka.pluginManager();
      try {
        return requester.loadClass0(
          name,
          false,
          pluginManager.isTransitiveDepend(
            container,
            requester.pluginContainer()
          )
        );
      } catch (final ClassNotFoundException ignored) {}
      for (final var loader : this.loaders) {
        try {
          return loader.loadClass0(
            name,
            resolve,
            pluginManager.isTransitiveDepend(
              container,
              loader.pluginContainer()
            )
          );
        } catch (final ClassNotFoundException ignored) {}
      }
    } finally {
      synchronized (this.classLoadLock) {
        lock.writeLock().unlock();
        if (this.classLoadLockCount.get(name) == 1) {
          this.classLoadLock.remove(name);
          this.classLoadLockCount.remove(name);
        } else {
          this.classLoadLockCount.compute(
              name,
              (x, prev) -> prev == null ? null : prev - 1
            );
        }
      }
    }
    return null;
  }
}
