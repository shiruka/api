/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.plugin.java;

import io.github.shiruka.api.plugin.InvalidPluginException;
import io.github.shiruka.api.plugin.PluginDescriptionFile;
import io.github.shiruka.api.text.TranslatedText;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents java plugin class loader.
 */
public final class JavaPluginClassLoader extends URLClassLoader {

  /**
   * the data folder.
   */
  @NotNull
  private final File dataFolder;

  /**
   * the description.
   */
  @NotNull
  private final PluginDescriptionFile description;

  /**
   * the file.
   */
  @NotNull
  private final File file;

  /**
   * the jar file.
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
   * the url.
   */
  @NotNull
  private final URL url;

  static {
    ClassLoader.registerAsParallelCapable();
  }

  /**
   * ctor.
   *
   * @param dataFolder the data folder.
   * @param description the description.
   * @param file the file.
   * @param loader the loader.
   * @param parent the parent.
   *
   * @throws IOException if something went wrong when reading the file.
   * @throws InvalidPluginException if something went wrong when reading the plugin's classes.
   */
  public JavaPluginClassLoader(@NotNull final File dataFolder, @NotNull final PluginDescriptionFile description,
                               @NotNull final File file, @NotNull final JavaPluginLoader loader,
                               @NotNull final ClassLoader parent) throws IOException, InvalidPluginException {
    super(new URL[]{file.toURI().toURL()}, parent);
    this.dataFolder = dataFolder;
    this.description = description;
    this.file = file;
    this.loader = loader;
    @NotNull JarFile jarFile;
    try {
      jarFile = new JarFile(file, true, ZipFile.OPEN_READ, JarFile.runtimeVersion());
    } catch (final Exception ignored) {
      jarFile = new JarFile(file);
    }
    this.jar = jarFile;
    this.manifest = this.jar.getManifest();
    this.url = file.toURI().toURL();
    this.logger = LogManager.getLogger(description.getName());
    try {
      final Class<?> jarClass;
      try {
        jarClass = Class.forName(description.getMain(), true, this);
      } catch (final ClassNotFoundException ex) {
        throw new InvalidPluginException(
          TranslatedText.get("shiruka.plugin.java.class_loader.ctor.main_class_not_found",
            description.getName(), description.getMain()).asString(), ex);
      }
      final Class<? extends JavaPlugin> pluginClass;
      try {
        pluginClass = jarClass.asSubclass(JavaPlugin.class);
      } catch (final ClassCastException ex) {
        throw new InvalidPluginException(
          TranslatedText.get("shiruka.plugin.java.class_loader.ctor.does_not_extend_java_plugin",
            description.getName(), description.getMain()).asString(), ex);
      }
      plugin = pluginClass.newInstance();
    } catch (final IllegalAccessException ex) {
      throw new InvalidPluginException("No public constructor", ex);
    } catch (final InstantiationException ex) {
      throw new InvalidPluginException("Abnormal plugin type", ex);
    }
  }
}
