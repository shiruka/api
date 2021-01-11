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

package io.github.shiruka.api.misc;

import java.util.Optional;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that uses {@link Supplier} and caches it to use after.
 *
 * @param <T> the value's type.
 */
public final class StickySupplier<T> implements Supplier<T> {

  /**
   * the original {@link Supplier}.
   */
  @NotNull
  private final Supplier<T> origin;

  /**
   * the cache value.
   */
  @Nullable
  private T cache;

  /**
   * ctor.
   *
   * @param origin the original {@link Supplier}.
   */
  public StickySupplier(@NotNull final Supplier<T> origin) {
    this.origin = origin;
  }

  /**
   * ctor.
   *
   * @param origin the original {@link T}.
   */
  public StickySupplier(@NotNull final T origin) {
    this(() -> origin);
  }

  @Override
  public T get() {
    return Optional.ofNullable(this.cache).orElseGet(() -> {
      this.cache = this.origin.get();
      return this.cache;
    });
  }
}
