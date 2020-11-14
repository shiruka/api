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

package io.github.shiruka.api.conf.path.advanced;

import io.github.shiruka.api.conf.AdvancedPath;
import io.github.shiruka.api.conf.Config;
import java.util.Optional;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a simple implementation for {@link AdvancedPath}
 *
 * @param <R> the raw value's type.
 * @param <T> the final value's type.
 */
public final class ApBasic<R, T> implements AdvancedPath<R, T> {

  /**
   * the path.
   */
  @NotNull
  private final String path;

  /**
   * the default value.
   */
  @Nullable
  private final T def;

  /**
   * the raw value function.
   */
  @NotNull
  private final Function<Config, R> rawValue;

  /**
   * the convert to final value function.
   */
  @NotNull
  private final Function<R, T> convertToFinal;

  /**
   * the convert to raw value function.
   */
  @NotNull
  private final Function<T, R> convertToRaw;

  /**
   * the config.
   */
  @Nullable
  private Config config;

  /**
   * ctor.
   *
   * @param path the path.
   * @param def the default value.
   * @param rawValue the raw value function.
   * @param convertToFinal the convert to final value function.
   * @param convertToRaw the convert to raw value function.
   */
  public ApBasic(@NotNull final String path, @Nullable final T def, @NotNull final Function<Config, R> rawValue,
                 @NotNull final Function<R, T> convertToFinal, @NotNull final Function<T, R> convertToRaw) {
    this.path = path;
    this.def = def;
    this.rawValue = rawValue;
    this.convertToFinal = convertToFinal;
    this.convertToRaw = convertToRaw;
  }

  @NotNull
  @Override
  public Optional<R> rawValue() {
    return this.getConfig().map(this.rawValue);
  }

  @NotNull
  @Override
  public Optional<T> convertToFinal(@NotNull final R rawValue) {
    return Optional.ofNullable(this.convertToFinal.apply(rawValue));
  }

  @NotNull
  @Override
  public Optional<R> convertToRaw(@NotNull final T finalValue) {
    return Optional.ofNullable(this.convertToRaw.apply(finalValue));
  }

  @NotNull
  @Override
  public String getPath() {
    return this.path;
  }

  @NotNull
  @Override
  public Optional<T> getDefault() {
    return Optional.ofNullable(this.def);
  }

  @NotNull
  @Override
  public Optional<Config> getConfig() {
    return Optional.ofNullable(this.config);
  }

  @Override
  public void setConfig(@NotNull final Config config) {
    this.config = config;
    this.config.addDefault(this.path, this.getDefault()
      .map(this::convertToRaw)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .orElse(null));
  }
}
