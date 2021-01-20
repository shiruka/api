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

package net.shiruka.api.command.context;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.*;
import net.shiruka.api.command.sender.CommandSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
final class CommandContextTest {

  private CommandContextBuilder builder;

  @Mock
  private CommandDispatcher dispatcher;

  @Mock
  private CommandNode rootNode;

  @Mock
  private CommandSender source;

  @BeforeEach
  void setUp() {
    this.builder = new CommandContextBuilder(0, this.rootNode, this.source);
  }

  @Test
  void testEquals() {
    final var otherSource = new SimpleCommandSender();
    final var command = mock(Command.class);
    final var otherCommand = mock(Command.class);
    final var rootNode = mock(CommandNode.class);
    final var otherRootNode = mock(CommandNode.class);
    final var node = mock(CommandNode.class);
    final var otherNode = mock(CommandNode.class);
    new EqualsTester()
      .addEqualityGroup(new CommandContextBuilder(0, rootNode, this.source).build(""), new CommandContextBuilder(0, rootNode, this.source).build(""))
      .addEqualityGroup(new CommandContextBuilder(0, otherRootNode, this.source).build(""), new CommandContextBuilder(0, otherRootNode, this.source).build(""))
      .addEqualityGroup(new CommandContextBuilder(0, rootNode, otherSource).build(""), new CommandContextBuilder(0, rootNode, otherSource).build(""))
      .addEqualityGroup(new CommandContextBuilder(0, rootNode, this.source).withCommand(command).build(""), new CommandContextBuilder(0, rootNode, this.source).withCommand(command).build(""))
      .addEqualityGroup(new CommandContextBuilder(0, rootNode, this.source).withCommand(otherCommand).build(""), new CommandContextBuilder(0, rootNode, this.source).withCommand(otherCommand).build(""))
      .addEqualityGroup(new CommandContextBuilder(0, rootNode, this.source).withArgument("foo", new ParsedArgument<>(0, 1, 123)).build("123"), new CommandContextBuilder(0, rootNode, this.source).withArgument("foo", new ParsedArgument<>(0, 1, 123)).build("123"))
      .addEqualityGroup(new CommandContextBuilder(0, rootNode, this.source).withNode(node, TextRange.between(0, 3)).withNode(otherNode, TextRange.between(4, 6)).build("123 456"), new CommandContextBuilder(0, rootNode, this.source).withNode(node, TextRange.between(0, 3)).withNode(otherNode, TextRange.between(4, 6)).build("123 456"))
      .addEqualityGroup(new CommandContextBuilder(0, rootNode, this.source).withNode(otherNode, TextRange.between(0, 3)).withNode(node, TextRange.between(4, 6)).build("123 456"), new CommandContextBuilder(0, rootNode, this.source).withNode(otherNode, TextRange.between(0, 3)).withNode(node, TextRange.between(4, 6)).build("123 456"))
      .testEquals();
  }

  @Test
  void testGetArgument() {
    final var context = this.builder.withArgument("foo", new ParsedArgument<>(0, 1, 123)).build("123");
    assertThat(context.getArgument("foo", int.class), is(123));
  }

  @Test
  void testGetArgument_nonexistent() {
    assertThrows(NullPointerException.class, () -> this.builder.build("").getArgument("foo", Object.class));
  }

  @Test
  void testGetArgument_wrongType() {
    assertThrows(IllegalArgumentException.class, () -> {
      final var context = this.builder.withArgument("foo", new ParsedArgument<>(0, 1, 123)).build("123");
      context.getArgument("foo", String.class);
    });
  }

  @Test
  void testRootNode() {
    assertThat(this.builder.build("").getRootNode(), is(this.rootNode));
  }

  @Test
  void testSource() {
    assertThat(this.builder.build("").getSender(), is(this.source));
  }
}
