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

package net.shiruka.api.plugin.java;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipFile;
import net.shiruka.api.Shiruka;
import net.shiruka.api.plugin.InvalidPluginException;
import net.shiruka.api.plugin.PluginDescriptionFile;
import net.shiruka.api.plugin.SimplePluginManager;
import net.shiruka.api.text.TranslatedText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.utils.Validate;

/**
 * a class that represents java plugin class loader.
 */
public final class JavaPluginClassLoader extends URLClassLoader {

  /**
   * the plugin does not extend java plugin.
   */
  private static final String PLUGIN_DOES_NOT_EXTEND_JAVA_PLUGIN = "shiruka.plugin.does_not_extend_java_plugin";

  /**
   * the plugin amin class not found.
   */
  private static final String PLUGIN_MAIN_CLASS_NOT_FOUND = "shiruka.plugin.main_class_not_found";

  /**
   * the classes.
   */
  private final Map<String, Class<?>> classes = new ConcurrentHashMap<>();

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
  @Nullable
  private final Manifest manifest;

  /**
   * the plugin.
   */
  @Nullable
  private final JavaPlugin plugin;

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
   * the initiated plugin.
   */
  @Nullable
  private JavaPlugin pluginInit;

  /**
   * the plugin state.
   */
  @Nullable
  private IllegalStateException pluginState;

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
          TranslatedText.get(JavaPluginClassLoader.PLUGIN_MAIN_CLASS_NOT_FOUND,
            description.getName(), description.getMain()).asString(), ex);
      }
      final Class<? extends JavaPlugin> pluginClass;
      try {
        pluginClass = jarClass.asSubclass(JavaPlugin.class);
      } catch (final ClassCastException ex) {
        throw new InvalidPluginException(
          TranslatedText.get(JavaPluginClassLoader.PLUGIN_DOES_NOT_EXTEND_JAVA_PLUGIN,
            description.getName(), description.getMain()).asString(), ex);
      }
      this.plugin = pluginClass.getConstructor().newInstance();
    } catch (final IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
      throw new InvalidPluginException("No public constructor", ex);
    } catch (final InstantiationException ex) {
      throw new InvalidPluginException("Abnormal plugin type", ex);
    }
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
  protected Class<?> findClass(final String name) throws ClassNotFoundException {
    return this.findClass(name, true);
  }

  /**
   * obtains the plugin.
   *
   * @return plugin.
   */
  @Nullable
  public JavaPlugin getPlugin() {
    return this.plugin;
  }

  @Override
  public URL getResource(final String name) {
    return this.findResource(name);
  }

  @Override
  public Enumeration<URL> getResources(final String name) throws IOException {
    return this.findResources(name);
  }

  @Override
  public String toString() {
    final var currPlugin = this.plugin != null ? this.plugin : this.pluginInit;
    return "JavaPluginClassLoader{" +
      "plugin=" + currPlugin +
      ", pluginEnabled=" + (currPlugin == null ? "uninitialized" : currPlugin.isEnabled()) +
      ", url=" + this.file +
      '}';
  }

  /**
   * finds the given {@code name}.
   *
   * @param name the name to find.
   * @param checkGlobal the check global to find.
   *
   * @return found class.
   *
   * @throws ClassNotFoundException if the given {@code name} starts with net.shiruka. or the class not found.
   */
  Class<?> findClass(@NotNull final String name, final boolean checkGlobal) throws ClassNotFoundException {
    if (name.startsWith("net.shiruka.")) {
      throw new ClassNotFoundException(name);
    }
    Class<?> result = this.classes.get(name);
    if (result != null) {
      return result;
    }
    if (checkGlobal) {
      result = this.loader.getClassByName(name, this);
      if (result != null) {
        final var provider = ((JavaPluginClassLoader) result.getClassLoader()).description;
        if (provider != this.description &&
          !this.seenIllegalAccess.contains(provider.getName()) &&
          !((SimplePluginManager) Shiruka.getPluginManager()).isTransitiveDepend(this.description, provider)) {
          this.seenIllegalAccess.add(provider.getName());
          if (this.plugin != null) {
            this.plugin.getLogger().warn("Loaded class {} from {} which is not a depend, soft-depend or load-before of this plugin.",
              name, provider.getFullName());
          } else {
            Shiruka.getLogger().warn("[{}] Loaded class {} from {} which is not a depend, soft-depend or load-before of this plugin.",
              this.description.getName(), name, provider.getFullName());
          }
        }
      }
    }
    if (result != null) {
      return result;
    }
    final var path = name.replace('.', '/').concat(".class");
    final var entry = this.jar.getJarEntry(path);
    if (entry != null) {
      final byte[] classBytes;
      try (final var is = this.jar.getInputStream(entry)) {
        classBytes = ByteStreams.toByteArray(is);
      } catch (final IOException ex) {
        throw new ClassNotFoundException(name, ex);
      }
      final var dot = name.lastIndexOf('.');
      if (dot != -1) {
        final var pkgName = name.substring(0, dot);
        if (this.getDefinedPackage(pkgName) == null) {
          try {
            if (this.manifest != null) {
              this.definePackage(pkgName, this.manifest, this.url);
            } else {
              this.definePackage(pkgName, null, null, null, null, null, null, null);
            }
          } catch (final IllegalArgumentException ex) {
            Preconditions.checkState(this.getDefinedPackage(pkgName) != null, "Cannot find package " + pkgName);
          }
        }
      }
      final var signers = entry.getCodeSigners();
      final var source = new CodeSource(this.url, signers);
      result = this.defineClass(name, classBytes, 0, classBytes.length, source);
    }
    if (result == null) {
      result = super.findClass(name);
    }
    if (result != null) {
      this.loader.setClass(name, result);
    }
    this.classes.put(name, result);
    return result;
  }

  /**
   * obtains the classes.
   *
   * @return classes.
   */
  @NotNull
  Set<String> getClasses() {
    return this.classes.keySet();
  }

  /**
   * initiates the given {@code plugin}.
   *
   * @param plugin the plugin to initiates.
   */
  synchronized void initialize(@NotNull final JavaPlugin plugin) {
    Validate.isTrue(plugin.getClass().getClassLoader() == this,
      "Cannot initialize plugin outside of this class loader");
    if (this.plugin != null || this.pluginInit != null) {
      throw new IllegalArgumentException("Plugin already initialized!", this.pluginState);
    }
    this.pluginState = new IllegalStateException("Initial initialization");
    this.pluginInit = plugin;
    plugin.setLogger(this.logger);
    plugin.init(this.loader, this.description, this.dataFolder, this.file, this);
  }
}
