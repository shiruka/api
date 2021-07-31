package net.shiruka.api.command.arguments;

import static net.shiruka.api.command.CommandException.FLOAT_TOO_BIG;
import static net.shiruka.api.command.CommandException.FLOAT_TOO_SMALL;
import static net.shiruka.api.command.Commands.floatArg;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;

final class FloatArgumentTypeTest {

  @Test
  void parse() throws CommandSyntaxException {
    final var reader = new TextReader("15");
    assertThat(floatArg().parse(reader), is(15f));
    assertThat(reader.canRead(), is(false));
  }

  @Test
  void parse_tooBig() {
    final var reader = new TextReader("5");
    try {
      floatArg(-100, 0).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(FLOAT_TOO_BIG));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void parse_tooSmall() {
    final var reader = new TextReader("-5");
    try {
      floatArg(0, 100).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(FLOAT_TOO_SMALL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testEquals() {
    new EqualsTester()
      .addEqualityGroup(floatArg(), floatArg())
      .addEqualityGroup(floatArg(-100, 100), floatArg(-100, 100))
      .addEqualityGroup(floatArg(-100, 50), floatArg(-100, 50))
      .addEqualityGroup(floatArg(-50, 100), floatArg(-50, 100))
      .testEquals();
  }

  @Test
  void testToString() {
    assertThat(floatArg(), hasToString("float()"));
    assertThat(floatArg(-100), hasToString("float(-100.0)"));
    assertThat(floatArg(-100, 100), hasToString("float(-100.0, 100.0)"));
    assertThat(floatArg(Integer.MIN_VALUE, 100), hasToString("float(-2.14748365E9, 100.0)"));
  }
}
