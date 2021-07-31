package net.shiruka.api.command.builder;

import static net.shiruka.api.command.Commands.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import net.shiruka.api.command.CommandNode;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class ArgumentBuilderTest {

  private TestableArgumentBuilder builder;

  @BeforeEach
  void setUp() {
    this.builder = new TestableArgumentBuilder();
  }

  @Test
  void testArguments() {
    final var argument = arg("bar", integerArg(10));
    this.builder.then(argument);
    assertThat(this.builder.getArguments(), hasSize(1));
    assertThat(this.builder.getArguments(), hasItem(argument.build()));
  }

  @Test
  void testRedirect() {
    final var target = mock(CommandNode.class);
    this.builder.redirect(target);
    assertThat(this.builder.getRedirect(), is(target));
  }

  @Test
  void testRedirect_withChild() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      final var target = mock(CommandNode.class);
      this.builder.then(literal("foo"));
      this.builder.redirect(target);
    });
  }

  @Test
  void testThen_withDefaultNode() {
    final var child = defaultLiteral("foo").build();
    this.builder.then(child);
    assertThat(this.builder.getDefaultNode(), is(child));
  }

  @Test
  void testThen_withRedirect() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      final var target = mock(CommandNode.class);
      this.builder.redirect(target);
      this.builder.then(literal("foo"));
    });
  }

  @Test
  void testThen_withTwoDefaultNodes() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      this.builder.then(defaultLiteral("foo"));
      this.builder.then(defaultLiteral("bar"));
    });
  }

  private static final class TestableArgumentBuilder extends ArgumentBuilder<TestableArgumentBuilder> {

    @NotNull
    @Override
    public CommandNode build() {
      throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public TestableArgumentBuilder getSelf() {
      return this;
    }
  }
}
