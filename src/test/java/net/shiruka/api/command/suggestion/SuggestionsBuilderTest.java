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

package net.shiruka.api.command.suggestion;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.common.collect.Lists;
import java.util.stream.Collectors;
import net.shiruka.api.command.TextRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class SuggestionsBuilderTest {

  private Suggestions.Builder builder;

  @Test
  void restart() {
    this.builder.suggest("won't be included in restart");
    final var other = this.builder.restart();
    assertThat(other, is(not(this.builder)));
    assertThat(other.getInput(), equalTo(this.builder.getInput()));
    assertThat(other.getStart(), is(this.builder.getStart()));
    assertThat(other.getRemaining(), equalTo(this.builder.getRemaining()));
  }

  @BeforeEach
  void setUp() {
    this.builder = Suggestions.builder("Hello w", 6);
  }

  @Test
  void sort_alphabetical() {
    final var result = this.builder.suggest("2").suggest("4").suggest("6").suggest("8").suggest("30").suggest("32").build();
    final var actual = result.getSuggestionList().stream().map(Suggestion::getText).collect(Collectors.toList());
    assertThat(actual, equalTo(Lists.newArrayList("2", "30", "32", "4", "6", "8")));
  }

  @Test
  void sort_mixed() {
    final var result = this.builder.suggest("11").suggest("22").suggest("33").suggest("a").suggest("b").suggest("c").suggest(2).suggest(4).suggest(6).suggest(8).suggest(30).suggest(32).suggest("3a").suggest("a3").build();
    final var actual = result.getSuggestionList().stream().map(Suggestion::getText).collect(Collectors.toList());
    assertThat(actual, equalTo(Lists.newArrayList("11", "2", "22", "33", "3a", "4", "6", "8", "30", "32", "a", "a3", "b", "c")));
  }

  @Test
  void sort_numerical() {
    final var result = this.builder.suggest(2).suggest(4).suggest(6).suggest(8).suggest(30).suggest(32).build();
    final var actual = result.getSuggestionList().stream().map(Suggestion::getText).collect(Collectors.toList());
    assertThat(actual, equalTo(Lists.newArrayList("2", "4", "6", "8", "30", "32")));
  }

  @Test
  void suggest_appends() {
    final var result = this.builder.suggest("world!").build();
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(6, 7), "world!"))));
    assertThat(result.getRange(), equalTo(TextRange.between(6, 7)));
    assertThat(result.isEmpty(), is(false));
  }

  @Test
  void suggest_multiple() {
    final var result = this.builder.suggest("world!").suggest("everybody").suggest("weekend").build();
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(6, 7), "everybody"), new Suggestion(TextRange.between(6, 7), "weekend"), new Suggestion(TextRange.between(6, 7), "world!"))));
    assertThat(result.getRange(), equalTo(TextRange.between(6, 7)));
    assertThat(result.isEmpty(), is(false));
  }

  @Test
  void suggest_noop() {
    final var result = this.builder.suggest("w").build();
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList()));
    assertThat(result.isEmpty(), is(true));
  }

  @Test
  void suggest_replaces() {
    final var result = this.builder.suggest("everybody").build();
    assertThat(result.getSuggestionList(), equalTo(Lists.newArrayList(new Suggestion(TextRange.between(6, 7), "everybody"))));
    assertThat(result.getRange(), equalTo(TextRange.between(6, 7)));
    assertThat(result.isEmpty(), is(false));
  }
}
