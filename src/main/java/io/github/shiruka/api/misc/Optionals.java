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

package io.github.shiruka.api.misc;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains {@link Optional} like methods.
 */
public final class Optionals {

  /**
   * ctor.
   */
  private Optionals() {
  }

  /**
   * runs the given consumer and returns the given object itself.
   *
   * @param object the object to return and use.
   * @param consumer the consumer to run.
   * @param <T> the object type.
   *
   * @return the given object itself.
   */
  @NotNull
  public static <T> T useAndGet(@NotNull final T object, @NotNull final Consumer<T> consumer) {
    return Optionals.useAndGet(object, t -> true, consumer);
  }

  /**
   * runs the given consumer if the given predicate returns true, and returns the given object itself.
   *
   * @param object the object to return and use.
   * @param predicate the predicate to check.
   * @param consumer the consumer to run.
   * @param <T> the object type.
   *
   * @return the given object itself.
   */
  @NotNull
  public static <T> T useAndGet(@NotNull final T object, @NotNull final Predicate<T> predicate,
                                @NotNull final Consumer<T> consumer) {
    if (predicate.test(object)) {
      consumer.accept(object);
    }
    return object;
  }
}
