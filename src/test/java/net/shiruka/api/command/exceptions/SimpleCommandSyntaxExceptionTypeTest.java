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
