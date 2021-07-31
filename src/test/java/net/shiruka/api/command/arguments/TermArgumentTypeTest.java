package net.shiruka.api.command.arguments;

import static net.shiruka.api.command.CommandException.TERM_INVALID;
import static net.shiruka.api.command.Commands.termArg;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;

final class TermArgumentTypeTest {

  @Test
  void testParseTerm() throws Exception {
    final var reader = mock(TextReader.class);
    when(reader.readUnquotedText()).thenReturn("hello");
    assertThat(termArg("hello", "world").parse(reader), equalTo("hello"));
    verify(reader).readUnquotedText();
  }

  @Test
  void testParseTermFails() {
    final var reader = mock(TextReader.class);
    when(reader.readUnquotedText()).thenReturn("foo");
    try {
      termArg("hello", "world").parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(TERM_INVALID));
    }
  }
}
