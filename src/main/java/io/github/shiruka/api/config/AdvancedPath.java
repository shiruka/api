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

package io.github.shiruka.api.config;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an advanced config path.
 *
 * @param <R> the type of the raw value from the config.
 * @param <T> the type of the final value.
 */
public interface AdvancedPath<R, T> extends ConfigPath<T> {

  /**
   * converts to the final value.
   *
   * @param rawValue the raw value.
   *
   * @return the final value.
   */
  @NotNull
  Optional<T> convertToFinal(@NotNull R rawValue);

  /**
   * converts to the raw value.
   *
   * @param finalValue the value.
   *
   * @return the raw value.
   */
  @NotNull
  Optional<R> convertToRaw(@NotNull T finalValue);

  @NotNull
  @Override
  default Optional<T> getValue() {
    final var config = this.getConfig();
    if (config.isEmpty()) {
      return Optional.empty();
    }
    final var raw = this.rawValue();
    if (raw.isEmpty()) {
      return this.getDefault();
    }
    final var value = this.convertToFinal(raw.get());
    if (value.isPresent()) {
      return value;
    }
    return this.getDefault();
  }

  @Override
  default void setValue(@Nullable final T value) {
    this.getConfig().ifPresent(config ->
      config.set(this.getPath(), Optional.ofNullable(value)
        .flatMap(this::convertToRaw)
        .orElse(null)));
  }

  /**
   * gets the raw value from the config.
   *
   * @return the raw value
   */
  @NotNull
  Optional<R> rawValue();
}
