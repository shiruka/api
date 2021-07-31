package net.shiruka.api.old.command.arguments;

import java.util.Collection;
import java.util.List;
import net.shiruka.api.old.command.ArgumentType;
import net.shiruka.api.old.command.CommandException;
import net.shiruka.api.old.command.TextReader;
import net.shiruka.api.old.command.exceptions.CommandSyntaxException;
import org.jetbrains.annotations.NotNull;

/**
 * a simple byte argument type implementation for {@link ArgumentType}.
 */
public final class ByteArgumentType implements ArgumentType<Byte> {

  /**
   * the examples.
   */
  private static final Collection<String> EXAMPLES = List.of("-128", "0", "127");

  /**
   * the maximum.
   */
  private final byte maximum;

  /**
   * the minimum.
   */
  private final byte minimum;

  /**
   * ctor.
   *
   * @param minimum the minimum.
   * @param maximum the maximum.
   */
  public ByteArgumentType(final byte minimum, final byte maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return ByteArgumentType.EXAMPLES;
  }

  @NotNull
  @Override
  public Byte parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var result = reader.readByte();
    if (result < this.minimum) {
      reader.setCursor(start);
      throw CommandException.BYTE_TOO_SMALL.createWithContext(reader, result, this.minimum);
    }
    if (result > this.maximum) {
      reader.setCursor(start);
      throw CommandException.BYTE_TOO_BIG.createWithContext(reader, result, this.maximum);
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
    if (!(obj instanceof ByteArgumentType)) {
      return false;
    }
    final var that = (ByteArgumentType) obj;
    return this.maximum == that.maximum && this.minimum == that.minimum;
  }

  @Override
  public String toString() {
    if (this.minimum == Byte.MIN_VALUE && this.maximum == Byte.MAX_VALUE) {
      return "byteArg()";
    }
    if (this.maximum == Byte.MAX_VALUE) {
      return "byteArg(" + this.minimum + ")";
    }
    return "byteArg(" + this.minimum + ", " + this.maximum + ")";
  }
}
