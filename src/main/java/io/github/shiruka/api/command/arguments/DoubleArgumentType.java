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

package io.github.shiruka.api.command.arguments;

import io.github.shiruka.api.command.ArgumentType;
import io.github.shiruka.api.command.CommandException;
import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * a simple double argument type implementation for {@link ArgumentType}.
 */
public final class DoubleArgumentType implements ArgumentType<Double> {

  /**
   * the examples.
   */
  private static final Collection<String> EXAMPLES = List.of("0", "1.2", ".5", "-1", "+1", "-.5", "-1234.56",
    "1e123", "1E123", "1.2e3", "1.2e-3", "-123.456e-7");

  /**
   * the maximum.
   */
  private final double maximum;

  /**
   * the minimum.
   */
  private final double minimum;

  /**
   * ctor.
   *
   * @param minimum the minimum.
   * @param maximum the maximum.
   */
  public DoubleArgumentType(final double minimum, final double maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return DoubleArgumentType.EXAMPLES;
  }

  @NotNull
  @Override
  public Double parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var result = reader.readDouble();
    if (result < this.minimum) {
      reader.setCursor(start);
      throw CommandException.DOUBLE_TOO_SMALL.createWithContext(reader, result, this.minimum);
    }
    if (result > this.maximum) {
      reader.setCursor(start);
      throw CommandException.DOUBLE_TOO_BIG.createWithContext(reader, result, this.maximum);
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
    if (!(obj instanceof DoubleArgumentType)) {
      return false;
    }
    final var that = (DoubleArgumentType) obj;
    return this.maximum == that.maximum &&
      this.minimum == that.minimum;
  }

  @Override
  public String toString() {
    if (this.minimum == -Double.MAX_VALUE && this.maximum == Double.MAX_VALUE) {
      return "double()";
    }
    if (this.maximum == Double.MAX_VALUE) {
      return "double(" + this.minimum + ")";
    }
    return "double(" + this.minimum + ", " + this.maximum + ")";
  }
}
