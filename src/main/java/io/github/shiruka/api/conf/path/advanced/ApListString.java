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
import io.github.shiruka.api.misc.StickySupplier;
import java.util.List;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that helps to create {@link AdvancedPath} which has {@link List} raw value.
 *
 * @param <T> the final value's type.
 */
public abstract class ApListString<T> extends ApEnvelope<List<String>, T> {

  /**
   * ctor.
   *
   * @param origin the original {@link AdvancedPath}.
   */
  private ApListString(@NotNull final AdvancedPath<List<String>, T> origin) {
    super(new StickySupplier<>(origin));
  }

  /**
   * ctor.
   *
   * @param path the path.
   * @param def the default value.
   * @param convertToFinal the convert to final value function.
   * @param convertToRaw the convert to raw value function.
   */
  protected ApListString(@NotNull final String path, @Nullable final T def,
                         @NotNull final Function<List<String>, T> convertToFinal,
                         @NotNull final Function<T, List<String>> convertToRaw) {
    this(new ApBasic<>(path, def,
      config -> (List<String>) config.get(path)
        .filter(o -> o instanceof List<?>)
        .orElse(null),
      convertToFinal, convertToRaw));
  }
}
