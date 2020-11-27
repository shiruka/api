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

package io.github.shiruka.api.conf;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * the interface of ConfigPath classes.
 *
 * @param <T> the type of the value.
 */
public interface ConfigPath<T> {

  /**
   * obtains the config of the path.
   *
   * @return the config of the path.
   */
  @NotNull
  Optional<Config> getConfig();

  /**
   * sets the config of the path to the given config.
   *
   * @param config the config to set.
   */
  void setConfig(@NotNull Config config);

  /**
   * obtains the default value of the path.
   *
   * @return the default value of the path.
   */
  @NotNull
  Optional<T> getDefault();

  /**
   * obtains tha path.
   *
   * @return the path.
   */
  @NotNull
  String getPath();

  /**
   * gets the value.
   *
   * @return the value.
   */
  @NotNull
  Optional<T> getValue();

  /**
   * sets the value.
   *
   * @param value the value.
   */
  void setValue(@Nullable T value);

  /**
   * removes the value from the path.
   */
  default void removeValue() {
    this.setValue(null);
  }
}
