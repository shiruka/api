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
