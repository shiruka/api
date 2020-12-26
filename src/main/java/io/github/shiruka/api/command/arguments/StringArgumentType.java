// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package io.github.shiruka.api.command.arguments;

import io.github.shiruka.api.command.ArgumentType;
import io.github.shiruka.api.command.CommandException;
import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.context.CommandContext;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import io.github.shiruka.api.command.suggestion.Suggestions;
import java.util.*;
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
  private final Set<String> options;

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
  public StringArgumentType(@NotNull final Set<String> options, @NotNull final StringType type) {
    this.options = Collections.unmodifiableSet(options);
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
    SINGLE_WORD("word", "words_with_underscores"),
    QUOTABLE_PHRASE("\"quoted phrase\"", "word", "\"\""),
    GREEDY_PHRASE("word", "words with spaces", "\"and symbols\""),
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
