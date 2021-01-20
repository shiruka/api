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

package net.shiruka.api.metadata;

import java.util.Optional;
import java.util.concurrent.Callable;
import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine metadata values.
 */
public interface MetadataValue {

  /**
   * creates a new {@link FixedMetadataValue} instance.
   *
   * @param plugin the plugin to create.
   * @param object the object to create.
   *
   * @return a new fixed metadata value instance.
   */
  @NotNull
  static FixedMetadataValue fixed(@NotNull final Plugin plugin, @NotNull final Object object) {
    return new FixedMetadataValue(plugin, object);
  }

  /**
   * creates a new {@link LazyMetadataValue} instance.
   *
   * @param plugin the plugin to create.
   * @param value the value to create.
   *
   * @return a new lazy metadata value instance.
   */
  @NotNull
  static LazyMetadataValue lazy(@NotNull final Plugin plugin, @NotNull final Callable<Object> value) {
    return new LazyMetadataValue(plugin, value);
  }

  /**
   * creates a new {@link LazyMetadataValue} instance.
   *
   * @param plugin the plugin to create.
   * @param strategy the strategy to create.
   * @param value the value to create.
   *
   * @return a new lazy metadata value instance.
   */
  @NotNull
  static LazyMetadataValue lazy(@NotNull final Plugin plugin, @NotNull final CacheStrategy strategy,
                                @NotNull final Callable<Object> value) {
    return new LazyMetadataValue(plugin, strategy, value);
  }

  /**
   * attempts to convert the value of this metadata item into a boolean.
   *
   * @return the value as a boolean.
   */
  default boolean asBoolean() {
    final var value = this.value();
    if (value instanceof Boolean) {
      return (Boolean) value;
    }
    if (value instanceof Number) {
      return ((Number) value).byteValue() != 0;
    }
    if (value instanceof String) {
      return Boolean.parseBoolean((String) value);
    }
    return true;
  }

  /**
   * attempts to convert the value of this metadata item into a Number.
   *
   * @return the value as a Number.
   */
  @NotNull
  default Optional<Number> asNumber() {
    final var value = this.value();
    if (value instanceof Number) {
      return Optional.of((Number) value);
    }
    return Optional.empty();
  }

  /**
   * attempts to convert the value of this metadata item into a Number.
   *
   * @return the value as a Number.
   *
   * @throws UnsupportedOperationException if the value is not a {@link Number}.
   */
  @NotNull
  default Number asNumberOrThrow() {
    return this.asNumber().orElseThrow(UnsupportedOperationException::new);
  }

  /**
   * attempts to convert the value of this metadata item into a string.
   *
   * @return the value as a string.
   */
  @NotNull
  default String asString() {
    final var value = this.value();
    if (value == null) {
      return "";
    }
    return value.toString();
  }

  /**
   * invalidates this metadata item, forcing it to recompute when next accessed.
   */
  void invalidate();

  /**
   * returns the {@link Plugin} that created this metadata item.
   *
   * @return the plugin that owns this metadata value. Could be null if the plugin was already unloaded.
   */
  @Nullable
  Plugin plugin();

  /**
   * fetches the value of this metadata item.
   *
   * @return the metadata value.
   */
  @Nullable
  Object value();
}
