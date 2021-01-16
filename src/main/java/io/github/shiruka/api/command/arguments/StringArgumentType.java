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

package io.github.shiruka.api.command.arguments;

import io.github.shiruka.api.command.ArgumentType;
import io.github.shiruka.api.command.CommandException;
import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.context.CommandContext;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import io.github.shiruka.api.command.suggestion.Suggestions;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;

/**
 * a simple string argument type implementation for {@link ArgumentType}.
 */
public final class StringArgumentType implements ArgumentType<String> {

  /**
   * the options.
   */
  @NotNull
  private final Collection<String> options;

  /**
   * the type.
   */
  @NotNull
  private final StringType type;

  /**
   * ctor.
   *
   * @param options the options.
   * @param type the type.
   */
  public StringArgumentType(@NotNull final Collection<String> options, @NotNull final StringType type) {
    this.options = Collections.unmodifiableCollection(options);
    this.type = type;
  }

  /**
   * ctor.
   *
   * @param type the type.
   */
  public StringArgumentType(@NotNull final StringType type) {
    this(Collections.emptySet(), type);
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    if (this.type != StringType.TERM) {
      return this.type.getExamples();
    }
    return this.options;
  }

  @NotNull
  @Override
  public String parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    if (this.type == StringType.GREEDY_PHRASE) {
      final var text = reader.getRemaining();
      reader.setCursor(reader.getTotalLength());
      return text;
    }
    if (this.type == StringType.SINGLE_WORD) {
      return reader.readUnquotedText();
    }
    if (this.type == StringType.TERM) {
      final String term = reader.readUnquotedText();
      if (!this.options.contains(term)) {
        throw CommandException.TERM_INVALID.createWithContext(reader, term);
      }
    }
    return reader.readText();
  }

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder) {
    if (this.type != StringType.TERM) {
      return Suggestions.empty();
    }
    this.options.stream()
      .filter(s -> s.toLowerCase(Locale.ROOT).startsWith(builder.getRemaining().toLowerCase(Locale.ROOT)))
      .sorted()
      .forEachOrdered(builder::suggest);
    return builder.buildFuture();
  }

  @Override
  public String toString() {
    if (this.type != StringType.TERM) {
      return "string()";
    }
    return "term(" + this.options + ")";
  }

  /**
   * an enum class to determine string types.
   */
  public enum StringType {
    /**
     * the single word.
     */
    SINGLE_WORD("word", "words_with_underscores"),
    /**
     * the quotable phrase.
     */
    QUOTABLE_PHRASE("\"quoted phrase\"", "word", "\"\""),
    /**
     * the greedy phrase.
     */
    GREEDY_PHRASE("word", "words with spaces", "\"and symbols\""),
    /**
     * the term.
     */
    TERM("predefined_token", "red", "green", "blue");

    /**
     * the examples.
     */
    @NotNull
    private final Collection<String> examples;

    /**
     * ctor.
     *
     * @param examples the examples.
     */
    StringType(@NotNull final String... examples) {
      this.examples = List.of(examples);
    }

    /**
     * obtains the examples.
     *
     * @return examples.
     */
    @NotNull
    public Collection<String> getExamples() {
      return this.examples;
    }
  }
}
