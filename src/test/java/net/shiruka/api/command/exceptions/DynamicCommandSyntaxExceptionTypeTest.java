package net.shiruka.api.command.exceptions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import net.shiruka.api.command.TextReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
final class DynamicCommandSyntaxExceptionTypeTest {

  private CeDynamic type;

  @Test
  void createWithContext() {
    final var reader = new TextReader("Foo bar");
    reader.setCursor(5);
    final var exception = this.type.createWithContext(reader, "World");
    assertThat(exception.getType(), is(this.type));
    assertThat(exception.getInput(), is("Foo bar"));
    assertThat(exception.getCursor(), is(5));
  }

  @BeforeEach
  void setUp() {
    this.type = new CeDynamic(name -> "Hello, " + name + "!");
  }
}