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

package net.shiruka.api.command.arguments;

import java.util.Collection;
import java.util.List;
import net.shiruka.api.command.ArgumentType;
import net.shiruka.api.command.CommandException;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
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
  public FloatArgumentType(final float minimum, final float maximum) {
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
