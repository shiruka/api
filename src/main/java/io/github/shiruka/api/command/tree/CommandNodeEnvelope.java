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

import io.github.shiruka.api.command.*;
import java.util.*;
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
  private final Map<String, CommandNode> children = new LinkedHashMap<>();

  /**
   * the fork.
   */
  private final boolean fork;

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
   * @param fork the forks.
   * @param modifier the modifier.
   * @param redirect the redirect.
   * @param requirements the requirement.
   * @param command the command.
   */
  protected CommandNodeEnvelope(final boolean fork, @Nullable final RedirectModifier modifier,
                                @Nullable final CommandNode redirect, @NotNull final Set<Requirement> requirements,
                                @Nullable final Command command) {
    this.fork = fork;
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
    final var child = this.children.get(node.name());
    if (child != null) {
      child.setCommand(node.getCommand());
      node.getChildren().forEach(child::addChild);
      return;
    }
    this.children.put(node.name(), node);
    if (node instanceof LiteralNode) {
      this.literals.put(node.name(), (LiteralNode) node);
    } else if (node instanceof ArgumentNode) {
      this.arguments.put(node.name(), (ArgumentNode<?>) node);
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
      return this.key().compareTo(o.key());
    }
    return o instanceof LiteralNode ? 1 : -1;
  }
}