/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
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

package io.github.shiruka.api.pack;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

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
}
