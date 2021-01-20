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

import static net.shiruka.api.command.CommandException.LITERAL_INCORRECT;
import static net.shiruka.api.command.Commands.literal;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import com.google.common.collect.Lists;
import com.google.common.testing.EqualsTester;
import net.shiruka.api.command.*;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.suggestion.Suggestion;
import net.shiruka.api.command.suggestion.Suggestions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class LiteralCommandNodeTest extends AbstractCommandNodeTest {

  private CommandContextBuilder contextBuilder;

  private LiteralNode node;

  @Override
  protected CommandNode getCommandNode() {
    return this.node;
  }

  @BeforeEach
  void setUp() {
    this.node = literal("foo").build();
    this.contextBuilder = new CommandContextBuilder(0, new RootNode(), new SimpleCommandSender());
  }

  @Test
  void testEquals() {
    final var command = mock(Command.class);
    new EqualsTester()
      .addEqualityGroup(
        literal("foo")
          .build(),
        literal("foo")
          .build())
      .addEqualityGroup(
        literal("bar")
          .executes(command)
          .build(),
        literal("bar")
          .executes(command)
          .build())
      .addEqualityGroup(
        literal("bar")
          .build(),
        literal("bar")
          .build())
      .addEqualityGroup(
        literal("foo")
          .then(literal("bar"))
          .build(),
        literal("foo")
          .then(literal("bar"))
          .build())
      .testEquals();
  }

  @Test
  void testParse() throws Exception {
    final var reader = new TextReader("foo bar");
    this.node.parse(reader, this.contextBuilder);
    assertThat(reader.getRemaining(), equalTo(" bar"));
  }

  @Test
  void testParseExact() throws Exception {
    final var reader = new TextReader("foo");
    this.node.parse(reader, this.contextBuilder);
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void testParseInvalid() {
    final var reader = new TextReader("bar");
    try {
      this.node.parse(reader, this.contextBuilder);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(LITERAL_INCORRECT));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testParseSimilar() {
    final var reader = new TextReader("foobar");
    try {
      this.node.parse(reader, this.contextBuilder);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(LITERAL_INCORRECT));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testSuggestions() throws Exception {
    final var empty = this.node.suggestions(this.contextBuilder.build(""), Suggestions.builder("", 0)).join();
    assertThat(empty.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.at(0), "foo"))));
    final var foo = this.node.suggestions(this.contextBuilder.build("foo"), Suggestions.builder("foo", 0)).join();
    assertThat(foo.isEmpty(), is(true));
    final var food = this.node.suggestions(this.contextBuilder.build("food"), Suggestions.builder("food", 0)).join();
    assertThat(food.isEmpty(), is(true));
    final var b = this.node.suggestions(this.contextBuilder.build("b"), Suggestions.builder("b", 0)).join();
    assertThat(food.isEmpty(), is(true));
  }

  @Test
  void testUsage() {
    assertThat(this.node.getUsage(), is("foo"));
  }
}
