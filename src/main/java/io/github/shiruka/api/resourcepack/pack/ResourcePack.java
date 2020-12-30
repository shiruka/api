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

package io.github.shiruka.api.resourcepack.pack;

import io.github.shiruka.api.resourcepack.Pack;
import io.github.shiruka.api.resourcepack.ResourcePackLoader;
import io.github.shiruka.api.resourcepack.ResourcePackManifest;
import io.github.shiruka.api.resourcepack.ResourcePackType;
import java.nio.file.Files;
import java.security.MessageDigest;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource packs.
 */
public final class ResourcePack implements Pack {

  /**
   * the loader.
   */
  @NotNull
  private final ResourcePackLoader loader;

  /**
   * the manifest.
   */
  @NotNull
  private final ResourcePackManifest manifest;

  /**
   * the hash.
   */
  private byte[] hash;

  /**
   * ctor.
   *
   * @param loader the loader.
   * @param manifest the manifest.
   */
  public ResourcePack(@NotNull final ResourcePackLoader loader, @NotNull final ResourcePackManifest manifest) {
    this.loader = loader;
    this.manifest = manifest;
  }

  @Override
  public byte[] getHash() {
    if (this.hash == null) {
      try {
        this.hash = MessageDigest.getInstance("SHA-256")
          .digest(Files.readAllBytes(this.loader.getPreparedFile().join()));
      } catch (final Exception e) {
        throw new IllegalStateException("Unable to get hash of pack", e);
      }
    }
    return this.hash.clone();
  }

  @NotNull
  @Override
  public ResourcePackLoader getLoader() {
    return this.loader;
  }

  @NotNull
  @Override
  public ResourcePackManifest getManifest() {
    return this.manifest;
  }

  @NotNull
  @Override
  public ResourcePackType getType() {
    return ResourcePackType.RESOURCES;
  }
}
