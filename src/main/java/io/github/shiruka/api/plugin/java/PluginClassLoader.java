package io.github.shiruka.api.plugin.java;

import com.google.inject.Guice;
import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.plugin.InvalidPluginException;
import io.github.shiruka.api.plugin.Plugin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipFile;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents plugin class loaders.
 */
public final class PluginClassLoader extends URLClassLoader {

  /**
   * the data folder.
   */
  @NotNull
  private final File dataFolder;

  /**
   * the file.
   */
  @NotNull
  private final File file;

  /**
   * the jar.
   */
  @NotNull
  private final JarFile jar;

  /**
   * the loader.
   */
  @NotNull
  private final JavaPluginLoader loader;

  /**
   * the logger.
   */
  @NotNull
  private final Logger logger;

  /**
   * the manifest.
   */
  @NotNull
  private final Manifest manifest;

  /**
   * the plugin container.
   */
  @NotNull
  @Getter
  private final Plugin.Container pluginContainer;

  /**
   * the seen illegal access.
   */
  private final Set<String> seenIllegalAccess = Collections.newSetFromMap(new ConcurrentHashMap<>());

  /**
   * the url.
   */
  @NotNull
  private final URL url;

  /**
   * ctor.
   *
   * @param loader the loader.
   * @param parent the parent.
   * @param description the description.
   * @param dataFolder the data folder.
   * @param pluginFile the plugin file.
   *
   * @throws InvalidPluginException if something goes wrong when creating the plugin's instance.
   * @throws IOException if an I/O error has occurred.
   */
  PluginClassLoader(@NotNull final JavaPluginLoader loader, @Nullable final ClassLoader parent,
                    @NotNull final Plugin.Description description, @NotNull final File dataFolder,
                    @NotNull final File pluginFile) throws InvalidPluginException, IOException {
    super(pluginFile.getName(), new URL[]{pluginFile.toURI().toURL()}, parent);
    this.loader = loader;
    this.dataFolder = dataFolder;
    this.file = pluginFile;
    this.jar = new JarFile(pluginFile, true, ZipFile.OPEN_READ, JarFile.runtimeVersion());
    this.manifest = this.jar.getManifest();
    this.url = pluginFile.toURI().toURL();
    this.logger = LogManager.getLogger(description.prefix());
    final Class<?> mainClass;
    final var mainClassPath = description.main();
    try {
      mainClass = Class.forName(mainClassPath, true, this);
    } catch (final ClassNotFoundException ex) {
      throw new InvalidPluginException("Cannot find main class `%s'", ex, mainClassPath);
    }
    final Class<? extends Plugin> pluginClass;
    try {
      pluginClass = mainClass.asSubclass(Plugin.class);
    } catch (final ClassCastException ex) {
      throw new InvalidPluginException("Main class `%s' does not implement Plugin", ex,
        mainClassPath);
    }
    final var module = new JavaPluginModule(dataFolder, description, this.logger, pluginFile, this);
    final var plugin = Guice.createInjector(module)
      .getInstance(pluginClass);
    this.pluginContainer = new Plugin.Container(plugin, description, this.logger, this);
  }

  @Override
  public void close() throws IOException {
    try {
      super.close();
    } finally {
      this.jar.close();
    }
  }

  @Override
  public void addURL(@NotNull final URL url) {
    super.addURL(url);
  }

  @Override
  protected Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
    return this.loadClass0(name, resolve, true);
  }

  @Override
  public URL getResource(@NotNull final String name) {
    return this.findResource(name);
  }

  @Override
  public Enumeration<URL> getResources(@NotNull final String name) throws IOException {
    return this.findResources(name);
  }

  /**
   * loads the class.
   *
   * @param name the name to load.
   * @param resolve the resolve to load.
   * @param checkGlobal the check global to load.
   *
   * @return loaded class.
   *
   * @throws ClassNotFoundException if the class not found.
   */
  @NotNull
  Class<?> loadClass0(@NotNull final String name, final boolean resolve, final boolean checkGlobal)
    throws ClassNotFoundException {
    try {
      return super.loadClass(name, resolve);
    } catch (final ClassNotFoundException ignored) {
    }
    if (!checkGlobal) {
      throw new ClassNotFoundException(name);
    }
    final var result = this.loader.getClassByName(name, resolve, this.pluginContainer, this);
    if (result == null) {
      throw new ClassNotFoundException(name);
    }
    final var classLoader = result.getClassLoader();
    if (classLoader instanceof PluginClassLoader pluginClassLoader) {
      final var provider = pluginClassLoader.getPluginContainer();
      if (provider != this.pluginContainer
        && !this.seenIllegalAccess.contains(provider.description().name())
        && !Shiruka.getPluginManager().isTransitiveDepend(this.pluginContainer, provider)) {
        this.seenIllegalAccess.add(provider.description().name());
        this.pluginContainer.logger().warn("Loaded class {} from {} which is not a depend, soft-depend or load-before of this plugin.",
          name, provider.description().getFullName());
      }
    }
    return result;
  }
}
