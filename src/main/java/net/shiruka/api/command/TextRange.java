package net.shiruka.api.command;

import java.util.Objects;
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

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof TextRange)) {
      return false;
    }
    final var that = (TextRange) obj;
    return this.start == that.start && this.end == that.end;
  }

  @Override
  public String toString() {
    return "StringRange{" +
      "start=" + this.start +
      ", end=" + this.end +
      '}';
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
