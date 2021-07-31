package net.shiruka.api.command.arguments;

import static net.shiruka.api.command.CommandException.SHORT_TOO_BIG;
import static net.shiruka.api.command.CommandException.SHORT_TOO_SMALL;
import static net.shiruka.api.command.Commands.shortArg;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.object.HasToString.hasToString;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
final class ShortArgumentTypeTest {

  @Test
  void parse() throws Exception {
    final var reader = new TextReader("15");
    assertThat(shortArg().parse(reader), is((short) 15));
    assertThat(reader.canRead(), is(false));
  }

  @Test
  void parse_tooBig() {
    final var reader = new TextReader("5");
    try {
      shortArg((short) -100, (short) 0).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(SHORT_TOO_BIG));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void parse_tooSmall() {
    final var reader = new TextReader("-5");
    try {
      shortArg((short) 0, (short) 100).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(SHORT_TOO_SMALL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testEquals() {
    new EqualsTester()
      .addEqualityGroup(shortArg(), shortArg())
      .addEqualityGroup(shortArg((short) -100, (short) 100), shortArg((short) -100, (short) 100))
      .addEqualityGroup(shortArg((short) -100, (short) 50), shortArg((short) -100, (short) 50))
      .addEqualityGroup(shortArg((short) -50, (short) 100), shortArg((short) -50, (short) 100))
      .testEquals();
  }

  @Test
  void testToString() {
    assertThat(shortArg(), hasToString("shortArg()"));
    assertThat(shortArg((short) -100), hasToString("shortArg(-100)"));
    assertThat(shortArg((short) -100, (short) 100), hasToString("shortArg(-100, 100)"));
    assertThat(shortArg(Short.MIN_VALUE, (short) 100), hasToString("shortArg(-32768, 100)"));
  }
}
