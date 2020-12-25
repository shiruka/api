// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package io.github.shiruka.api.command.arguments;

import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.exceptions.CommandException;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * a simple long argument type implementation for {@link ArgumentType}.
 */
public final class LongArgumentType implements ArgumentType<Long> {

  /**
   * the examples.
   */
  private static final Collection<String> EXAMPLES = List.of("0", "123", "-123", "+123");

  /**
   * the maximum.
   */
  private final long maximum;

  /**
   * the minimum.
   */
  private final long minimum;

  /**
   * ctor.
   *
   * @param minimum the minimum.
   * @param maximum the maximum.
   */
  LongArgumentType(final long minimum, final long maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return LongArgumentType.EXAMPLES;
  }

  @NotNull
  @Override
  public Long parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var result = reader.readLong();
    if (result < this.minimum) {
      reader.setCursor(start);
      throw CommandException.LONG_TOO_SMALL.createWithContext(reader, result, this.minimum);
    }
    if (result > this.maximum) {
      reader.setCursor(start);
      throw CommandException.LONG_TOO_BIG.createWithContext(reader, result, this.maximum);
    }
    return result;
  }

  @Override
  public int hashCode() {
    return 31 * Long.hashCode(this.minimum) + Long.hashCode(this.maximum);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof LongArgumentType)) {
      return false;
    }
    final var that = (LongArgumentType) obj;
    return this.maximum == that.maximum &&
      this.minimum == that.minimum;
  }

  @Override
  public String toString() {
    if (this.minimum == Long.MIN_VALUE && this.maximum == Long.MAX_VALUE) {
      return "longArg()";
    }
    if (this.maximum == Long.MAX_VALUE) {
      return "longArg(" + this.minimum + ")";
    }
    return "longArg(" + this.minimum + ", " + this.maximum + ")";
  }
}
