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

package io.github.shiruka.api.command.context;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.command.*;
import io.github.shiruka.api.command.tree.CommandNode;
import java.util.*;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents command context builders.
 */
public final class CommandContextBuilder {

  /**
   * the arguments.
   */
  private final Map<String, ParsedArgument<?>> arguments = new LinkedHashMap<>();

  /**
   * the nodes.
   */
  private final List<ParsedCommandNode> nodes = new ArrayList<>();

  /**
   * the root node.
   */
  @NotNull
  private final CommandNode rootNode;

  /**
   * the child.
   */
  @Nullable
  private CommandContextBuilder child;

  /**
   * the command.
   */
  @Nullable
  private Command command;

  /**
   * the fork.
   */
  private boolean fork;

  /**
   * the modifier.
   */
  @Nullable
  private RedirectModifier modifier;

  /**
   * the range.
   */
  @NotNull
  private TextRange range;

  /**
   * the sender.
   */
  @NotNull
  private CommandSender sender;

  /**
   * ctor.
   *
   * @param sender the command source.
   * @param rootNode the root node of the command tree.
   * @param start the start in the input that this context spans.
   */
  public CommandContextBuilder(final int start, @NotNull final CommandNode rootNode,
                               @NotNull final CommandSender sender) {
    this.rootNode = rootNode;
    this.range = TextRange.at(start);
    this.sender = sender;
  }

  /**
   * builds the command context
   *
   * @param input the input string.
   *
   * @return the built command context.
   */
  @NotNull
  public CommandContext build(final String input) {
    return new CommandContext(this.arguments, this.child == null ? null : this.child.build(input), this.command,
      this.fork, input, this.modifier, this.nodes, this.range, this.rootNode, this.sender);
  }

  /**
   * creates a shallow copy of this builder.
   *
   * @return a copy of this builder.
   */
  @NotNull
  public CommandContextBuilder copy() {
    final var copy = new CommandContextBuilder(this.range.getStart(), this.rootNode, this.sender);
    copy.command = this.command;
    copy.arguments.putAll(this.arguments);
    copy.nodes.addAll(this.nodes);
    copy.child = this.child;
    copy.range = this.range;
    copy.fork = this.fork;
    return copy;
  }

  /**
   * finds the {@link SuggestionContext} given a cursor value.
   *
   * @param cursor the cursor position to find a suggestion context for.
   *
   * @return the {@link SuggestionContext} for the given cursor value.
   *
   * @throws IllegalStateException if the cursor position is smaller than the {@link #getRange()} or not within the
   *   bounds of any registered node.
   */
  @NotNull
  public SuggestionContext findSuggestionContext(final int cursor) {
    Preconditions.checkState(this.range.getStart() <= cursor,
      "Can't find node before cursor");
    if (this.range.getEnd() < cursor) {
      if (this.child != null) {
        return this.child.findSuggestionContext(cursor);
      }
      if (!this.nodes.isEmpty()) {
        final var last = this.nodes.get(this.nodes.size() - 1);
        return new SuggestionContext(last.getNode(), last.getRange().getEnd() + 1);
      }
      return new SuggestionContext(this.rootNode, this.range.getStart());
    }
    CommandNode prev = this.rootNode;
    for (final var node : this.nodes) {
      final var nodeRange = node.getRange();
      if (nodeRange.getStart() <= cursor && cursor <= nodeRange.getEnd()) {
        return new SuggestionContext(prev, nodeRange.getStart());
      }
      prev = node.getNode();
    }
    return new SuggestionContext(prev, this.range.getStart());
  }

  /**
   * obtains all stored arguments.
   *
   * @return all stored arguments.
   */
  @NotNull
  public Map<String, ParsedArgument<?>> getArguments() {
    return Collections.unmodifiableMap(this.arguments);
  }

  /**
   * obtains the child context, i.e. the context for a child command node.
   *
   * @return the child context.
   */
  @Nullable
  public CommandContextBuilder getChild() {
    return this.child;
  }

  /**
   * obtains the command to execute.
   *
   * @return the command to execute or {@code null} if not set.
   */
  @Nullable
  public Command getCommand() {
    return this.command;
  }

  /**
   * collects the last child command context in the chain, i.e. the lowest child you can reach from this context.
   *
   * @return the last child command context builder.
   */
  @NotNull
  public CommandContextBuilder getLastChild() {
    var result = this;
    while (result.getChild() != null) {
      result = result.getChild();
    }
    return result;
  }

  /**
   * obtains a list with all nodes.
   *
   * @return a list with all nodes.
   */
  @NotNull
  public List<ParsedCommandNode> getNodes() {
    return Collections.unmodifiableList(this.nodes);
  }

  /**
   * obtains the range this context spans in the input.
   *
   * @return the range this context spans in the input.
   */
  @NotNull
  public TextRange getRange() {
    return this.range;
  }

  /**
   * obtains the root node in the command tree.
   *
   * @return the root node in the command tree.
   */
  @NotNull
  public CommandNode getRootNode() {
    return this.rootNode;
  }

  /**
   * the command sender for the built command context.
   *
   * @return the command sender for the built command context.
   */
  @NotNull
  public CommandSender getSender() {
    return this.sender;
  }

  /**
   * stores an argument in this command, that can later be retrieved.
   *
   * @param name the name of the argument.
   * @param argument the argument to store.
   *
   * @return {@code this} for builder chain.
   *
   * @see CommandContext#getArgument
   */
  @NotNull
  public CommandContextBuilder withArgument(@NotNull final String name, @NotNull final ParsedArgument<?> argument) {
    this.arguments.put(name, argument);
    return this;
  }

  /**
   * sets a child context, which is the context for a child command node.
   *
   * @param child the child context.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public CommandContextBuilder withChild(@NotNull final CommandContextBuilder child) {
    this.child = child;
    return this;
  }

  /**
   * sets the command to execute, i.e. the one that was matched by this context.
   *
   * @param command the command that should be executed.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public CommandContextBuilder withCommand(@Nullable final Command command) {
    this.command = command;
    return this;
  }

  /**
   * adds the given command node alongside its parsed range to this context.
   *
   * @param node the command node to add.
   * @param range the range the node spans in the input.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public CommandContextBuilder withNode(@NotNull final CommandNode node, @NotNull final TextRange range) {
    this.nodes.add(new ParsedCommandNode(node, range));
    this.range = TextRange.encompassing(this.range, range);
    this.modifier = node.getRedirectModifier();
    this.fork = node.isFork();
    return this;
  }

  /**
   * sets the sender for this command.
   *
   * @param sender the command sender.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public CommandContextBuilder withSender(@NotNull final CommandSender sender) {
    this.sender = sender;
    return this;
  }
}
