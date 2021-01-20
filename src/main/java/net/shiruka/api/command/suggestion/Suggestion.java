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

package net.shiruka.api.command.suggestion;

import java.util.Objects;
import java.util.function.UnaryOperator;
import net.shiruka.api.command.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents suggestion.
 */
public class Suggestion implements UnaryOperator<String>, Comparable<Suggestion> {

  /**
   * the range.
   */
  @NotNull
  private final TextRange range;

  /**
   * the text.
   */
  @NotNull
  private final String text;

  /**
   * the tooltip.
   */
  @Nullable
  private final String tooltip;

  /**
   * ctor.
   *
   * @param range the range.
   * @param text the text.
   * @param tooltip the tooltip.
   */
  public Suggestion(@NotNull final TextRange range, @NotNull final String text, @Nullable final String tooltip) {
    this.range = range;
    this.text = text;
    this.tooltip = tooltip;
  }

  /**
   * ctor.
   *
   * @param range the range.
   * @param text the text.
   */
  public Suggestion(@NotNull final TextRange range, @NotNull final String text) {
    this(range, text, null);
  }

  /**
   * obtains the range.
   *
   * @return range.
   */
  @NotNull
  public final TextRange getRange() {
    return this.range;
  }

  /**
   * obtains the text.
   *
   * @return text.
   */
  @NotNull
  public final String getText() {
    return this.text;
  }

  /**
   * obtains the tooltip.
   *
   * @return tooltip.
   */
  @Nullable
  public final String getTooltip() {
    return this.tooltip;
  }

  @NotNull
  @Override
  public String apply(@NotNull final String input) {
    if (this.range.getStart() == 0 && this.range.getEnd() == input.length()) {
      return this.text;
    }
    final var result = new StringBuilder();
    if (this.range.getStart() > 0) {
      result.append(input, 0, this.range.getStart());
    }
    result.append(this.text);
    if (this.range.getEnd() < input.length()) {
      result.append(input.substring(this.range.getEnd()));
    }
    return result.toString();
  }

  @Override
  public int compareTo(final Suggestion o) {
    return this.text.compareTo(o.text);
  }

  /**
   * compares the given suggestion with {@code this}.
   *
   * @param suggestion the suggestion to compare.
   *
   * @return compare result.
   */
  public int compareToIgnoreCase(@NotNull final Suggestion suggestion) {
    return this.text.compareToIgnoreCase(suggestion.text);
  }

  /**
   * expands the given command and range.
   *
   * @param command the command to expand.
   * @param range the range to expand.
   *
   * @return a new suggestion instance with the same tooltip.
   */
  @NotNull
  public Suggestion expand(@NotNull final String command, @NotNull final TextRange range) {
    if (range.equals(this.range)) {
      return this;
    }
    final var result = new StringBuilder();
    if (range.getStart() < this.range.getStart()) {
      result.append(command, range.getStart(), this.range.getStart());
    }
    result.append(this.text);
    if (range.getEnd() > this.range.getEnd()) {
      result.append(command, this.range.getEnd(), range.getEnd());
    }
    return new Suggestion(range, result.toString(), this.tooltip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.range, this.text, this.tooltip);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Suggestion)) {
      return false;
    }
    final var that = (Suggestion) obj;
    return Objects.equals(this.range, that.range) &&
      Objects.equals(this.text, that.text) &&
      Objects.equals(this.tooltip, that.tooltip);
  }

  @Override
  public String toString() {
    return "Suggestion{" +
      "range=" + this.range +
      ", text='" + this.text + '\'' +
      ", tooltip='" + this.tooltip + '\'' +
      '}';
  }
}
