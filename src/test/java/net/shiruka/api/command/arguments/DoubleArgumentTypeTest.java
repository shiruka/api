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

import static net.shiruka.api.command.CommandException.DOUBLE_TOO_BIG;
import static net.shiruka.api.command.CommandException.DOUBLE_TOO_SMALL;
import static net.shiruka.api.command.Commands.doubleArg;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;

final class DoubleArgumentTypeTest {

  @Test
  void parse() throws Exception {
    final var reader = new TextReader("15");
    assertThat(doubleArg().parse(reader), is(15.0));
    assertThat(reader.canRead(), is(false));
  }

  @Test
  void parse_tooBig() {
    final var reader = new TextReader("5");
    try {
      doubleArg(-100, 0).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DOUBLE_TOO_BIG));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void parse_tooSmall() {
    final var reader = new TextReader("-5");
    try {
      doubleArg(0, 100).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DOUBLE_TOO_SMALL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testEquals() {
    new EqualsTester()
      .addEqualityGroup(doubleArg(), doubleArg())
      .addEqualityGroup(doubleArg(-100, 100), doubleArg(-100, 100))
      .addEqualityGroup(doubleArg(-100, 50), doubleArg(-100, 50))
      .addEqualityGroup(doubleArg(-50, 100), doubleArg(-50, 100))
      .testEquals();
  }

  @Test
  void testToString() {
    assertThat(doubleArg(), hasToString("double()"));
    assertThat(doubleArg(-100), hasToString("double(-100.0)"));
    assertThat(doubleArg(-100, 100), hasToString("double(-100.0, 100.0)"));
    assertThat(doubleArg(Integer.MIN_VALUE, 100), hasToString("double(-2.147483648E9, 100.0)"));
  }
}
