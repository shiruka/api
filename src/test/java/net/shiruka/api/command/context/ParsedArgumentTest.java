package net.shiruka.api.command.context;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.TextReader;
import org.junit.jupiter.api.Test;

final class ParsedArgumentTest {

  @Test
  void getRaw() {
    final var reader = new TextReader("0123456789");
    final var argument = new ParsedArgument<>(2, 5, "");
    assertThat(argument.getRange().get(reader), equalTo("234"));
  }

  @Test
  void testEquals() {
    new EqualsTester()
      .addEqualityGroup(new ParsedArgument<>(0, 3, "bar"), new ParsedArgument<>(0, 3, "bar"))
      .addEqualityGroup(new ParsedArgument<>(3, 6, "baz"), new ParsedArgument<>(3, 6, "baz"))
      .addEqualityGroup(new ParsedArgument<>(6, 9, "baz"), new ParsedArgument<>(6, 9, "baz"))
      .testEquals();
  }
}