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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import net.shiruka.api.command.TextRange;
import org.junit.jupiter.api.Test;

final class SuggestionTest {

  @Test
  void apply_insertation_end() {
    final var suggestion = new Suggestion(TextRange.at(5), " world!");
    assertThat(suggestion.apply("Hello"), equalTo("Hello world!"));
  }

  @Test
  void apply_insertation_middle() {
    final var suggestion = new Suggestion(TextRange.at(6), "small ");
    assertThat(suggestion.apply("Hello world!"), equalTo("Hello small world!"));
  }

  @Test
  void apply_insertation_start() {
    final var suggestion = new Suggestion(TextRange.at(0), "And so I said: ");
    assertThat(suggestion.apply("Hello world!"), equalTo("And so I said: Hello world!"));
  }

  @Test
  void apply_replacement_end() {
    final var suggestion = new Suggestion(TextRange.between(6, 12), "Creeper!");
    assertThat(suggestion.apply("Hello world!"), equalTo("Hello Creeper!"));
  }

  @Test
  void apply_replacement_everything() {
    final var suggestion = new Suggestion(TextRange.between(0, 12), "Oh dear.");
    assertThat(suggestion.apply("Hello world!"), equalTo("Oh dear."));
  }

  @Test
  void apply_replacement_middle() {
    final var suggestion = new Suggestion(TextRange.between(6, 11), "Alex");
    assertThat(suggestion.apply("Hello world!"), equalTo("Hello Alex!"));
  }

  @Test
  void apply_replacement_start() {
    final var suggestion = new Suggestion(TextRange.between(0, 5), "Goodbye");
    assertThat(suggestion.apply("Hello world!"), equalTo("Goodbye world!"));
  }

  @Test
  void expand_both() {
    final var suggestion = new Suggestion(TextRange.at(11), "minecraft:");
    assertThat(suggestion.expand("give Steve fish_block", TextRange.between(5, 21)), equalTo(new Suggestion(TextRange.between(5, 21), "Steve minecraft:fish_block")));
  }

  @Test
  void expand_left() {
    final var suggestion = new Suggestion(TextRange.at(1), "oo");
    assertThat(suggestion.expand("f", TextRange.between(0, 1)), equalTo(new Suggestion(TextRange.between(0, 1), "foo")));
  }

  @Test
  void expand_replacement() {
    final var suggestion = new Suggestion(TextRange.between(6, 11), "strangers");
    assertThat(suggestion.expand("Hello world!", TextRange.between(0, 12)), equalTo(new Suggestion(TextRange.between(0, 12), "Hello strangers!")));
  }

  @Test
  void expand_right() {
    final var suggestion = new Suggestion(TextRange.at(0), "minecraft:");
    assertThat(suggestion.expand("fish", TextRange.between(0, 4)), equalTo(new Suggestion(TextRange.between(0, 4), "minecraft:fish")));
  }

  @Test
  void expand_unchanged() {
    final var suggestion = new Suggestion(TextRange.at(1), "oo");
    assertThat(suggestion.expand("f", TextRange.at(1)), equalTo(suggestion));
  }
}
