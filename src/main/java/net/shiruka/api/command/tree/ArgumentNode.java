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

package net.shiruka.api.command.tree;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.shiruka.api.command.*;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.context.ParsedArgument;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.suggestion.Suggestions;
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
   * the default value.
   */
  @Nullable
  private final V defaultValue;

  /**
   * the name.
   */
  @NotNull
  private final String name;

  /**
   * the suggestion override.
   */
  @Nullable
  private final SuggestionProvider suggestions;

  /**
   * the type.
   */
  @NotNull
  private final ArgumentType<V> type;

  /**
   * ctor.
   *
   * @param defaultNode the default node.
   * @param defaultValue the default value.
   * @param description the description.
   * @param fork the forks.
   * @param isDefaultNode the is default node.
   * @param modifier the modifier.
   * @param redirect the redirect.
   * @param requirements the requirements.
   * @param command the command.
   * @param name the name.
   * @param suggestions the suggestion override.
   * @param type the type.
   */
  public ArgumentNode(@Nullable final CommandNode defaultNode, @Nullable final V defaultValue,
                      @Nullable final String description, final boolean fork, final boolean isDefaultNode,
                      @Nullable final RedirectModifier modifier, @Nullable final CommandNode redirect,
                      @NotNull final Set<Requirement> requirements, @Nullable final Command command,
                      @NotNull final String name, @Nullable final SuggestionProvider suggestions,
                      @NotNull final ArgumentType<V> type) {
    super(defaultNode, description, fork, isDefaultNode, modifier, redirect, requirements, command);
    this.defaultValue = defaultValue;
    this.name = name;
    this.suggestions = suggestions;
    this.type = type;
  }

  /**
   * obtains the default value.
   *
   * @return default value.
   */
  @Nullable
  public V getDefaultValue() {
    return this.defaultValue;
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return this.type.getExamples();
  }

  @NotNull
  @Override
  public String getKey() {
    return this.name;
  }

  @NotNull
  @Override
  public String getName() {
    return this.name;
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

  @Override
  public void parse(@NotNull final TextReader reader, @NotNull final CommandContextBuilder builder)
    throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var result = reader.canRead() || !this.isDefaultNode()
      ? this.type.parse(reader)
      : this.defaultValue;
    final var parsed = new ParsedArgument<>(start, reader.getCursor(), Objects.requireNonNull(result, "result"));
    builder.withArgument(this.name, parsed);
    builder.withNode(this, parsed.getRange());
  }

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder)
    throws CommandSyntaxException {
    if (this.suggestions == null) {
      return this.type.suggestions(context, builder);
    }
    return this.suggestions.getSuggestions(context, builder);
  }

  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  public ArgumentType<V> getType() {
    return this.type;
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
    if (!(obj instanceof ArgumentNode<?>)) {
      return false;
    }
    final var that = (ArgumentNode<?>) obj;
    return this.name.equals(that.name) &&
      this.type.equals(that.type) &&
      super.equals(obj);
  }

  @Override
  public String toString() {
    return "<argument " + this.name + ":" + this.type + ">";
  }
}
