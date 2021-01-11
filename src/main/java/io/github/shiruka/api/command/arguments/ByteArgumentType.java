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
