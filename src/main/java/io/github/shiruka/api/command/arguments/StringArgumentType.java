// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package io.github.shiruka.api.command.arguments;

import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * a simple string argument type implementation for {@link ArgumentType}.
 */
public final class StringArgumentType implements ArgumentType<String> {

  /**
   * the type.
   */
  @NotNull
  private final StringType type;

  /**
   * ctor.
   *
   * @param type the type.
   */
  StringArgumentType(@NotNull final StringType type) {
    this.type = type;
  }

  /**
   * puts escape if required.
   *
   * @param input the input to put.
   *
   * @return modified input.
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
   * puts escapes around of the {@code input}.
   *
   * @param input the input to put.
   *
   * @return escaped input.
   */
  @NotNull
  private static String escape(@NotNull final String input) {
    final var result = new StringBuilder("\"");
    for (var i = 0; i < input.length(); i++) {
      final var c = input.charAt(i);
      if (c == '\\' || c == '"') {
        result.append('\\');
      }
      result.append(c);
    }
    result.append("\"");
    return result.toString();
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return this.type.getExamples();
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
    return reader.readText();
  }

  @Override
  public String toString() {
    return "string()";
  }

  /**
   * an enum class to determine string types.
   */
  public enum StringType {
    SINGLE_WORD("word", "words_with_underscores"),
    QUOTABLE_PHRASE("\"quoted phrase\"", "word", "\"\""),
    GREEDY_PHRASE("word", "words with spaces", "\"and symbols\"");

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
