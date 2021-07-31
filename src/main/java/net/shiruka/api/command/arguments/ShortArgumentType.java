package net.shiruka.api.command.arguments;

import java.util.Collection;
import java.util.List;
import net.shiruka.api.command.ArgumentType;
import net.shiruka.api.command.CommandException;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.jetbrains.annotations.NotNull;

/**
 * a simple short argument type implementation for {@link ArgumentType}.
 */
public final class ShortArgumentType implements ArgumentType<Short> {

  /**
   * the examples.
   */
  private static final Collection<String> EXAMPLES = List.of("0", "123", "-123");

  /**
   * the maximum.
   */
  private final short maximum;

  /**
   * the minimum.
   */
  private final short minimum;

  /**
   * ctor.
   *
   * @param minimum the minimum.
   * @param maximum the maximum.
   */
  public ShortArgumentType(final short minimum, final short maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return ShortArgumentType.EXAMPLES;
  }

  @NotNull
  @Override
  public Short parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var result = reader.readShort();
    if (result < this.minimum) {
      reader.setCursor(start);
      throw CommandException.SHORT_TOO_SMALL.createWithContext(reader, result, this.minimum);
    }
    if (result > this.maximum) {
      reader.setCursor(start);
      throw CommandException.SHORT_TOO_BIG.createWithContext(reader, result, this.maximum);
    }
    return result;
  }

  @Override
  public int hashCode() {
    return 31 * this.minimum + this.maximum;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ShortArgumentType)) {
      return false;
    }
    final var that = (ShortArgumentType) obj;
    return this.maximum == that.maximum && this.minimum == that.minimum;
  }

  @Override
  public String toString() {
    if (this.minimum == Short.MIN_VALUE && this.maximum == Short.MAX_VALUE) {
      return "shortArg()";
    }
    if (this.maximum == Short.MAX_VALUE) {
      return "shortArg(" + this.minimum + ")";
    }
    return "shortArg(" + this.minimum + ", " + this.maximum + ")";
  }
}
