/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
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

import io.github.shiruka.api.command.context.CommandContext;
import io.github.shiruka.api.command.CommandResult;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.suggestion.Suggestions;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
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
   * @param fork the forks.
   * @param modifier the modifier.
   * @param redirect the redirect.
   * @param requirements the requirement.
   * @param command the command.
   * @param literal the literal.
   */
  public LiteralNode(final boolean fork, @Nullable final Function<CommandContext, Collection<CommandSender>> modifier,
                     @Nullable final CommandNode redirect, @NotNull final Set<Predicate<CommandSender>> requirements,
                     @Nullable final Function<CommandContext, CommandResult> command,
                     @NotNull final String literal) {
    super(fork, modifier, redirect, requirements, command);
    this.literal = literal;
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

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder) {
    if (this.literal.toLowerCase(Locale.ROOT).startsWith(builder.getRemaining().toLowerCase(Locale.ROOT))) {
      return builder.suggest(this.literal).buildFuture();
    }
    return Suggestions.empty();
  }

  @NotNull
  @Override
  public String key() {
    return this.literal;
  }

  @NotNull
  @Override
  public String name() {
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
    if (!reader.getText().substring(start, end).equals(this.literal)) {
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
