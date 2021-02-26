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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import net.shiruka.api.command.CommandNode;
import net.shiruka.api.command.SimpleCommandSender;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.suggestion.Suggestions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class RootNodeTest extends AbstractCommandNodeTest {

  private RootNode node;

  @Override
  protected CommandNode getCommandNode() {
    return this.node;
  }

  @BeforeEach
  void setUp() {
    this.node = new RootNode();
  }

  @Test
  void testAddChildNoRoot() {
    assertThrows(UnsupportedOperationException.class, () -> this.node.addChild(new RootNode()));
  }

  @Test
  void testParse() throws Exception {
    final var reader = new TextReader("hello world");
    this.node.parse(reader, new CommandContextBuilder(0, new RootNode(), new SimpleCommandSender()));
    assertThat(reader.getCursor(), is(0));
  }

  @Test
  void testSuggestions() throws Exception {
    final var context = mock(CommandContext.class);
    final var result = this.node.suggestions(context, Suggestions.builder("", 0)).join();
    assertThat(result.isEmpty(), is(true));
  }

  @Test
  void testUsage() {
    assertThat(this.node.getSmartUsage(), is(""));
  }
}
