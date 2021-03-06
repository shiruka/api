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

package net.shiruka.api.command;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.context.ParseResults;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.sender.CommandSender;
import net.shiruka.api.command.suggestion.Suggestions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface for the command tree, just representing a single command node.
 */
public interface CommandNode extends Comparable<CommandNode> {

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
   * checks the parse result with {@link #getContextRequirement()}.
   *
   * @param parse the parse to check.
   *
   * @return {@code true} if the test succeeded.
   */
  boolean canUse(@NotNull ParseResults parse);

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
  Command getCommand();

  /**
   * sets the command.
   *
   * @param command the command to set.
   */
  void setCommand(@Nullable Command command);

  /**
   * obtains the context requirement.
   *
   * @return context requirement.
   */
  @NotNull
  Predicate<ParseResults> getContextRequirement();

  /**
   * obtains the default node.
   *
   * @return default node.
   */
  @NotNull
  Optional<CommandNode> getDefaultNode();

  /**
   * obtains the description.
   *
   * @return description.
   */
  @Nullable
  String getDescription();

  /**
   * obtains the examples.
   *
   * @return examples.
   */
  @NotNull
  Collection<String> getExamples();

  /**
   * obtains the key.
   *
   * @return key.
   */
  @NotNull
  String getKey();

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  String getName();

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
  RedirectModifier getRedirectModifier();

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
  Set<Requirement> getRequirements();

  /**
   * obtains the smart usage.
   *
   * @return smart usage.
   */
  @NotNull
  String getSmartUsage();

  /**
   * obtains the usage.
   *
   * @return usage.
   */
  @Nullable
  String getUsage();

  /**
   * checks if the {@code this} is a default node.
   *
   * @return {@code true} if {@code this} is a default node.
   */
  boolean isDefaultNode();

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
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  boolean isValidInput(@NotNull String input) throws CommandSyntaxException;

  /**
   * parses the given reader to write into the builder.
   *
   * @param reader te reader to parse.
   * @param builder the builder to parse.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  void parse(@NotNull TextReader reader, @NotNull CommandContextBuilder builder) throws CommandSyntaxException;

  /**
   * removes the given node from the root.
   *
   * @param node the node to remove.
   */
  default void removeChild(@NotNull final CommandNode node) {
    this.removeChild(node.getName());
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
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  CompletableFuture<Suggestions> suggestions(@NotNull CommandContext context, @NotNull Suggestions.Builder builder)
    throws CommandSyntaxException;
}
