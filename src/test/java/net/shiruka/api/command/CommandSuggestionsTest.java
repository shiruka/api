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

package net.shiruka.api.command;

import static net.shiruka.api.command.Commands.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.shiruka.api.command.sender.CommandSender;
import net.shiruka.api.command.suggestion.Suggestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
final class CommandSuggestionsTest {

  @Mock
  private CommandSender source;

  private CommandDispatcher subject;

  private static TextReader inputWithOffset(final String input, final int offset) {
    final var result = new TextReader(input);
    result.setCursor(offset);
    return result;
  }

  @Test
  void getCompletionSuggestions_execute_simulation() {
    final var execute = literal("execute").build();
    this.subject.register(execute);
    this.subject.register(literal("execute")
      .then(literal("as")
        .then(arg("name", wordArg())
          .redirect(execute)))
      .then(literal("store")
        .then(arg("name", wordArg())
          .redirect(execute)))
      .then(literal("run")
        .executes(c -> CommandResult.empty())));
    final var parse = this.subject.parse("execute as Dinnerbone as", this.source);
    final var result = CommandDispatcher.getCompletionSuggestions(parse).join();
    assertThat(result.isEmpty(), is(true));
  }

  @Test
  void getCompletionSuggestions_execute_simulation_partial() {
    final var execute = literal("execute").build();
    this.subject.register(execute);
    this.subject.register(literal("execute")
      .then(literal("as")
        .then(literal("bar")
          .redirect(execute))
        .then(literal("baz")
          .redirect(execute)))
      .then(literal("store")
        .then(arg("name", wordArg())
          .redirect(execute)))
      .then(literal("run")
        .executes(c -> CommandResult.empty())));
    final var parse = this.subject.parse("execute as bar as ", this.source);
    final var result = CommandDispatcher.getCompletionSuggestions(parse).join();
    assertThat(result.getRange(), equalTo(TextRange.at(18)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.at(18), "bar"), new Suggestion(TextRange.at(18), "baz"))));
  }

  @Test
  void getCompletionSuggestions_movingCursor_redirect() {
    final var actualOne = literal("actual_one")
      .then(literal("faz"))
      .then(literal("fbz"))
      .then(literal("gaz"))
      .build();
    this.subject.register(actualOne);
    final var actualTwo = literal("actual_two").build();
    this.subject.register(actualTwo);
    this.subject.register(literal("redirect_one").redirect(actualOne));
    this.subject.register(literal("redirect_two").redirect(actualOne));
    this.testSuggestions("redirect_one faz ", 0, TextRange.at(0), "actual_one", "actual_two", "redirect_one", "redirect_two");
    this.testSuggestions("redirect_one faz ", 9, TextRange.between(0, 9), "redirect_one", "redirect_two");
    this.testSuggestions("redirect_one faz ", 10, TextRange.between(0, 10), "redirect_one");
    this.testSuggestions("redirect_one faz ", 12, TextRange.at(0));
    this.testSuggestions("redirect_one faz ", 13, TextRange.at(13), "faz", "fbz", "gaz");
    this.testSuggestions("redirect_one faz ", 14, TextRange.between(13, 14), "faz", "fbz");
    this.testSuggestions("redirect_one faz ", 15, TextRange.between(13, 15), "faz");
    this.testSuggestions("redirect_one faz ", 16, TextRange.at(0));
    this.testSuggestions("redirect_one faz ", 17, TextRange.at(0));
  }

  @Test
  void getCompletionSuggestions_movingCursor_subCommands() {
    this.subject.register(literal("parent_one")
      .then(literal("faz"))
      .then(literal("fbz"))
      .then(literal("gaz")));
    this.subject.register(literal("parent_two"));
    this.testSuggestions("parent_one faz ", 0, TextRange.at(0), "parent_one", "parent_two");
    this.testSuggestions("parent_one faz ", 1, TextRange.between(0, 1), "parent_one", "parent_two");
    this.testSuggestions("parent_one faz ", 7, TextRange.between(0, 7), "parent_one", "parent_two");
    this.testSuggestions("parent_one faz ", 8, TextRange.between(0, 8), "parent_one");
    this.testSuggestions("parent_one faz ", 10, TextRange.at(0));
    this.testSuggestions("parent_one faz ", 11, TextRange.at(11), "faz", "fbz", "gaz");
    this.testSuggestions("parent_one faz ", 12, TextRange.between(11, 12), "faz", "fbz");
    this.testSuggestions("parent_one faz ", 13, TextRange.between(11, 13), "faz");
    this.testSuggestions("parent_one faz ", 14, TextRange.at(0));
    this.testSuggestions("parent_one faz ", 15, TextRange.at(0));
  }

  @Test
  void getCompletionSuggestions_redirect() {
    final var actual = literal("actual").then(literal("sub")).build();
    this.subject.register(actual);
    this.subject.register(literal("redirect").redirect(actual));
    final var parse = this.subject.parse("redirect ", this.source);
    final var result = CommandDispatcher.getCompletionSuggestions(parse).join();
    assertThat(result.getRange(), equalTo(TextRange.at(9)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.at(9), "sub"))));
  }

  @Test
  void getCompletionSuggestions_redirectPartial() {
    final var actual = literal("actual").then(literal("sub")).build();
    this.subject.register(actual);
    this.subject.register(literal("redirect").redirect(actual));
    final var parse = this.subject.parse("redirect s", this.source);
    final var result = CommandDispatcher.getCompletionSuggestions(parse).join();
    assertThat(result.getRange(), equalTo(TextRange.between(9, 10)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(9, 10), "sub"))));
  }

  @Test
  void getCompletionSuggestions_redirectPartial_withInputOffset() {
    final var actual = literal("actual").then(literal("sub")).build();
    this.subject.register(actual);
    this.subject.register(literal("redirect").redirect(actual));
    final var parse = this.subject.parse(CommandSuggestionsTest.inputWithOffset("/redirect s", 1), this.source);
    final var result = CommandDispatcher.getCompletionSuggestions(parse).join();
    assertThat(result.getRange(), equalTo(TextRange.between(10, 11)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(10, 11), "sub"))));
  }

  @Test
  void getCompletionSuggestions_redirect_lots() {
    final var loop = literal("redirect").build();
    this.subject.register(loop);
    this.subject.register(literal("redirect")
      .then(literal("loop")
        .then(arg("loop", integerArg())
          .redirect(loop))));
    final var result = CommandDispatcher.getCompletionSuggestions(this.subject.parse("redirect loop 1 loop 02 loop 003 ", this.source)).join();
    assertThat(result.getRange(), equalTo(TextRange.at(33)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.at(33), "loop"))));
  }

  @Test
  void getCompletionSuggestions_rootCommands() {
    this.subject.register(literal("foo"));
    this.subject.register(literal("bar"));
    this.subject.register(literal("baz"));
    final var result = CommandDispatcher.getCompletionSuggestions(this.subject.parse("", this.source)).join();
    assertThat(result.getRange(), equalTo(TextRange.at(0)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.at(0), "bar"), new Suggestion(TextRange.at(0), "baz"), new Suggestion(TextRange.at(0), "foo"))));
  }

  @Test
  void getCompletionSuggestions_rootCommands_partial() {
    this.subject.register(literal("foo"));
    this.subject.register(literal("bar"));
    this.subject.register(literal("baz"));
    final var result = CommandDispatcher.getCompletionSuggestions(this.subject.parse("b", this.source)).join();
    assertThat(result.getRange(), equalTo(TextRange.between(0, 1)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(0, 1), "bar"), new Suggestion(TextRange.between(0, 1), "baz"))));
  }

  @Test
  void getCompletionSuggestions_rootCommands_partial_withInputOffset() {
    this.subject.register(literal("foo"));
    this.subject.register(literal("bar"));
    this.subject.register(literal("baz"));
    final var result = CommandDispatcher.getCompletionSuggestions(this.subject.parse(CommandSuggestionsTest.inputWithOffset("Zb", 1), this.source)).join();
    assertThat(result.getRange(), equalTo(TextRange.between(1, 2)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(1, 2), "bar"), new Suggestion(TextRange.between(1, 2), "baz"))));
  }

  @Test
  void getCompletionSuggestions_rootCommands_withInputOffset() {
    this.subject.register(literal("foo"));
    this.subject.register(literal("bar"));
    this.subject.register(literal("baz"));
    final var result = CommandDispatcher.getCompletionSuggestions(this.subject.parse(CommandSuggestionsTest.inputWithOffset("OOO", 3), this.source)).join();
    assertThat(result.getRange(), equalTo(TextRange.at(3)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.at(3), "bar"), new Suggestion(TextRange.at(3), "baz"), new Suggestion(TextRange.at(3), "foo"))));
  }

  @Test
  void getCompletionSuggestions_subCommands() {
    this.subject.register(literal("parent")
      .then(literal("foo"))
      .then(literal("bar"))
      .then(literal("baz")));
    final var result = CommandDispatcher.getCompletionSuggestions(this.subject.parse("parent ", this.source)).join();
    assertThat(result.getRange(), equalTo(TextRange.at(7)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.at(7), "bar"), new Suggestion(TextRange.at(7), "baz"), new Suggestion(TextRange.at(7), "foo"))));
  }

  @Test
  void getCompletionSuggestions_subCommands_partial() {
    this.subject.register(literal("parent")
      .then(literal("foo"))
      .then(literal("bar"))
      .then(literal("baz")));
    final var parse = this.subject.parse("parent b", this.source);
    final var result = CommandDispatcher.getCompletionSuggestions(parse).join();
    assertThat(result.getRange(), equalTo(TextRange.between(7, 8)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(7, 8), "bar"), new Suggestion(TextRange.between(7, 8), "baz"))));
  }

  @Test
  void getCompletionSuggestions_subCommands_partial_withInputOffset() {
    this.subject.register(literal("parent")
      .then(literal("foo"))
      .then(literal("bar"))
      .then(literal("baz")));
    final var parse = this.subject.parse(CommandSuggestionsTest.inputWithOffset("junk parent b", 5), this.source);
    final var result = CommandDispatcher.getCompletionSuggestions(parse).join();
    assertThat(result.getRange(), equalTo(TextRange.between(12, 13)));
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(12, 13), "bar"), new Suggestion(TextRange.between(12, 13), "baz"))));
  }

  @BeforeEach
  void setUp() {
    this.subject = new CommandDispatcher();
  }

  private void testSuggestions(final String contents, final int cursor, final TextRange range, final String... suggestions) {
    final var result = CommandDispatcher.getCompletionSuggestions(this.subject.parse(contents, this.source), cursor).join();
    assertThat(result.getRange(), equalTo(range));
    final var expected = new ArrayList<Suggestion>();
    for (final String suggestion : suggestions) {
      expected.add(new Suggestion(range, suggestion));
    }
    assertThat(result.getSuggestionList(), equalTo(expected));
  }
}
