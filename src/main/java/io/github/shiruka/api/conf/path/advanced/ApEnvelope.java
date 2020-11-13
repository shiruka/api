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
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * an envelope class for {@link AdvancedPath}.
 *
 * @param <R> the raw value's type.
 * @param <T> the final value's type.
 */
public abstract class ApEnvelope<R, T> implements AdvancedPath<R, T> {

  /**
   * the original {@link AdvancedPath}.
   */
  @NotNull
  private final Supplier<AdvancedPath<R, T>> origin;

  /**
   * ctor.
   *
   * @param origin the original.
   */
  protected ApEnvelope(@NotNull final Supplier<AdvancedPath<R, T>> origin) {
    this.origin = origin;
  }

  @NotNull
  @Override
  public final Optional<R> rawValue() {
    return this.origin.get().rawValue();
  }

  @NotNull
  @Override
  public final Optional<T> convertToFinal(@NotNull final R rawValue) {
    return this.origin.get().convertToFinal(rawValue);
  }

  @NotNull
  @Override
  public final Optional<R> convertToRaw(@NotNull final T finalValue) {
    return this.origin.get().convertToRaw(finalValue);
  }

  @NotNull
  @Override
  public final String getPath() {
    return this.origin.get().getPath();
  }

  @NotNull
  @Override
  public final Optional<T> getDefault() {
    return this.origin.get().getDefault();
  }

  @NotNull
  @Override
  public final Optional<Config> getConfig() {
    return this.origin.get().getConfig();
  }

  @Override
  public final void setConfig(@NotNull final Config config) {
    this.origin.get().setConfig(config);
  }
}
