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

import io.github.shiruka.api.base.Keyed;
import io.github.shiruka.api.base.Named;
import io.github.shiruka.api.command.context.CommandContext;
import io.github.shiruka.api.command.CommandResult;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import io.github.shiruka.api.command.suggestion.Suggestions;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface for the command tree, just representing a single command node.
 */
public interface CommandNode extends Comparable<CommandNode>, Named, Keyed {

  /**
   * adds a new child node to this command node.
   *
   * @param node the child command node to add.
   *
   * @throws UnsupportedOperationException if you try to add a root command to any other command node.
   */
  void addChild(@NotNull CommandNode node);

  /**
   * checks whether the given command source can use this command node.
   *
   * @param sender the command sender to check for.
   *
   * @return {@code true} if the given command sender can use this command node.
   */
  boolean canUse(@NotNull CommandSender sender);

  /**
   * gives the child.
   *
   * @param name the name to get.
   *
   * @return child node.
   */
  @NotNull
  Optional<CommandNode> getChild(@NotNull String name);

  /**
   * obtains the children of {@code this}.
   *
   * @return list of child.
   */
  @NotNull
  Collection<CommandNode> getChildren();

  /**
   * obtains the executor of the node.
   *
   * @return the executor.
   */
  @Nullable
  Function<CommandContext, CommandResult> getCommand();

  /**
   * sets the command.
   *
   * @param command the command to set.
   */
  void setCommand(@Nullable Function<CommandContext, CommandResult> command);

  /**
   * obtains the examples.
   *
   * @return examples.
   */
  @NotNull
  Collection<String> getExamples();

  /**
   * obtains the redirect.
   *
   * @return redirect.
   */
  @Nullable
  CommandNode getRedirect();

  /**
   * obtains the redirect modifier.
   *
   * @return redirect modifier.
   */
  @Nullable
  Function<CommandContext, Collection<CommandSender>> getRedirectModifier();

  /**
   * collects the relevant command nodes.
   *
   * @param input the input to collect.
   *
   * @return relevant nodes.
   */
  @NotNull
  Collection<? extends CommandNode> getRelevantNodes(@NotNull TextReader input);

  /**
   * obtains the requirements.
   *
   * @return requirements.
   */
  @NotNull
  Set<Predicate<CommandSender>> getRequirements();

  /**
   * obtains the usage text.
   *
   * @return usage text.
   */
  @NotNull
  String getUsage();

  /**
   * obtains the fork.
   *
   * @return {@code true} if the node is forked.
   */
  boolean isFork();

  /**
   * checks if the given input is valid.
   *
   * @param input the input to check.
   *
   * @return {@code true} if the input is valid.
   */
  boolean isValidInput(@NotNull final String input) throws CommandSyntaxException;

  /**
   * removes the given node from the root.
   *
   * @param node the node to remove.
   */
  default void removeChild(@NotNull final CommandNode node) {
    this.removeChild(node.name());
  }

  /**
   * removes the given node from the root.
   *
   * @param node the node to remove.
   */
  void removeChild(@NotNull String node);

  /**
   * collects the suggestions.
   *
   * @param context the context to suggest.
   * @param builder the builder to suggest.
   *
   * @return completable suggestions future.
   */
  @NotNull
  CompletableFuture<Suggestions> suggestions(@NotNull CommandContext context, @NotNull Suggestions.Builder builder);
}
