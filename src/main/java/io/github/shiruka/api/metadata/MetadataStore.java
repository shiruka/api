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

package io.github.shiruka.api.metadata;

import io.github.shiruka.api.plugin.Plugin;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine metadata store.
 *
 * @param <T> type of the where data stored in.
 */
public interface MetadataStore<T> {

  /**
   * creates a unique name for the object receiving metadata by combining unique data from the subject with a key.
   *
   * @param subject the object for which this key is being generated.
   * @param key the name identifying the metadata value.
   *
   * @return a unique metadata key for the given subject.
   */
  @NotNull
  String disambiguate(@NotNull T subject, @NotNull String key);

  /**
   * returns all metadata values attached to an object. if multiple plugins have attached metadata, each will value will
   * be included.
   *
   * @param subject the object being interrogated.
   * @param key the unique metadata key being sought.
   *
   * @return a list of values, one for each plugin that has set the
   *   requested value.
   */
  @NotNull
  List<MetadataValue> getMetadata(@NotNull T subject, @NotNull String key);

  /**
   * tests to see if a metadata attribute has been set on an object.
   *
   * @param subject the object upon which the has-metadata test is performed.
   * @param key the unique metadata key being queried.
   *
   * @return the existence of the key within subject.
   */
  boolean hasMetadata(@NotNull T subject, @NotNull String key);

  /**
   * invalidates all metadata in the metadata store that originates from the given plugin. doing this will force each
   * invalidated metadata item to be recalculated the next time it is accessed.
   *
   * @param plugin the plugin requesting the invalidation.
   */
  void invalidateAll(@NotNull Plugin plugin);

  /**
   * removes all metadata in the metadata store that originates from the given plugin.
   *
   * @param plugin the plugin requesting the invalidation.
   */
  void removeAll(@NotNull final Plugin plugin);

  /**
   * removes a metadata item owned by a plugin from a subject.
   *
   * @param subject the object to remove the metadata from.
   * @param key the unique metadata key identifying the metadata to remove.
   * @param plugin the plugin attempting to remove a metadata item.
   */
  void removeMetadata(@NotNull T subject, @NotNull String key, @NotNull Plugin plugin);

  /**
   * adds a metadata value to an object.
   *
   * @param subject the object receiving the metadata.
   * @param key a unique key to identify this metadata.
   * @param value the metadata value to apply.
   */
  void setMetadata(@NotNull T subject, @NotNull String key, @NotNull MetadataValue value);
}
