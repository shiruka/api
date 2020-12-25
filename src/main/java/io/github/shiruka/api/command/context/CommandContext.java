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
import io.github.shiruka.api.command.CommandResult;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.command.TextRange;
import io.github.shiruka.api.command.tree.CommandNode;
import java.util.*;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents command contexts.
 */
public final class CommandContext {

  /**
   * the primitive to wrapper.
   */
  @NotNull
  private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER = new HashMap<>();

  /**
   * the arguments.
   */
  @NotNull
  private final Map<String, ParsedArgument<?>> arguments;

  /**
   * the child.
   */
  @Nullable
  private final CommandContext child;

  /**
   * the command.
   */
  @Nullable
  private final Function<CommandContext, CommandResult> command;

  /**
   * the fork.
   */
  private final boolean fork;

  /**
   * the input.
   */
  @NotNull
  private final String input;

  /**
   * the modifier.
   */
  @Nullable
  private final Function<CommandContext, Collection<CommandSender>> modifier;

  /**
   * the nodes.
   */
  @NotNull
  private final List<ParsedCommandNode> nodes;

  /**
   * the range.
   */
  @NotNull
  private final TextRange range;

  /**
   * the root node.
   */
  @NotNull
  private final CommandNode rootNode;

  /**
   * the sender.
   */
  @NotNull
  private final CommandSender sender;

  static {
    CommandContext.PRIMITIVE_TO_WRAPPER.put(boolean.class, Boolean.class);
    CommandContext.PRIMITIVE_TO_WRAPPER.put(byte.class, Byte.class);
    CommandContext.PRIMITIVE_TO_WRAPPER.put(short.class, Short.class);
    CommandContext.PRIMITIVE_TO_WRAPPER.put(char.class, Character.class);
    CommandContext.PRIMITIVE_TO_WRAPPER.put(int.class, Integer.class);
    CommandContext.PRIMITIVE_TO_WRAPPER.put(long.class, Long.class);
    CommandContext.PRIMITIVE_TO_WRAPPER.put(float.class, Float.class);
    CommandContext.PRIMITIVE_TO_WRAPPER.put(double.class, Double.class);
  }

  /**
   * ctor.
   *
   * @param arguments the arguments.
   * @param child the child.
   * @param command the command.
   * @param fork the fork.
   * @param input the input.
   * @param modifier the modifier.
   * @param nodes the nodes.
   * @param range the range
   * @param rootNode the root node.
   * @param sender the sender.
   */
  public CommandContext(@NotNull final Map<String, ParsedArgument<?>> arguments, @Nullable final CommandContext child,
                        @Nullable final Function<CommandContext, CommandResult> command, final boolean fork,
                        @NotNull final String input,
                        @Nullable final Function<CommandContext, Collection<CommandSender>> modifier,
                        @NotNull final List<ParsedCommandNode> nodes, @NotNull final TextRange range,
                        @NotNull final CommandNode rootNode, @NotNull final CommandSender sender) {
    this.arguments = Collections.unmodifiableMap(arguments);
    this.child = child;
    this.command = command;
    this.fork = fork;
    this.input = input;
    this.modifier = modifier;
    this.nodes = Collections.unmodifiableList(nodes);
    this.range = range;
    this.rootNode = rootNode;
    this.sender = sender;
  }

  /**
   * copies all the values and creates a new instance for the given sender
   *
   * @param sender the sender to create.
   *
   * @return copied context.
   */
  @NotNull
  public CommandContext copyFor(@NotNull final CommandSender sender) {
    if (this.sender == sender) {
      return this;
    }
    return new CommandContext(this.arguments, this.child, this.command, this.fork, this.input, this.modifier,
      this.nodes, this.range, this.rootNode, sender);
  }

  /**
   * obtains the argument's value.
   *
   * @param name the name to get.
   * @param clazz the class to get.
   * @param <V> type of the value.
   *
   * @return argument's value.
   */
  @NotNull
  public <V> V getArgument(@NotNull final String name, @NotNull final Class<V> clazz) {
    final var argument = Objects.requireNonNull(this.arguments.get(name),
      String.format("No such argument '%s' exists on this command", name));
    final var result = argument.getResult();
    final var primitive = CommandContext.PRIMITIVE_TO_WRAPPER.getOrDefault(clazz, clazz)
      .isAssignableFrom(result.getClass());
    Preconditions.checkArgument(primitive, "Argument '%s' is defined as %s, not %s",
      name, result.getClass().getSimpleName(), clazz);
    //noinspection unchecked
    return (V) result;
  }

  /**
   * obtains the child.
   *
   * @return child.
   */
  @Nullable
  public CommandContext getChild() {
    return this.child;
  }

  /**
   * obtains the command.
   *
   * @return command.
   */
  @Nullable
  public Function<CommandContext, CommandResult> getCommand() {
    return this.command;
  }

  /**
   * obtains the input.
   *
   * @return input.
   */
  @NotNull
  public String getInput() {
    return this.input;
  }

  /**
   * obtains the last child.
   *
   * @return last child.
   */
  @NotNull
  public CommandContext getLastChild() {
    var result = this;
    while (result.getChild() != null) {
      result = result.getChild();
    }
    return result;
  }

  /**
   * obtains the nodes.
   *
   * @return nodes.
   */
  @NotNull
  public List<ParsedCommandNode> getNodes() {
    return this.nodes;
  }

  /**
   * obtains the range.
   *
   * @return range.
   */
  @NotNull
  public TextRange getRange() {
    return this.range;
  }

  /**
   * obtains the modifier.
   *
   * @return modifier.
   */
  @Nullable
  public Function<CommandContext, Collection<CommandSender>> getRedirectModifier() {
    return this.modifier;
  }

  /**
   * obtains the root node.
   *
   * @return root node.
   */
  @NotNull
  public CommandNode getRootNode() {
    return this.rootNode;
  }

  /**
   * obtains the command sender.
   *
   * @return command sender.
   */
  @NotNull
  public CommandSender getSender() {
    return this.sender;
  }

  /**
   * checks if the {@link #nodes} is not empty.
   *
   * @return {@code true} if the nodes is not empty.
   */
  public boolean hasNodes() {
    return !this.nodes.isEmpty();
  }

  @Override
  public int hashCode() {
    var result = this.sender.hashCode();
    result = 31 * result + this.arguments.hashCode();
    result = 31 * result + (this.command != null ? this.command.hashCode() : 0);
    result = 31 * result + this.rootNode.hashCode();
    result = 31 * result + this.nodes.hashCode();
    result = 31 * result + (this.child != null ? this.child.hashCode() : 0);
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CommandContext)) {
      return false;
    }
    final var that = (CommandContext) obj;
    return this.arguments.equals(that.arguments) &&
      this.rootNode.equals(that.rootNode) &&
      this.nodes.size() == that.nodes.size() &&
      this.nodes.equals(that.nodes) &&
      Objects.equals(this.command, that.command) &&
      this.sender.equals(that.sender) &&
      Objects.equals(this.child, that.child);
  }
}
