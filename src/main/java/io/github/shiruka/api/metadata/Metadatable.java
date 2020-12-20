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

package io.github.shiruka.api.metadata;

import io.github.shiruka.api.plugin.Plugin;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine things that able to store metadata.
 */
public interface Metadatable {

  /**
   * returns a list of previously set metadata values from the implementing object's metadata store.
   *
   * @param metadataKey the unique metadata key being sought.
   *
   * @return A list of values, one for each plugin that has set the requested value.
   */
  @NotNull
  List<MetadataValue> getMetadata(@NotNull String metadataKey);

  /**
   * tests to see whether the implementing object contains the given metadata value in its metadata store.
   *
   * @param metadataKey the unique metadata key being queried.
   *
   * @return the existence of the metadataKey within subject.
   */
  boolean hasMetadata(@NotNull String metadataKey);

  /**
   * removes the given metadata value from the implementing object's metadata store.
   *
   * @param metadataKey the unique metadata key identifying the metadata to remove.
   * @param owningPlugin this plugin's metadata value will be removed. all other values will be left untouched.
   *
   * @throws IllegalArgumentException if plugin is null
   */
  void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin);

  /**
   * sets a metadata value in the implementing object's metadata store.
   *
   * @param metadataKey a unique key to identify this metadata.
   * @param newMetadataValue the metadata value to apply.
   *
   * @throws IllegalArgumentException if value is null, or the owning plugin is null
   */
  void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue);
}
