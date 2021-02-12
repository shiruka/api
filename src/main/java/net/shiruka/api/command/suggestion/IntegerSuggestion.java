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
import net.shiruka.api.command.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents integer suggestions.
 */
public final class IntegerSuggestion extends Suggestion {

  /**
   * the suggestion.
   */
  private final int suggestion;

  /**
   * ctor.
   *
   * @param range the range.
   * @param suggestion the suggestion.
   */
  public IntegerSuggestion(final TextRange range, final int suggestion) {
    this(range, suggestion, null);
  }

  /**
   * ctor.
   *
   * @param range the range.
   * @param suggestion the suggestion.
   * @param tooltip the tooltip.
   */
  public IntegerSuggestion(@NotNull final TextRange range, final int suggestion, @Nullable final String tooltip) {
    super(range, Integer.toString(suggestion), tooltip);
    this.suggestion = suggestion;
  }

  @Override
  public int compareTo(final Suggestion o) {
    if (o instanceof IntegerSuggestion) {
      return Integer.compare(this.suggestion, ((IntegerSuggestion) o).suggestion);
    }
    return super.compareTo(o);
  }

  @Override
  public int compareToIgnoreCase(@NotNull final Suggestion suggestion) {
    return this.compareTo(suggestion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.suggestion);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof IntegerSuggestion)) {
      return false;
    }
    final var that = (IntegerSuggestion) obj;
    return this.suggestion == that.suggestion && super.equals(obj);
  }

  @Override
  public String toString() {
    return "IntegerSuggestion{" +
      "suggestion=" + this.suggestion +
      ", range=" + this.getRange() +
      ", text='" + this.getText() + '\'' +
      ", tooltip='" + this.getTooltip() + '\'' +
      '}';
  }

  /**
   * obtains the suggestion.
   *
   * @return suggestion.
   */
  public int getSuggestion() {
    return this.suggestion;
  }
}
