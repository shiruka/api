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

import io.github.shiruka.api.util.SemanticVersion;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine smallest piece of resource packs.
 */
public interface Pack extends Closeable {

  @Override
  default void close() throws IOException {
    this.getLoader().close();
  }

  /**
   * obtains the chunk.
   *
   * @param offset the offset to get.
   * @param length the length to get.
   *
   * @return chunk.
   */
  default byte[] getChunk(final int offset, final int length) {
    final byte[] chunk;
    if (this.getSize() - offset > length) {
      chunk = new byte[length];
    } else {
      chunk = new byte[(int) (this.getSize() - offset)];
    }
    try (final var stream = Files.newInputStream(this.getLoader().getPreparedFile().join())) {
      stream.skip(offset);
      stream.read(chunk);
    } catch (final Exception e) {
      throw new IllegalStateException("Unable to read pack chunk!");
    }
    return chunk;
  }

  /**
   * obtains the hash.
   *
   * @return hash.
   */
  byte[] getHash();

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  default UUID getId() {
    return this.getManifest().getHeader().getUuid();
  }

  /**
   * obtains the loader.
   *
   * @return loader.
   */
  @NotNull
  PackLoader getLoader();

  /**
   * obtains the manifest.
   *
   * @return manifest.
   */
  @NotNull
  PackManifest getManifest();

  /**
   * obtains the namr.
   *
   * @return name.
   */
  @NotNull
  default String getName() {
    return this.getManifest().getHeader().getName();
  }

  /**
   * obtains the size.
   *
   * @return size.
   */
  default long getSize() {
    try {
      return Files.size(this.getLoader().getPreparedFile().join());
    } catch (final IOException e) {
      throw new IllegalStateException("Unable to get size of pack", e);
    }
  }

  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  PackType getType();

  /**
   * obtains the version.
   *
   * @return version.
   */
  @NotNull
  default SemanticVersion getVersion() {
    return this.getManifest().getHeader().getVersion();
  }

  /**
   * a functional interface to create {@link Pack}.
   */
  @FunctionalInterface
  interface Factory {

    /**
     * create a new pack instance.
     *
     * @param loader the loader to create.
     * @param manifest the manifest to create.
     * @param module the module to create.
     *
     * @return a new pack instance.
     */
    @NotNull
    Pack create(@NotNull PackLoader loader, @NotNull PackManifest manifest, @Nullable PackManifest.Module module);
  }
}
