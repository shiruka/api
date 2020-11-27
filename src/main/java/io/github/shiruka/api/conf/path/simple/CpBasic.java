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
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a simple implementation for {@link ConfigPath}.
 *
 * @param <T> the value's type.
 */
public final class CpBasic<T> implements ConfigPath<T> {

  /**
   * the convert function.
   */
  @NotNull
  private final Function<Object, T> convert;

  /**
   * the default value.
   */
  @Nullable
  private final T def;

  /**
   * the path.
   */
  @NotNull
  private final String path;

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
   * @param convert the convert function.
   */
  public CpBasic(@NotNull final String path, @Nullable final T def, @NotNull final Function<Object, T> convert) {
    this.path = path;
    this.def = def;
    this.convert = convert;
  }

  /**
   * ctor.
   *
   * @param path the path.
   * @param convert the convert function.
   */
  public CpBasic(@NotNull final String path, @NotNull final Function<Object, T> convert) {
    this(path, null, convert);
  }

  @NotNull
  @Override
  public Optional<Config> getConfig() {
    return Optional.ofNullable(this.config);
  }

  @Override
  public void setConfig(@NotNull final Config config) {
    this.config = config;
    this.config.addDefault(this.path, this.def);
  }

  @NotNull
  @Override
  public Optional<T> getDefault() {
    return Optional.ofNullable(this.def);
  }

  @NotNull
  @Override
  public String getPath() {
    return this.path;
  }

  @NotNull
  @Override
  public Optional<T> getValue() {
    return this.getConfig()
      .flatMap(conf ->
        conf.get(this.path, this.def)
          .map(this.convert));
  }

  @Override
  public void setValue(@Nullable final T value) {
    this.getConfig().ifPresent(conf -> conf.set(this.path, value));
  }
}
