package net.shiruka.api.command.arguments;

import static net.shiruka.api.command.Commands.booleanArg;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import net.shiruka.api.command.TextReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class BoolArgumentTypeTest {

  private BooleanArgumentType type;

  @Test
  void parse() throws Exception {
    final var reader = mock(TextReader.class);
    when(reader.readBoolean()).thenReturn(true);
    final var c = System.currentTimeMillis();
    assertThat(this.type.parse(reader), is(true));
    final var d = System.currentTimeMillis();
    verify(reader).readBoolean();
    final var e = System.currentTimeMillis();
  }

  @BeforeEach
  void setUp() {
    this.type = booleanArg();
  }
}
