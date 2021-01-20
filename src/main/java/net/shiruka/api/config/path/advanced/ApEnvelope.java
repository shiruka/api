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

package net.shiruka.api.config.path.advanced;

import java.util.Optional;
import net.shiruka.api.config.AdvancedPath;
import net.shiruka.api.config.Config;
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
  private final AdvancedPath<R, T> origin;

  /**
   * ctor.
   *
   * @param origin the original.
   */
  protected ApEnvelope(@NotNull final AdvancedPath<R, T> origin) {
    this.origin = origin;
  }

  @NotNull
  @Override
  public final Optional<T> convertToFinal(@NotNull final R rawValue) {
    return this.origin.convertToFinal(rawValue);
  }

  @NotNull
  @Override
  public final Optional<R> convertToRaw(@NotNull final T finalValue) {
    return this.origin.convertToRaw(finalValue);
  }

  @NotNull
  @Override
  public final Optional<R> rawValue() {
    return this.origin.rawValue();
  }

  @NotNull
  @Override
  public final Optional<Config> getConfig() {
    return this.origin.getConfig();
  }

  @Override
  public final void setConfig(@NotNull final Config config) {
    this.origin.setConfig(config);
  }

  @NotNull
  @Override
  public final Optional<T> getDefault() {
    return this.origin.getDefault();
  }

  @NotNull
  @Override
  public final String getPath() {
    return this.origin.getPath();
  }
}
