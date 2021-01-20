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

package net.shiruka.api.command.context;

import java.util.Objects;
import net.shiruka.api.command.TextRange;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents parsed arguments.
 *
 * @param <V> type of the argument value.
 */
public final class ParsedArgument<V> {

  /**
   * the range.
   */
  @NotNull
  private final TextRange range;

  /**
   * the result.
   */
  @NotNull
  private final V result;

  /**
   * ctor.
   *
   * @param start the start.
   * @param end the end.
   * @param result the result.
   */
  public ParsedArgument(final int start, final int end, @NotNull final V result) {
    this.range = TextRange.between(start, end);
    this.result = result;
  }

  /**
   * obtains the range.
   *
   * @return range.
   */
  @NotNull
  public TextRange getRange() {
    return this.range;
  }

  /**
   * obtains the result.
   *
   * @return result.
   */
  @NotNull
  public V getResult() {
    return this.result;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.range, this.result);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ParsedArgument)) {
      return false;
    }
    final var that = (ParsedArgument<?>) obj;
    return Objects.equals(this.range, that.range) && Objects.equals(this.result, that.result);
  }
}
