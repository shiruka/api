package net.shiruka.api.command.builder;

import static net.shiruka.api.command.Commands.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import net.shiruka.api.command.ArgumentType;
import net.shiruka.api.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
final class RequiredArgumentBuilderTest {

  private RequiredBuilder<Integer> builder;

  @Mock
  private
  Command command;

  @Mock
  private ArgumentType<Integer> type;

  @BeforeEach
  void setUp() {
    this.builder = arg("foo", this.type);
  }

  @Test
  void testBuild() {
    final var node = this.builder.build();
    assertThat(node.getName(), is("foo"));
    assertThat(node.getType(), is(this.type));
    assertThat(node.isDefaultNode(), is(false));
    assertThat(node.getDefaultValue(), is(nullValue()));
  }

  @Test
  void testBuildDefaultNode() {
    final var node = defaultArg("foo", this.type, 42).build();
    assertThat(node.getName(), is("foo"));
    assertThat(node.getType(), is(this.type));
    assertThat(node.isDefaultNode(), is(true));
    assertThat(node.getDefaultValue(), is(42));
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
    assertThat(node.getName(), is("foo"));
    assertThat(node.getType(), is(this.type));
    assertThat(node.getCommand(), is(this.command));
  }
}
