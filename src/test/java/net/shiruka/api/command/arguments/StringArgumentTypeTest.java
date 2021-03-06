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

import static net.shiruka.api.command.Commands.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.mockito.Mockito.*;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;

final class StringArgumentTypeTest {

  @Test
  void testEscapeIfRequired_escapes() {
    assertThat(StringArgumentType.escapeIfRequired("\\"), is(equalTo("\"\\\\\"")));
  }

  @Test
  void testEscapeIfRequired_multipleWords() {
    assertThat(StringArgumentType.escapeIfRequired("hello world"), is(equalTo("\"hello world\"")));
  }

  @Test
  void testEscapeIfRequired_notRequired() {
    assertThat(StringArgumentType.escapeIfRequired("hello"), is(equalTo("hello")));
    assertThat(StringArgumentType.escapeIfRequired(""), is(equalTo("")));
  }

  @Test
  void testEscapeIfRequired_quote() {
    assertThat(StringArgumentType.escapeIfRequired("hello \"world\"!"), is(equalTo("\"hello \\\"world\\\"!\"")));
  }

  @Test
  void testEscapeIfRequired_singleQuote() {
    assertThat(StringArgumentType.escapeIfRequired("\""), is(equalTo("\"\\\"\"")));
  }

  @Test
  void testParseGreedyString() throws CommandSyntaxException {
    final var reader = new TextReader("Hello world! This is a test.");
    assertThat(greedyArg().parse(reader), equalTo("Hello world! This is a test."));
    assertThat(reader.canRead(), is(false));
  }

  @Test
  void testParseString() throws CommandSyntaxException {
    final var reader = mock(TextReader.class);
    when(reader.readText()).thenReturn("hello world");
    assertThat(stringArg().parse(reader), equalTo("hello world"));
    verify(reader).readText();
  }

  @Test
  void testParseWord() throws CommandSyntaxException {
    final var reader = mock(TextReader.class);
    when(reader.readUnquotedText()).thenReturn("hello");
    assertThat(wordArg().parse(reader), equalTo("hello"));
    verify(reader).readUnquotedText();
  }

  @Test
  void testToString() {
    assertThat(stringArg(), hasToString("string()"));
  }
}
