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

package io.github.shiruka.api.conf.path.simple;

import io.github.shiruka.api.conf.Config;
import io.github.shiruka.api.conf.ConfigPath;
import java.util.Optional;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an envelope class for {@link ConfigPath}.
 *
 * @param <T> the value's type.
 */
public abstract class CpEnvelope<T> implements ConfigPath<T> {

  /**
   * the original {@link ConfigPath}.
   */
  @NotNull
  private final Supplier<ConfigPath<T>> origin;

  /**
   * ctor.
   *
   * @param origin the original {@link ConfigPath}.
   */
  protected CpEnvelope(@NotNull final Supplier<ConfigPath<T>> origin) {
    this.origin = origin;
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

  @NotNull
  @Override
  public final Optional<T> getValue() {
    return this.origin.get().getValue();
  }

  @Override
  public final void setValue(@Nullable final T value) {
    this.origin.get().setValue(value);
  }
}
