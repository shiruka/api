package net.shiruka.api.old.command.suggestion;

import java.util.Objects;
import net.shiruka.api.old.command.TextRange;
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
