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

package net.shiruka.api.command.exceptions;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import net.shiruka.api.command.CommandException;
import net.shiruka.api.command.TextReader;
import org.junit.jupiter.api.Test;

@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
final class SimpleCommandSyntaxExceptionTypeTest {

  @Test
  void createWithContext() {
    final var type = new CeSimple("error");
    final var reader = new TextReader("Foo bar");
    reader.setCursor(5);
    final var exception = type.createWithContext(reader);
    assertThat(exception.getType(), is(type));
    assertThat(exception.getInput(), is("Foo bar"));
    assertThat(exception.getCursor(), is(5));
  }

  @Test
  void getContext_long() {
    final var exception = new CommandSyntaxException(20, "Hello world! This has an error in it. Oh dear!", "error", mock(CommandException.class));
    assertThat(exception.getContext(), equalTo("...d! This ha<--[HERE]"));
  }

  @Test
  void getContext_none() {
    final var exception = new CommandSyntaxException("error", mock(CommandException.class));
    assertThat(exception.getContext(), is(nullValue()));
  }

  @Test
  void getContext_short() {
    final var exception = new CommandSyntaxException(5, "Hello world!", "error", mock(CommandException.class));
    assertThat(exception.getContext(), equalTo("Hello<--[HERE]"));
  }
}
