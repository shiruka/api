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

import java.util.*;
import net.shiruka.api.command.*;
import net.shiruka.api.command.sender.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an abstract implementation of {@link CommandNode}.
 */
public abstract class CommandNodeEnvelope implements CommandNode {

  /**
   * the arguments.
   */
  private final Map<String, ArgumentNode<?>> arguments = new LinkedHashMap<>();

  /**
   * the children.
   */
  private final Map<String, CommandNode> children = new TreeMap<>();

  /**
   * the default node.
   */
  @Nullable
  private final CommandNode defaultNode;

  /**
   * the description.
   */
  @Nullable
  private final String description;

  /**
   * the fork.
   */
  private final boolean fork;

  /**
   * the is default node.
   */
  private final boolean isDefaultNode;

  /**
   * the literals.
   */
  private final Map<String, LiteralNode> literals = new LinkedHashMap<>();

  /**
   * the modifier.
   */
  @Nullable
  private final RedirectModifier modifier;

  /**
   * the redirect.
   */
  @Nullable
  private final CommandNode redirect;

  /**
   * the requirement.
   */
  @NotNull
  private final Set<Requirement> requirements;

  /**
   * the command.
   */
  @Nullable
  private Command command;

  /**
   * ctor.
   *
   * @param defaultNode the default node.
   * @param description the description.
   * @param fork the forks.
   * @param isDefaultNode the is default node.
   * @param modifier the modifier.
   * @param redirect the redirect.
   * @param requirements the requirement.
   * @param command the command.
   */
  protected CommandNodeEnvelope(@Nullable final CommandNode defaultNode, @Nullable final String description,
                                final boolean fork, final boolean isDefaultNode,
                                @Nullable final RedirectModifier modifier, @Nullable final CommandNode redirect,
                                @NotNull final Set<Requirement> requirements, @Nullable final Command command) {
    this.defaultNode = defaultNode;
    this.description = description;
    this.fork = fork;
    this.isDefaultNode = isDefaultNode;
    this.modifier = modifier;
    this.redirect = redirect;
    this.requirements = Collections.unmodifiableSet(requirements);
    this.command = command;
  }

  @Override
  public final void addChild(@NotNull final CommandNode node) {
    if (node instanceof RootNode) {
      throw new UnsupportedOperationException("Cannot add a RootNode as a child to any other CommandNode!");
    }
    final var child = this.children.get(node.getName());
    if (child != null) {
      child.setCommand(node.getCommand());
      node.getChildren().forEach(child::addChild);
      return;
    }
    this.children.put(node.getName(), node);
    if (node instanceof LiteralNode) {
      this.literals.put(node.getName(), (LiteralNode) node);
    } else if (node instanceof ArgumentNode) {
      this.arguments.put(node.getName(), (ArgumentNode<?>) node);
    }
  }

  @Override
  public final boolean canUse(@NotNull final CommandSender sender) {
    return this.requirements.stream().allMatch(predicate -> predicate.test(sender));
  }

  @NotNull
  @Override
  public final Optional<CommandNode> getChild(@NotNull final String name) {
    return Optional.ofNullable(this.children.get(name));
  }

  @NotNull
  @Override
  public final Collection<CommandNode> getChildren() {
    return this.children.values();
  }

  @Nullable
  @Override
  public final Command getCommand() {
    return this.command;
  }

  @Override
  public final void setCommand(@Nullable final Command command) {
    this.command = command;
  }

  @NotNull
  @Override
  public final Optional<CommandNode> getDefaultNode() {
    return Optional.ofNullable(this.defaultNode);
  }

  @Nullable
  @Override
  public final String getDescription() {
    return this.description;
  }

  @Nullable
  @Override
  public final CommandNode getRedirect() {
    return this.redirect;
  }

  @Nullable
  @Override
  public final RedirectModifier getRedirectModifier() {
    return this.modifier;
  }

  @NotNull
  @Override
  public final Collection<? extends CommandNode> getRelevantNodes(@NotNull final TextReader input) {
    if (!input.canRead() && this.defaultNode != null) {
      return Collections.singleton(this.defaultNode);
    }
    if (this.literals.size() <= 0) {
      return this.arguments.values();
    }
    final var cursor = input.getCursor();
    while (input.canRead() && input.peek() != ' ') {
      input.skip();
    }
    final var text = input.getText().substring(cursor, input.getCursor());
    input.setCursor(cursor);
    final var literal = this.literals.get(text);
    if (literal != null) {
      return Collections.singleton(literal);
    }
    return this.arguments.values();
  }

  @NotNull
  @Override
  public final Set<Requirement> getRequirements() {
    return this.requirements;
  }

  @Override
  public final boolean isDefaultNode() {
    return this.isDefaultNode;
  }

  @Override
  public final boolean isFork() {
    return this.fork;
  }

  @Override
  public final void removeChild(@NotNull final String node) {
    this.children.remove(node);
    this.literals.remove(node);
    this.arguments.remove(node);
  }

  @Override
  public final int compareTo(@NotNull final CommandNode o) {
    if (this instanceof LiteralNode && o instanceof LiteralNode) {
      return this.getKey().compareTo(o.getKey());
    }
    return o instanceof LiteralNode ? 1 : -1;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CommandNodeEnvelope)) {
      return false;
    }
    final var that = (CommandNodeEnvelope) obj;
    return this.children.equals(that.children) &&
      Objects.equals(this.command, that.command);
  }
}
