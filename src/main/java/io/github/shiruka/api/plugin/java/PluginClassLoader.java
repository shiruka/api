package io.github.shiruka.api.plugin.java;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.ByteStreams;
import com.google.inject.Guice;
import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.plugin.InvalidPluginException;
import io.github.shiruka.api.plugin.Plugin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipFile;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents plugin class loaders.
 */
@Accessors(fluent = true)
public final class PluginClassLoader extends URLClassLoader {

  /**
   * the classes.
   */
  private final Map<String, Class<?>> classes = Maps.newConcurrentMap();

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
   * the manifest.
   */
  @NotNull
  private final Manifest manifest;

  /**
   * the plugin container.
   */
  @Getter
  @NotNull
  private final Plugin.Container pluginContainer;

  /**
   * the seen illegal access.
   */
  private final Collection<String> seenIllegalAccess = Sets.newConcurrentHashSet();

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
    this.jar = new JarFile(pluginFile, true, ZipFile.OPEN_READ, JarFile.runtimeVersion());
    this.manifest = this.jar.getManifest();
    this.url = pluginFile.toURI().toURL();
    final var logger = LogManager.getLogger(description.prefix());
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
    } catch (final ClassCastException e) {
      throw new InvalidPluginException("Main class `%s' does not implement Plugin", e,
        mainClassPath);
    }
    this.pluginContainer = new Plugin.Container(
      this,
      dataFolder,
      description,
      this.loader,
      logger,
      Guice.createInjector(new JavaPluginModule(dataFolder, description, logger, pluginFile, this))
        .getInstance(pluginClass),
      pluginFile);
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
  protected Class<?> findClass(@NotNull final String name) throws ClassNotFoundException {
    if (name.startsWith("io.github.shiruka.")) {
      throw new ClassNotFoundException(name);
    }
    var result = this.classes.get(name);
    if (result != null) {
      return result;
    }
    final var path = name.replace('.', '/').concat(".class");
    final var entry = this.jar.getJarEntry(path);
    if (entry == null) {
      result = super.findClass(name);
      this.classes.put(name, result);
      return result;
    }
    final byte[] classBytes;
    try (final var is = this.jar.getInputStream(entry)) {
      classBytes = ByteStreams.toByteArray(is);
    } catch (final IOException ex) {
      throw new ClassNotFoundException(name, ex);
    }
    final var dot = name.lastIndexOf('.');
    if (dot != -1) {
      final var packageName = name.substring(0, dot);
      if (this.getDefinedPackage(packageName) == null) {
        try {
          this.definePackage(packageName, this.manifest, this.url);
        } catch (final IllegalArgumentException ex) {
          Preconditions.checkState(this.getDefinedPackage(packageName) != null, "Cannot find package %s",
            packageName);
        }
      }
    }
    result = this.defineClass(
      name,
      classBytes,
      0,
      classBytes.length,
      new CodeSource(this.url, entry.getCodeSigners()));
    if (result == null) {
      result = super.findClass(name);
    }
    this.classes.put(name, result);
    return result;
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
   * @param classPath the class path to load.
   * @param resolve the resolve to load.
   * @param checkGlobal the check global to load.
   *
   * @return loaded class.
   *
   * @throws ClassNotFoundException if the class not found.
   */
  @NotNull
  Class<?> loadClass0(@NotNull final String classPath, final boolean resolve, final boolean checkGlobal)
    throws ClassNotFoundException {
    try {
      return super.loadClass(classPath, resolve);
    } catch (final ClassNotFoundException ignored) {
    }
    if (!checkGlobal) {
      throw new ClassNotFoundException(classPath);
    }
    final var result = Optional.ofNullable(this.loader.getClassByName(classPath, resolve, this.pluginContainer, this))
      .orElseThrow(() -> new ClassNotFoundException(classPath));
    if (!(result.getClassLoader() instanceof PluginClassLoader pluginClassLoader)) {
      return result;
    }
    final var provider = pluginClassLoader.pluginContainer();
    final var description = provider.description();
    final var name = description.name();
    if (provider != this.pluginContainer &&
      !this.seenIllegalAccess.contains(name) &&
      !Shiruka.pluginManager().isTransitiveDepend(this.pluginContainer, provider)) {
      this.seenIllegalAccess.add(name);
      this.pluginContainer.logger().warn("Loaded class {} from {} which is not a depend, soft-depend or load-before of this plugin.",
        classPath, description.fullName());
    }
    return result;
  }
}
