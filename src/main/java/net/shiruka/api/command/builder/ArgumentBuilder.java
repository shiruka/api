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

package net.shiruka.api.command.builder;

import com.google.common.base.Preconditions;
import java.util.*;
import net.shiruka.api.base.Self;
import net.shiruka.api.command.*;
import net.shiruka.api.command.tree.RootNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface that helps to create command node instances.
 *
 * @param <T> type of the implementation.
 */
public abstract class ArgumentBuilder<T extends ArgumentBuilder<T>> implements Self<T> {

  /**
   * the root of arguments.
   */
  private final RootNode arguments = new RootNode();

  /**
   * the is default node.
   */
  private final boolean isDefaultNode;

  /**
   * the requirements.
   */
  @NotNull
  private final Set<Requirement> requirements = new HashSet<>();

  /**
   * the command.
   */
  @Nullable
  private Command command;

  /**
   * the default node.
   */
  @Nullable
  private CommandNode defaultNode;

  /**
   * the description.
   */
  @Nullable
  private String description;

  /**
   * the fork
   */
  private boolean fork;

  /**
   * the modifier.
   */
  @Nullable
  private RedirectModifier modifier;

  /**
   * the target.
   */
  @Nullable
  private CommandNode redirect;

  /**
   * ctor.
   */
  protected ArgumentBuilder() {
    this(false);
  }

  /**
   * ctor.
   *
   * @param isDefaultNode the is default node.
   */
  protected ArgumentBuilder(final boolean isDefaultNode) {
    this.isDefaultNode = isDefaultNode;
  }

  /**
   * sets the description.
   *
   * @param description the description to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T describe(@Nullable final String description) {
    this.description = description;
    return this.getSelf();
  }

  /**
   * sets the command.
   *
   * @param command the command to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T executes(@Nullable final Command command) {
    this.command = command;
    return this.getSelf();
  }

  /**
   * sets the fork.
   *
   * @param target the target to set.
   * @param modifier the modifier to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T fork(@NotNull final CommandNode target, @NotNull final RedirectModifier modifier) {
    return this.forward(target, modifier, true);
  }

  /**
   * sets the forward.
   *
   * @param target the target to set.
   * @param modifier the modifier to set.
   * @param fork the modifier to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T forward(@NotNull final CommandNode target, @Nullable final RedirectModifier modifier,
                         final boolean fork) {
    Preconditions.checkState(this.arguments.getChildren().isEmpty(), "Cannot forward a node with children");
    this.redirect = target;
    this.modifier = modifier;
    this.fork = fork;
    return this.getSelf();
  }

  /**
   * obtains the arguments.
   *
   * @return arguments.
   */
  @NotNull
  public final Collection<CommandNode> getArguments() {
    return this.arguments.getChildren();
  }

  /**
   * obtains the command.
   *
   * @return command.
   */
  @Nullable
  public final Command getCommand() {
    return this.command;
  }

  /**
   * obtains the default node.
   *
   * @return default node.
   */
  @Nullable
  public final CommandNode getDefaultNode() {
    return this.defaultNode;
  }

  /**
   * obtains the description.
   *
   * @return description.
   */
  @Nullable
  public final String getDescription() {
    return this.description;
  }

  /**
   * obtains the modifier.
   *
   * @return modifier.
   */
  @Nullable
  public final RedirectModifier getModifier() {
    return this.modifier;
  }

  /**
   * obtains the redirect.
   *
   * @return redirect.
   */
  @Nullable
  public final CommandNode getRedirect() {
    return this.redirect;
  }

  /**
   * obtains the requirements.
   *
   * @return requirements.
   */
  @NotNull
  public final Set<Requirement> getRequirements() {
    return Collections.unmodifiableSet(this.requirements);
  }

  /**
   * obtains the is default node.
   *
   * @return is default node.
   */
  public final boolean isDefaultNode() {
    return this.isDefaultNode;
  }

  /**
   * obtains the fork.
   *
   * @return fork.
   */
  public final boolean isFork() {
    return this.fork;
  }

  /**
   * sets the redirect.
   *
   * @param target the target to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T redirect(@NotNull final CommandNode target) {
    return this.forward(target, null, false);
  }

  /**
   * sets the redirect.
   *
   * @param target the target to set.
   * @param modifier the modifier to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T redirect(@NotNull final CommandNode target, @Nullable final SingleRedirectModifier modifier) {
    return this.forward(target, modifier == null ? null : o -> Collections.singleton(modifier.apply(o)), false);
  }

  /**
   * adds the given requirements.
   *
   * @param requirements the requirements to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T requires(@NotNull final Requirement... requirements) {
    return this.requires(List.of(requirements));
  }

  /**
   * adds the given requirements.
   *
   * @param requirements the requirements to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T requires(@NotNull final Iterable<Requirement> requirements) {
    requirements.forEach(this.requirements::add);
    return this.getSelf();
  }

  /**
   * adds the new argument.
   *
   * @param argument the argument to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T then(@NotNull final ArgumentBuilder<?> argument) {
    return this.then(argument.build());
  }

  /**
   * adds the new argument.
   *
   * @param argument the argument to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public final T then(@NotNull final CommandNode argument) {
    Preconditions.checkState(this.redirect == null, "Cannot add children to a redirected node");
    if (argument.isDefaultNode()) {
      Preconditions.checkState(this.defaultNode == null,
        "Cannot add multiple default nodes as child of one node");
      this.defaultNode = argument;
    }
    this.arguments.addChild(argument);
    return this.getSelf();
  }

  /**
   * builds a new {@link CommandNode} instance.
   *
   * @return a new node instance.
   */
  @NotNull
  public abstract CommandNode build();
}
