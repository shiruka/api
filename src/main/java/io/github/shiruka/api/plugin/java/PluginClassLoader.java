package io.github.shiruka.api.plugin.java;

import com.google.inject.Guice;
import io.github.shiruka.api.plugin.InvalidPluginException;
import io.github.shiruka.api.plugin.Plugin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
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
    try {
      mainClass = Class.forName(description.main(), true, this);
    } catch (final ClassNotFoundException ex) {
      throw new InvalidPluginException("Cannot find main class `%s'", ex, description.main());
    }
    final Class<? extends Plugin> pluginClass;
    try {
      pluginClass = mainClass.asSubclass(Plugin.class);
    } catch (final ClassCastException ex) {
      throw new InvalidPluginException("Main class `%s' does not implement Plugin", ex,
        description.main());
    }
    final var module = new JavaPluginModule(dataFolder, description, this.logger, pluginFile, this);
    final var plugin = Guice.createInjector(module)
      .getInstance(pluginClass);
    this.pluginContainer = new Plugin.Container(plugin, description, this);
  }
}
