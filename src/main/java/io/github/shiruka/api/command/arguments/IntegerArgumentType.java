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
 * a simple integer argument type implementation for {@link ArgumentType}.
 */
public final class IntegerArgumentType implements ArgumentType<Integer> {

  /**
   * the examples.
   */
  private static final Collection<String> EXAMPLES = List.of("0", "123", "-123", "+123");

  /**
   * the maximum.
   */
  private final int maximum;

  /**
   * the minimum.
   */
  private final int minimum;

  /**
   * ctor.
   *
   * @param minimum the minimum.
   * @param maximum the maxmum.
   */
  IntegerArgumentType(final int minimum, final int maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return IntegerArgumentType.EXAMPLES;
  }

  @NotNull
  @Override
  public Integer parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var result = reader.readInt();
    if (result < this.minimum) {
      reader.setCursor(start);
      throw CommandException.INTEGER_TOO_SMALL.createWithContext(reader, result, this.minimum);
    }
    if (result > this.maximum) {
      reader.setCursor(start);
      throw CommandException.INTEGER_TOO_BIG.createWithContext(reader, result, this.maximum);
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
    if (!(obj instanceof IntegerArgumentType)) {
      return false;
    }
    final var that = (IntegerArgumentType) obj;
    return this.maximum == that.maximum &&
      this.minimum == that.minimum;
  }

  @Override
  public String toString() {
    if (this.minimum == Integer.MIN_VALUE && this.maximum == Integer.MAX_VALUE) {
      return "integer()";
    }
    if (this.maximum == Integer.MAX_VALUE) {
      return "integer(" + this.minimum + ")";
    }
    return "integer(" + this.minimum + ", " + this.maximum + ")";
  }
}
