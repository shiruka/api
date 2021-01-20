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

import static net.shiruka.api.command.CommandException.LONG_TOO_BIG;
import static net.shiruka.api.command.CommandException.LONG_TOO_SMALL;
import static net.shiruka.api.command.Commands.longArg;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;

final class LongArgumentTypeTest {

  @Test
  void parse() throws Exception {
    final var reader = new TextReader("15");
    assertThat(longArg().parse(reader), is(15L));
    assertThat(reader.canRead(), is(false));
  }

  @Test
  void parse_tooBig() {
    final var reader = new TextReader("5");
    try {
      longArg(-100, 0).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(LONG_TOO_BIG));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void parse_tooSmall() {
    final var reader = new TextReader("-5");
    try {
      longArg(0, 100).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(LONG_TOO_SMALL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testEquals() {
    new EqualsTester()
      .addEqualityGroup(longArg(), longArg())
      .addEqualityGroup(longArg(-100, 100), longArg(-100, 100))
      .addEqualityGroup(longArg(-100, 50), longArg(-100, 50))
      .addEqualityGroup(longArg(-50, 100), longArg(-50, 100))
      .testEquals();
  }

  @Test
  void testToString() {
    assertThat(longArg(), hasToString("longArg()"));
    assertThat(longArg(-100), hasToString("longArg(-100)"));
    assertThat(longArg(-100, 100), hasToString("longArg(-100, 100)"));
    assertThat(longArg(Long.MIN_VALUE, 100), hasToString("longArg(-9223372036854775808, 100)"));
  }
}
