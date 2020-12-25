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
 * a simple float argument type implementation for {@link ArgumentType}.
 */
public final class FloatArgumentType implements ArgumentType<Float> {

  /**
   * the examples.
   */
  private static final Collection<String> EXAMPLES = List.of("0", "1.2", ".5", "-1", "+1", "-.5", "-1234.56",
    "1e123", "1E123", "1.2e3", "1.2e-3", "-123.456e-7");

  /**
   * the maximum.
   */
  private final float maximum;

  /**
   * the minimum.
   */
  private final float minimum;

  /**
   * ctor.
   *
   * @param minimum the minimum.
   * @param maximum the maximum.
   */
  FloatArgumentType(final float minimum, final float maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return FloatArgumentType.EXAMPLES;
  }

  @NotNull
  @Override
  public Float parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var result = reader.readFloat();
    if (result < this.minimum) {
      reader.setCursor(start);
      throw CommandException.FLOAT_TOO_SMALL.createWithContext(reader, result, this.minimum);
    }
    if (result > this.maximum) {
      reader.setCursor(start);
      throw CommandException.FLOAT_TOO_BIG.createWithContext(reader, result, this.maximum);
    }
    return result;
  }

  @Override
  public int hashCode() {
    return (int) (31 * this.minimum + this.maximum);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof FloatArgumentType)) {
      return false;
    }
    final var that = (FloatArgumentType) obj;
    return this.maximum == that.maximum &&
      this.minimum == that.minimum;
  }

  @Override
  public String toString() {
    if (this.minimum == -Float.MAX_VALUE && this.maximum == Float.MAX_VALUE) {
      return "float()";
    }
    if (this.maximum == Float.MAX_VALUE) {
      return "float(" + this.minimum + ")";
    }
    return "float(" + this.minimum + ", " + this.maximum + ")";
  }
}
