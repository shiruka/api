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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;

/**
 * an abstract implementation for {@link MetadataStore}.
 *
 * @param <T> type of the where data stored in.
 */
public abstract class MetadataStoreBase<T> implements MetadataStore<T> {

  /**
   * the metadata map.
   */
  private final Map<String, Map<Plugin, MetadataValue>> metadataMap = new ConcurrentHashMap<>();

  @NotNull
  @Override
  public final List<MetadataValue> getMetadata(@NotNull final T subject, @NotNull final String key) {
    final var disambiguate = this.disambiguate(subject, key);
    final var entry = this.metadataMap.get(disambiguate);
    if (entry != null) {
      return List.copyOf(entry.values());
    }
    return Collections.emptyList();
  }

  @Override
  public final boolean hasMetadata(@NotNull final T subject, @NotNull final String key) {
    return this.metadataMap.containsKey(this.disambiguate(subject, key));
  }

  @Override
  public final void invalidateAll(@NotNull final Plugin plugin) {
    this.metadataMap.values().stream()
      .filter(values -> values.containsKey(plugin))
      .forEach(values -> values.get(plugin).invalidate());
  }

  @Override
  public final void removeAll(@NotNull final Plugin plugin) {
    final var iterator = this.metadataMap.values().iterator();
    while (iterator.hasNext()) {
      final var values = iterator.next();
      values.remove(plugin);
      if (values.isEmpty()) {
        iterator.remove();
      }
    }
  }

  @Override
  public final void removeMetadata(@NotNull final T subject, @NotNull final String key, @NotNull final Plugin plugin) {
    final var disambiguate = this.disambiguate(subject, key);
    final var entry = this.metadataMap.get(disambiguate);
    if (entry == null) {
      return;
    }
    synchronized (this.metadataMap) {
      entry.remove(plugin);
      if (entry.isEmpty()) {
        this.metadataMap.remove(disambiguate);
      }
    }
  }

  @Override
  public final void setMetadata(@NotNull final T subject, @NotNull final String key,
                                @NotNull final MetadataValue value) {
    final var owningPlugin = value.plugin();
    final var disambiguate = this.disambiguate(subject, key);
    final var entry = this.metadataMap
      .computeIfAbsent(disambiguate, k -> new WeakHashMap<>(1));
    synchronized (this.metadataMap) {
      entry.put(owningPlugin, value);
    }
  }
}
