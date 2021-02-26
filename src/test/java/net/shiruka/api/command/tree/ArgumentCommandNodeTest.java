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

import static net.shiruka.api.command.Commands.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.Command;
import net.shiruka.api.command.CommandNode;
import net.shiruka.api.command.SimpleCommandSender;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.suggestion.Suggestions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class ArgumentCommandNodeTest extends AbstractCommandNodeTest {

  private CommandContextBuilder contextBuilder;

  private ArgumentNode<Integer> node;

  @Override
  protected CommandNode getCommandNode() {
    return this.node;
  }

  @BeforeEach
  void setUp() {
    this.node = arg("foo", integerArg()).build();
    this.contextBuilder = new CommandContextBuilder(0, new RootNode(), new SimpleCommandSender());
  }

  @Test
  void testEquals() {
    final var command = (Command) mock(Command.class);
    new EqualsTester()
      .addEqualityGroup(
        arg("foo", integerArg()).build(),
        arg("foo", integerArg()).build()
      )
      .addEqualityGroup(
        arg("foo", integerArg()).executes(command).build(),
        arg("foo", integerArg()).executes(command).build()
      )
      .addEqualityGroup(
        arg("bar", integerArg(-100, 100)).build(),
        arg("bar", integerArg(-100, 100)).build()
      )
      .addEqualityGroup(
        arg("foo", integerArg(-100, 100)).build(),
        arg("foo", integerArg(-100, 100)).build()
      )
      .addEqualityGroup(
        arg("foo", integerArg()).then(
          arg("bar", integerArg())
        ).build(),
        arg("foo", integerArg()).then(
          arg("bar", integerArg())
        ).build()
      )
      .testEquals();
  }

  @Test
  void testParse() throws Exception {
    final var reader = new TextReader("123 456");
    this.node.parse(reader, this.contextBuilder);
    assertThat(this.contextBuilder.getArguments().containsKey("foo"), is(true));
    assertThat(this.contextBuilder.getArguments().get("foo").getResult(), is(123));
  }

  @Test
  void testParseDefaultNode() throws Exception {
    final var reader = new TextReader("");
    final var node = defaultArg("foo", integerArg(), 42).build();
    node.parse(reader, this.contextBuilder);
    assertThat(this.contextBuilder.getArguments().containsKey("foo"), is(true));
    assertThat(this.contextBuilder.getArguments().get("foo").getResult(), is(42));
  }

  @Test
  void testSuggestions() throws Exception {
    final var result = this.node.suggestions(this.contextBuilder.build(""), Suggestions.builder("", 0)).join();
    assertThat(result.isEmpty(), is(true));
  }

  @Test
  void testUsage() {
    assertThat(this.node.getSmartUsage(), is("<foo>"));
  }
}
