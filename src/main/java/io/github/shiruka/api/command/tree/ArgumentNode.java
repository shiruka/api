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
import io.github.shiruka.api.command.arguments.ArgumentType;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import io.github.shiruka.api.command.suggestion.Suggestions;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a simple dynamic implementation for {@link CommandNodeEnvelope}.
 *
 * @param <V> type of the argument value.
 */
public final class ArgumentNode<V> extends CommandNodeEnvelope {

  /**
   * the usage argument close.
   */
  private static final String USAGE_ARGUMENT_CLOSE = ">";

  /**
   * the usage argument open.
   */
  private static final String USAGE_ARGUMENT_OPEN = "<";

  /**
   * the name.
   */
  @NotNull
  private final String name;

  /**
   * the suggestion override.
   */
  @Nullable
  private final BiFunction<CommandContext, Suggestions.Builder, CompletableFuture<Suggestions>> suggestions;

  /**
   * the type.
   */
  @NotNull
  private final ArgumentType<V> type;

  /**
   * ctor.
   *
   * @param fork the forks.
   * @param modifier the modifier.
   * @param redirect the redirect.
   * @param requirements the requirements.
   * @param command the command.
   * @param name the name.
   * @param suggestions the suggestion override.
   * @param type the type.
   */
  public ArgumentNode(final boolean fork,
                      @Nullable final Function<CommandContext, Collection<CommandSender>> modifier,
                      @Nullable final CommandNode redirect, @NotNull final Set<Predicate<CommandSender>> requirements,
                      @Nullable final Function<CommandContext, CommandResult> command, @NotNull final String name,
                      @Nullable final BiFunction<CommandContext, Suggestions.Builder, CompletableFuture<Suggestions>> suggestions,
                      @NotNull final ArgumentType<V> type) {
    super(fork, modifier, redirect, requirements, command);
    this.name = name;
    this.suggestions = suggestions;
    this.type = type;
  }

  /**
   * ctor.
   *
   * @param command the command.
   * @param fork the forks.
   * @param modifier the modifier.
   * @param redirect the redirect.
   * @param requirements the requirements.
   * @param name the name.
   * @param type the type.
   */
  public ArgumentNode(final boolean fork, @Nullable final Function<CommandContext, Collection<CommandSender>> modifier,
                      @Nullable final CommandNode redirect, @NotNull final Set<Predicate<CommandSender>> requirements,
                      @Nullable final Function<CommandContext, CommandResult> command, @NotNull final String name,
                      @NotNull final ArgumentType<V> type) {
    this(fork, modifier, redirect, requirements, command, name, null, type);
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return this.type.getExamples();
  }

  @NotNull
  @Override
  public String getUsage() {
    return ArgumentNode.USAGE_ARGUMENT_OPEN + this.name + ArgumentNode.USAGE_ARGUMENT_CLOSE;
  }

  @Override
  public boolean isValidInput(@NotNull final String input) throws CommandSyntaxException {
    final var reader = new TextReader(input);
    this.type.parse(reader);
    return !reader.canRead() || reader.peek() == ' ';
  }

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder) {
    if (this.suggestions == null) {
      return this.type.suggestions(context, builder);
    }
    return this.suggestions.apply(context, builder);
  }

  @Override
  public int hashCode() {
    var result = this.name.hashCode();
    result = 31 * result + this.type.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ArgumentNode)) {
      return false;
    }
    final var that = (ArgumentNode<?>) obj;
    return this.name.equals(that.name) &&
      this.type.equals(that.type) &&
      super.equals(obj);
  }

  @NotNull
  @Override
  public String key() {
    return this.name;
  }

  @NotNull
  @Override
  public String name() {
    return this.name;
  }
}
