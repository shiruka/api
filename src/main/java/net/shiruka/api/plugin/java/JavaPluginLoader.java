package net.shiruka.api.plugin.java;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import lombok.Cleanup;
import net.shiruka.api.plugin.InvalidDescriptionException;
import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents java plugin loaders which is implementation of {@link Plugin.Loader}.
 */
public final class JavaPluginLoader implements Plugin.Loader {

  /**
   * creates a description instance from the file.
   *
   * @param file the file to create.
   *
   * @return a newly created description from file.
   *
   * @throws java.io.FileNotFoundException if the file not found.
   * @throws IOException if something goes wrong when reading values in the file.
   * @throws InvalidDescriptionException if something goes wrong when parsing the map.
   */
  @NotNull
  private static Plugin.Description load(@NotNull final Path file) throws IOException, InvalidDescriptionException {
    @Cleanup final var stream = new JarInputStream(new BufferedInputStream(Files.newInputStream(file)));
    @Nullable JarEntry entry;
    while ((entry = stream.getNextJarEntry()) != null) {
      if (entry.isDirectory() ||
        !entry.getName().endsWith(".class")) {
        continue;
      }
      try {
        return Plugin.Description.of(stream);
      } catch (final Exception e) {
        throw new InvalidDescriptionException("Plugin does not have valid description file!", e);
      }
    }
    throw new InvalidDescriptionException("Plugin does not have valid description file!");
  }

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
