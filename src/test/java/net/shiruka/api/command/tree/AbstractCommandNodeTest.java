/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
