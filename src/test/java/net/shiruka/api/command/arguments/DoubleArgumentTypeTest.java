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
