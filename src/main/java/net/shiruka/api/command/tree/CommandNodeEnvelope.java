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

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectRBTreeMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import net.shiruka.api.command.Command;
import net.shiruka.api.command.CommandNode;
import net.shiruka.api.command.RedirectModifier;
import net.shiruka.api.command.Requirement;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.context.ParseResults;
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
  private final Map<String, ArgumentNode<?>> arguments = new Object2ObjectLinkedOpenHashMap<>();

  /**
   * the children.
   */
  private final Map<String, CommandNode> children = new Object2ObjectRBTreeMap<>();

  /**
   * the context requirement.
   */
  @NotNull
  private final Predicate<ParseResults> contextRequirement;

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
   * the usage.
   */
  @Nullable
  private final String usage;

  /**
   * the command.
   */
  @Nullable
  private Command command;

  /**
   * the has literals.
   */
  private boolean hasLiterals = false;

  /**
   * ctor.
   *
   * @param contextRequirement the context requirement.
   * @param defaultNode the default node.
   * @param description the description.
   * @param fork the forks.
   * @param isDefaultNode the is default node.
   * @param modifier the modifier.
   * @param redirect the redirect.
   * @param requirements the requirement.
   * @param usage the usage.
   * @param command the command.
   */
  protected CommandNodeEnvelope(@NotNull final Predicate<ParseResults> contextRequirement,
                                @Nullable final CommandNode defaultNode, @Nullable final String description,
                                final boolean fork, final boolean isDefaultNode,
                                @Nullable final RedirectModifier modifier, @Nullable final CommandNode redirect,
                                @NotNull final Set<Requirement> requirements, @Nullable final String usage,
                                @Nullable final Command command) {
    this.contextRequirement = contextRequirement;
    this.defaultNode = defaultNode;
    this.description = description;
    this.fork = fork;
    this.isDefaultNode = isDefaultNode;
    this.modifier = modifier;
    this.redirect = redirect;
    this.requirements = Collections.unmodifiableSet(requirements);
    this.usage = usage;
    this.command = command;
  }

  @Override
  public final void addChild(@NotNull final CommandNode node) {
    if (node instanceof RootNode) {
      throw new UnsupportedOperationException("Cannot add a RootNode as a child to any other CommandNode!");
    }
    if (node instanceof LiteralNode) {
      ((LiteralNode) node).getAliases().forEach(this::addChild);
    }
    final var name = node.getName();
    final var child = this.children.get(name);
    if (child != null) {
      child.setCommand(node.getCommand());
      node.getChildren().forEach(child::addChild);
      return;
    }
    this.children.put(name, node);
    if (node instanceof LiteralNode) {
      this.hasLiterals = true;
    } else if (node instanceof ArgumentNode) {
      this.arguments.put(name, (ArgumentNode<?>) node);
    }
  }

  @Override
  public final boolean canUse(@NotNull final CommandSender sender) {
    return this.requirements.stream().allMatch(predicate -> predicate.test(sender));
  }

  @Override
  public final boolean canUse(@NotNull final ParseResults parse) {
    return this.contextRequirement.test(parse);
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
  public final Predicate<ParseResults> getContextRequirement() {
    return this.contextRequirement;
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
    if (!this.hasLiterals) {
      return this.arguments.values();
    }
    final var cursor = input.getCursor();
    while (input.canRead() && input.peek() != ' ') {
      input.skip();
    }
    final var text = input.getText().substring(cursor, input.getCursor());
    input.setCursor(cursor);
    final var literal = this.children.get(text);
    if (literal instanceof LiteralNode) {
      return Collections.singleton(literal);
    }
    return this.arguments.values();
  }

  @NotNull
  @Override
  public final Set<Requirement> getRequirements() {
    return this.requirements;
  }

  @Nullable
  @Override
  public final String getUsage() {
    return this.usage;
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
    final var child = this.children.remove(node);
    if (child != null) {
      this.arguments.remove(node);
    }
  }

  @Override
  public final int compareTo(@NotNull final CommandNode o) {
    if (this instanceof LiteralNode && o instanceof LiteralNode) {
      return this.getKey().compareTo(o.getKey());
    }
    return o instanceof LiteralNode ? 1 : -1;
  }

  @Override
  public int hashCode() {
    return 31 * this.children.hashCode() + (this.command != null ? this.command.hashCode() : 0);
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
