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
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine things that able to store metadata.
 */
public interface Metadatable {

  /**
   * finds first the metadata values from the implementing object's metadata store.
   *
   * @param key the key to get.
   *
   * @return the first requested metadata value for the key.
   *
   * @see #getMetadata(String)
   */
  @NotNull
  default Optional<MetadataValue> getFirstMetadata(@NotNull final String key) {
    return Optional.of(this.getMetadata(key))
      .filter(values -> !values.isEmpty())
      .map(values -> values.get(0));
  }

  /**
   * returns a list of previously set metadata values from the implementing object's metadata store.
   *
   * @param key the key to get.
   *
   * @return a list of values, one for each plugin that has set the requested value.
   */
  @NotNull
  List<MetadataValue> getMetadata(@NotNull String key);

  /**
   * tests to see whether the implementing object contains the given metadata value in its metadata store.
   *
   * @param key the key to check.
   *
   * @return the existence of the metadataKey within subject.
   */
  boolean hasMetadata(@NotNull String key);

  /**
   * removes all the given metadata value from the implementing object's metadata store.
   *
   * @param key the key to remove.
   */
  void removeAllMetadata(@NotNull String key);

  /**
   * removes the given metadata value from the implementing object's metadata store.
   *
   * @param key the key to remove.
   * @param plugin the value to remove.
   */
  void removeMetadata(@NotNull String key, @NotNull Plugin plugin);

  /**
   * sets a fixed metadata value in the implementing object's metadata store.
   *
   * @param key the key to set.
   * @param plugin the plugin to set.
   * @param value the value to set.
   */
  default void setFixedMetadata(@NotNull final String key, @NotNull final Plugin plugin, @NotNull final Object value) {
    this.setMetadata(key, new FixedMetadata(plugin, value));
  }

  /**
   * sets a metadata value in the implementing object's metadata store.
   *
   * @param key the key to set.
   * @param value the value to set.
   */
  void setMetadata(@NotNull String key, @NotNull MetadataValue value);
}
