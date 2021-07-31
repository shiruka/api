package net.shiruka.api.command.builder;

import static net.shiruka.api.command.Commands.arg;
import static net.shiruka.api.command.Commands.integerArg;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import net.shiruka.api.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

final class LiteralArgumentBuilderTest {

  private LiteralBuilder builder;

  @Mock
  private
  Command command;

  @BeforeEach
  void setUp() {
    this.builder = new LiteralBuilder("foo");
  }

  @Test
  void testBuild() {
    final var node = this.builder.build();
    assertThat(node.getLiteral(), is("foo"));
    assertThat(node.isDefaultNode(), is(false));
  }

  @Test
  void testBuildWithChildren() {
    this.builder.then(arg("bar", integerArg()));
    this.builder.then(arg("baz", integerArg()));
    final var node = this.builder.build();
    assertThat(node.getChildren(), hasSize(2));
  }

  @Test
  void testBuildWithExecutor() {
    final var node = this.builder.executes(this.command).build();
    assertThat(node.getLiteral(), is("foo"));
    assertThat(node.getCommand(), is(this.command));
  }
}