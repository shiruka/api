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

import static net.shiruka.api.command.CommandException.INTEGER_TOO_BIG;
import static net.shiruka.api.command.CommandException.INTEGER_TOO_SMALL;
import static net.shiruka.api.command.Commands.integerArg;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;

final class IntegerArgumentTypeTest {

  @Test
  void parse() throws Exception {
    final var reader = new TextReader("15");
    assertThat(integerArg().parse(reader), is(15));
    assertThat(reader.canRead(), is(false));
  }

  @Test
  void parse_tooBig() {
    final var reader = new TextReader("5");
    try {
      integerArg(-100, 0).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(INTEGER_TOO_BIG));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void parse_tooSmall() {
    final var reader = new TextReader("-5");
    try {
      integerArg(0, 100).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(INTEGER_TOO_SMALL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testEquals() {
    new EqualsTester()
      .addEqualityGroup(integerArg(), integerArg())
      .addEqualityGroup(integerArg(-100, 100), integerArg(-100, 100))
      .addEqualityGroup(integerArg(-100, 50), integerArg(-100, 50))
      .addEqualityGroup(integerArg(-50, 100), integerArg(-50, 100))
      .testEquals();
  }

  @Test
  void testToString() {
    assertThat(integerArg(), hasToString("integer()"));
    assertThat(integerArg(-100), hasToString("integer(-100)"));
    assertThat(integerArg(-100, 100), hasToString("integer(-100, 100)"));
    assertThat(integerArg(Integer.MIN_VALUE, 100), hasToString("integer(-2147483648, 100)"));
  }
}
