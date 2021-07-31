package net.shiruka.api.command.tree;

import static net.shiruka.api.command.Commands.literal;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import net.shiruka.api.command.Command;
import net.shiruka.api.command.CommandNode;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public abstract class AbstractCommandNodeTest {

  @Mock
  private Command command;

  protected abstract CommandNode getCommandNode();

  @Test
  final void testAddChild() {
    final var node = this.getCommandNode();
    node.addChild(literal("child1").build());
    node.addChild(literal("child2").build());
    node.addChild(literal("child1").build());
    assertThat(node.getChildren(), hasSize(2));
  }

  @Test
  final void testAddChildMergesGrandchildren() {
    final var node = this.getCommandNode();
    node.addChild(literal("child").then(
      literal("grandchild1")
    ).build());
    node.addChild(literal("child").then(
      literal("grandchild2")
    ).build());
    assertThat(node.getChildren(), hasSize(1));
    assertThat(node.getChildren().iterator().next().getChildren(), hasSize(2));
  }

  @Test
  final void testAddChildOverwritesCommand() {
    final var node = this.getCommandNode();
    node.addChild(literal("child").build());
    node.addChild(literal("child").executes(this.command).build());
    assertThat(node.getChildren().iterator().next().getCommand(), is(this.command));
  }

  @Test
  final void testAddChildPreservesCommand() {
    final var node = this.getCommandNode();
    node.addChild(literal("child").executes(this.command).build());
    node.addChild(literal("child").build());
    assertThat(node.getChildren().iterator().next().getCommand(), is(this.command));
  }
}
