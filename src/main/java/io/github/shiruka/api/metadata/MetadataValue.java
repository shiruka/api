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
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine metaadata values.
 */
public interface MetadataValue {

  /**
   * attempts to convert the value of this metadata item into a boolean.
   *
   * @return the value as a boolean.
   */
  @NotNull
  Optional<Boolean> asBoolean();

  /**
   * attempts to convert the value of this metadata item into a boolean.
   *
   * @return the value as a boolean.
   *
   * @throws UnsupportedOperationException if the value is not a {@link Boolean}.
   */
  default boolean asBooleanOrThrow() {
    return this.asBoolean().orElseThrow(UnsupportedOperationException::new);
  }

  /**
   * attempts to convert the value of this metadata item into a Number.
   *
   * @return the value as a Number.
   */
  @NotNull
  Optional<Number> asNumber();

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
  Optional<String> asString();

  /**
   * attempts to convert the value of this metadata item into a string.
   *
   * @return the value as a string.
   *
   * @throws UnsupportedOperationException if the value is not a {@link String}.
   */
  @NotNull
  default String asStringOrThrow() {
    return this.asString().orElseThrow(UnsupportedOperationException::new);
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
  @NotNull
  Plugin plugin();

  /**
   * fetches the value of this metadata item.
   *
   * @return the metadata value.
   */
  @NotNull
  Object value();
}
