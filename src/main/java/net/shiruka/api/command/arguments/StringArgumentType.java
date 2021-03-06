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

package net.shiruka.api.command.arguments;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import net.shiruka.api.command.ArgumentType;
import net.shiruka.api.command.CommandException;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.suggestion.Suggestions;
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

  /**
   * puts escape to the given {@code input} if required.
   *
   * @param input the input to put.
   *
   * @return escaped input.
   */
  @NotNull
  public static String escapeIfRequired(@NotNull final String input) {
    for (final var c : input.toCharArray()) {
      if (!TextReader.isAllowedInUnquotedText(c)) {
        return StringArgumentType.escape(input);
      }
    }
    return input;
  }

  /**
   * puts escape to the given {@code input}.
   *
   * @param input the input to put.
   *
   * @return escaped input.
   */
  @NotNull
  private static String escape(@NotNull final String input) {
    final var result = new StringBuilder("\"");
    IntStream.range(0, input.length()).forEach(i -> {
      final var c = input.charAt(i);
      if (c == '\\' || c == '"') {
        result.append('\\');
      }
      result.append(c);
    });
    result.append("\"");
    return result.toString();
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
      final var term = reader.readUnquotedText();
      if (!this.options.contains(term)) {
        throw CommandException.TERM_INVALID.createWithContext(reader, term);
      }
      return term;
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
