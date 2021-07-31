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
