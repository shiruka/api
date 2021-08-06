package io.github.shiruka.api.plugin.java;

import io.github.shiruka.api.Shiruka;
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

  @NotNull
  @Override
  public Plugin.Description loadDescription(@NotNull final File file) throws InvalidDescriptionException {
    try (final var jar = new JarFile(file)) {
      final var entry = jar.getJarEntry("plugin.yml");
      if (entry == null) {
        throw new InvalidDescriptionException(new FileNotFoundException("Jar does not contain plugin.yml file!"));
      }
      return Plugin.Description.of(jar.getInputStream(entry));
    } catch (final IOException e) {
      throw new InvalidDescriptionException(e);
    }
  }

  @NotNull
  @Override
  public Plugin.Container loadPlugin(@NotNull final File file) throws InvalidPluginException,
    UnknownDependencyException {
    if (Files.notExists(file.toPath())) {
      throw new InvalidPluginException(new FileNotFoundException("%s does not exist".formatted(file.getPath())));
    }
    final Plugin.Description description;
    try {
      description = this.loadDescription(file);
    } catch (final InvalidDescriptionException ex) {
      throw new InvalidPluginException(ex);
    }
    final var parentFile = Shiruka.getPluginManager().getPluginsDirectory();
    final var dataFolder = new File(parentFile, description.name());
    if (Files.exists(dataFolder.toPath()) && !Files.isDirectory(dataFolder.toPath())) {
      throw new InvalidPluginException("Projected data folder: `%s' for %s (%s) exists and is not a directory",
        dataFolder, description.getFullName(), file);
    }
    final var missingHardDependencies = new HashSet<>(description.depends().size());
    for (final var pluginName : description.depends()) {
      if (Shiruka.getPluginManager().getPlugin(pluginName).isEmpty()) {
        missingHardDependencies.add(pluginName);
      }
    }
    if (!missingHardDependencies.isEmpty()) {
      throw new UnknownDependencyException("Unknown/missing dependency plugins: [%s]. Please download and install these plugins to run '%s'.",
        missingHardDependencies, description.getFullName());
    }
    final PluginClassLoader loader;
    try {
      loader = new PluginClassLoader(this, this.getClass().getClassLoader(), description, dataFolder, file);
    } catch (final InvalidPluginException ex) {
      throw ex;
    } catch (final Throwable ex) {
      throw new InvalidPluginException(ex);
    }
    this.loaders.add(loader);
    return loader.getPluginContainer();
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
  Class<?> getClassByName(@NotNull final String name, final boolean resolve, @NotNull final Plugin.Container container,
                          @NotNull final PluginClassLoader requester) {
    final ReentrantReadWriteLock lock;
    synchronized (this.classLoadLock) {
      lock = this.classLoadLock.computeIfAbsent(name, x -> new ReentrantReadWriteLock());
      this.classLoadLockCount.compute(name, (x, prev) -> prev != null ? prev + 1 : 1);
    }
    lock.writeLock().lock();
    try {
      final var pluginManager = Shiruka.getPluginManager();
      try {
        return requester.loadClass0(
          name,
          false,
          pluginManager.isTransitiveDepend(container, requester.getPluginContainer()));
      } catch (final ClassNotFoundException ignored) {
      }
      for (final var loader : this.loaders) {
        try {
          return loader.loadClass0(
            name,
            resolve,
            pluginManager.isTransitiveDepend(container, loader.getPluginContainer()));
        } catch (final ClassNotFoundException ignored) {
        }
      }
    } finally {
      synchronized (this.classLoadLock) {
        lock.writeLock().unlock();
        if (this.classLoadLockCount.get(name) == 1) {
          this.classLoadLock.remove(name);
          this.classLoadLockCount.remove(name);
        } else {
          this.classLoadLockCount.compute(name, (x, prev) -> prev == null ? null : prev - 1);
        }
      }
    }
    return null;
  }
}
