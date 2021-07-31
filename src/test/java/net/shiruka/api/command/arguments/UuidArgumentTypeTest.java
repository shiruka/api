package net.shiruka.api.command.arguments;

import static net.shiruka.api.command.Commands.uniqueIdArg;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.UUID;
import net.shiruka.api.command.TextReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
final class UuidArgumentTypeTest {

  @Test
  void parse() throws Exception {
    final var reader = new TextReader("00000000-0000-0000-0000-000000000000");
    assertThat(uniqueIdArg().parse(reader), is(new UUID(0L, 0L)));
  }
}