package net.shiruka.api.old.pack;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine resource pack loaders.
 */
public interface PackLoader extends Closeable {

  /**
   * collects and runs the {@code consumer} for each collected path in the {@code path}.
   *
   * @param path the path to collect.
   * @param recurse the recurse to collect.
   * @param consumer the consumer to collect.
   */
  void forEachIn(@NotNull Path path, boolean recurse, @NotNull Consumer<Path> consumer);

  /**
   * obtains asset in the given path.
   *
   * @param path the path to get.
   *
   * @return asset.
   *
   * @throws IOException if an I/O error has occurred.
   */
  @NotNull
  Optional<InputStream> getAsset(@NotNull Path path) throws IOException;

  /**
   * obtains the location to load.
   *
   * @return location to load.
   */
  @NotNull
  Path getLocation();

  /**
   * obtains the prepared file.
   *
   * @return prepared file.
   */
  @NotNull
  CompletableFuture<Path> getPreparedFile();

  /**
   * checks if the given path is exist.
   *
   * @param path the path to check.
   *
   * @return {@code true} if the path is exist.
   */
  boolean hasAsset(@NotNull Path path);

  /**
   * checks if the given path is exist as folder.
   *
   * @param path the path to check.
   *
   * @return {@code true} if the path is exist.
   */
  boolean hasFolder(@NotNull Path path);

  /**
   * runs when the server shutdown.
   */
  void shutdown();

  /**
   * an interface to create resource pack loaders.
   */
  interface Factory {

    /**
     * checks if the given path can be loaded by the factory.
     *
     * @param path the path to check.
     *
     * @return {@code true} if the factory can load the pack.
     */
    boolean canLoad(@NotNull Path path);

    /**
     * creates a new loader.
     *
     * @param path the path to create.
     *
     * @return a new resource pack loader instance.
     */
    @Nullable
    PackLoader create(@NotNull Path path);
  }
}
