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

package io.github.shiruka.api.command.tree;

import io.github.shiruka.api.command.*;
import io.github.shiruka.api.command.context.CommandContext;
import io.github.shiruka.api.command.context.CommandContextBuilder;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import io.github.shiruka.api.command.suggestion.Suggestions;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a simple literal implementation for {@link CommandNodeEnvelope}.
 */
public final class LiteralNode extends CommandNodeEnvelope {

  /**
   * the literal.
   */
  @NotNull
  private final String literal;

  /**
   * ctor.
   *
   * @param description the description.
   * @param fork the forks.
   * @param modifier the modifier.
   * @param redirect the redirect.
   * @param requirements the requirement.
   * @param command the command.
   * @param literal the literal.
   */
  public LiteralNode(@Nullable final String description, final boolean fork, @Nullable final RedirectModifier modifier,
                     @Nullable final CommandNode redirect, @NotNull final Set<Requirement> requirements,
                     @Nullable final Command command, @NotNull final String literal) {
    super(description, fork, modifier, redirect, requirements, command);
    this.literal = literal.toLowerCase(Locale.ROOT);
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return Collections.singleton(this.literal);
  }

  @NotNull
  @Override
  public String getUsage() {
    return this.literal;
  }

  @Override
  public boolean isValidInput(@NotNull final String input) {
    return this.parse(new TextReader(input)) > -1;
  }

  @Override
  public void parse(@NotNull final TextReader reader, @NotNull final CommandContextBuilder builder)
    throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var end = this.parse(reader);
    if (end > -1) {
      builder.withNode(this, TextRange.between(start, end));
      return;
    }
    throw CommandException.LITERAL_INCORRECT.createWithContext(reader, this.literal);
  }

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder)
    throws CommandSyntaxException {
    if (this.literal.startsWith(builder.getRemaining().toLowerCase(Locale.ROOT))) {
      return builder.suggest(this.literal).buildFuture();
    }
    return Suggestions.empty();
  }

  @NotNull
  @Override
  public String getKey() {
    return this.literal;
  }

  @NotNull
  @Override
  public String getName() {
    return this.literal;
  }

  /**
   * parses the given reader.
   *
   * @param reader the reader to read.
   *
   * @return {@code -1} if the reader couldn't read.
   */
  private int parse(@NotNull final TextReader reader) {
    final var start = reader.getCursor();
    if (!reader.canRead(this.literal.length())) {
      return -1;
    }
    final var end = start + this.literal.length();
    if (!reader.getText().toLowerCase(Locale.ROOT).substring(start, end).equals(this.literal)) {
      return -1;
    }
    reader.setCursor(end);
    if (!reader.canRead() || reader.peek() == ' ') {
      return end;
    }
    reader.setCursor(start);
    return -1;
  }
}
