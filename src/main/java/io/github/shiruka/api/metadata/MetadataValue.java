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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine metaadata values.
 */
public interface MetadataValue {

  /**
   * attempts to convert the value of this metadata item into a boolean.
   *
   * @return the value as a boolean.
   */
  boolean asBoolean();

  /**
   * attempts to convert the value of this metadata item into a Number.
   *
   * @return the value as a Number.
   */
  @Nullable
  Number asNumber();

  /**
   * attempts to convert the value of this metadata item into a string.
   *
   * @return the value as a string.
   */
  @NotNull
  String asString();

  /**
   * returns the {@link Plugin} that created this metadata item.
   *
   * @return the plugin that owns this metadata value. Could be null if the plugin was already unloaded.
   */
  @NotNull
  Plugin getOwningPlugin();

  /**
   * invalidates this metadata item, forcing it to recompute when next accessed.
   */
  void invalidate();

  /**
   * fetches the value of this metadata item.
   *
   * @return the metadata value.
   */
  @NotNull
  Object value();
}
