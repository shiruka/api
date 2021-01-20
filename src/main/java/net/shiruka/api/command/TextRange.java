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

package net.shiruka.api.command;

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents text range.
 */
public final class TextRange {

  /**
   * the end.
   */
  private final int end;

  /**
   * the start.
   */
  private final int start;

  /**
   * ctor.
   *
   * @param start the start.
   * @param end the end.
   */
  private TextRange(final int start, final int end) {
    this.start = start;
    this.end = end;
  }

  /**
   * creates a new text range instance at the given position.
   *
   * @param position the position to create.
   *
   * @return a new text range instance.
   */
  @NotNull
  public static TextRange at(final int position) {
    return new TextRange(position, position);
  }

  /**
   * creates a new text range instance between {@code start} and {@code end}.
   *
   * @param start the start to create.
   * @param end the end to create.
   *
   * @return a new text range instance.
   */
  @NotNull
  public static TextRange between(final int start, final int end) {
    return new TextRange(start, end);
  }

  /**
   * creates a new text range instance encompassing {@code a} and {@code b}.
   *
   * @param a the a to create.
   * @param b the b to create.
   *
   * @return a new text range instance.
   */
  @NotNull
  public static TextRange encompassing(@NotNull final TextRange a, @NotNull final TextRange b) {
    return new TextRange(Math.min(a.getStart(), b.getStart()), Math.max(a.getEnd(), b.getEnd()));
  }

  /**
   * obtains the text between {@code start} and {@code end}.
   *
   * @param text the text to obtains.
   *
   * @return text between {@code start} and {@code end}.
   */
  @NotNull
  public String get(@NotNull final String text) {
    return text.substring(this.start, this.end);
  }

  /**
   * obtains the string value from the given reader.
   *
   * @param reader the reader to obtain.
   *
   * @return a string value between {@code start} and {@code end}.
   */
  @NotNull
  public String get(@NotNull final TextReader reader) {
    return this.get(reader.getText());
  }

  /**
   * obtains the end.
   *
   * @return end.
   */
  public int getEnd() {
    return this.end;
  }

  /**
   * obtains the start.
   *
   * @return start.
   */
  public int getStart() {
    return this.start;
  }

  /**
   * checks if the start and end is equal.
   *
   * @return {@code true} if the start and end are equal.
   */
  public boolean isEmpty() {
    return this.start == this.end;
  }
}
