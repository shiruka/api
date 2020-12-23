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

package io.github.shiruka.api.command;

import io.github.shiruka.api.base.Named;
import java.util.Collection;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine arg schemes.
 *
 * @param <V> type of the arg value at runtime.
 */
public interface ArgScheme<V> extends Named {

  /**
   * converts the given original input into the runtime arg value.
   *
   * @param original the original to create.
   *
   * @return converted input value.
   */
  @NotNull
  Optional<V> create(@NotNull String original);

  /**
   * checks if the arg scheme is literal or not.
   *
   * @return {@code true} if the arg scheme is literal, otherwise {@code false}.
   */
  boolean isLiteral();

  /**
   * obtains the suggestions for the current arg scheme.
   *
   * @return suggestions of the current arg.
   */
  @NotNull
  Collection<String> suggestions(@NotNull CommandSender sender, @NotNull ArgCollection<?> previousArgs,
                                 @NotNull String current);

  /**
   * obtains type of the arg value at runtime.
   *
   * @return arg type at runtime.
   */
  @NotNull
  Class<V> type();
}
