package net.shiruka.api.command.arguments;

import static net.shiruka.api.command.CommandException.BYTE_TOO_BIG;
import static net.shiruka.api.command.CommandException.BYTE_TOO_SMALL;
import static net.shiruka.api.command.Commands.byteArg;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
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
final class ByteArgumentTypeTest {

  @Test
  void parse() throws Exception {
    final var reader = new TextReader("15");
    assertThat(byteArg().parse(reader), is((byte) 15));
    assertThat(reader.canRead(), is(false));
  }

  @Test
  void parse_tooBig() {
    final var reader = new TextReader("5");
    try {
      byteArg((byte) -100, (byte) 0).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(BYTE_TOO_BIG));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void parse_tooSmall() {
    final var reader = new TextReader("-5");
    try {
      byteArg((byte) 0, (byte) 100).parse(reader);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(BYTE_TOO_SMALL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testEquals() {
    new EqualsTester()
      .addEqualityGroup(byteArg(), byteArg())
      .addEqualityGroup(byteArg((byte) -100, (byte) 100), byteArg((byte) -100, (byte) 100))
      .addEqualityGroup(byteArg((byte) -100, (byte) 50), byteArg((byte) -100, (byte) 50))
      .addEqualityGroup(byteArg((byte) -50, (byte) 100), byteArg((byte) -50, (byte) 100))
      .testEquals();
  }

  @Test
  void testToString() {
    assertThat(byteArg(), hasToString("byteArg()"));
    assertThat(byteArg((byte) -100), hasToString("byteArg(-100)"));
    assertThat(byteArg((byte) -100, (byte) 100), hasToString("byteArg(-100, 100)"));
    assertThat(byteArg(Byte.MIN_VALUE, (byte) 100), hasToString("byteArg(-128, 100)"));
  }
}
