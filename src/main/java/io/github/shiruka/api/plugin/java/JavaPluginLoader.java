package io.github.shiruka.api.plugin.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.JarFile;
import io.github.shiruka.api.plugin.InvalidDescriptionException;
import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents java plugin loaders which is implementation of {@link Plugin.Loader}.
 */
public final class JavaPluginLoader implements Plugin.Loader {

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
}
