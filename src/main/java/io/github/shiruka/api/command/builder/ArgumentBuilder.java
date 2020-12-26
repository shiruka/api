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

package io.github.shiruka.api.command.builder;

import io.github.shiruka.api.base.Self;
import io.github.shiruka.api.command.Command;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.command.RedirectModifier;
import io.github.shiruka.api.command.SingleRedirectModifier;
import io.github.shiruka.api.command.tree.CommandNode;
import io.github.shiruka.api.command.tree.RootNode;
import io.github.shiruka.api.entity.Player;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
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
   * the requirements.
   */
  @NotNull
  private final Set<Predicate<CommandSender>> requirements = new HashSet<>();

  /**
   * the command.
   */
  @Nullable
  private Command command;

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
   * sets the command.
   *
   * @param command the command to set.
   *
   * @return {@link #self()} for builder chain.
   */
  @NotNull
  public final T executes(@Nullable final Command command) {
    this.command = command;
    return this.self();
  }

  /**
   * sets the fork.
   *
   * @param target the target to set.
   * @param modifier the modifier to set.
   *
   * @return {@link #self()} for builder chain.
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
   * @return {@link #self()} for builder chain.
   */
  @NotNull
  public final T forward(@NotNull final CommandNode target, @Nullable final RedirectModifier modifier,
                         final boolean fork) {
    if (!this.arguments.getChildren().isEmpty()) {
      throw new IllegalStateException("Cannot forward a node with children");
    }
    this.redirect = target;
    this.modifier = modifier;
    this.fork = fork;
    return this.self();
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
  public final Set<Predicate<CommandSender>> getRequirements() {
    return Collections.unmodifiableSet(this.requirements);
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
   * adds a requirement which control is the {@link CommandSender} is a {@link Player}.
   */
  @NotNull
  public final T playerOnly() {
    return this.playerOnly("&cYou have to use this command as a Player!");
  }

  /**
   * adds a requirement which control is the {@link CommandSender} is a {@link Player}.
   */
  @NotNull
  public final T playerOnly(@NotNull final String errorMessage) {
    return this.playerOnly(sender -> errorMessage);
  }

  /**
   * adds a requirement which control is the {@link CommandSender} is a {@link Player}.
   */
  @NotNull
  public final T playerOnly(@NotNull final Function<CommandSender, String> errorMessage) {
    return this.requires(sender -> {
      if (sender instanceof Player) {
        return true;
      }
      sender.sendMessage(errorMessage.apply(sender));
      return false;
    });
  }

  /**
   * sets the redirect.
   *
   * @param target the target to set.
   *
   * @return {@link #self()} for builder chain.
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
   * @return {@link #self()} for builder chain.
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
   * @return {@link #self()} for builder chain.
   */
  @SafeVarargs
  @NotNull
  public final T requires(@NotNull final Predicate<CommandSender>... requirements) {
    return this.requires(List.of(requirements));
  }

  /**
   * adds the given requirements.
   *
   * @param requirements the requirements to set.
   *
   * @return {@link #self()} for builder chain.
   */
  @NotNull
  public final T requires(@NotNull final Iterable<Predicate<CommandSender>> requirements) {
    requirements.forEach(this.requirements::add);
    return this.self();
  }

  /**
   * adds the new argument.
   *
   * @param argument the argument to add.
   *
   * @return {@link #self()} for builder chain.
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
   * @return {@link #self()} for builder chain.
   */
  @NotNull
  public final T then(@NotNull final CommandNode argument) {
    if (this.redirect != null) {
      throw new IllegalStateException("Cannot add children to a redirected node");
    }
    this.arguments.addChild(argument);
    return this.self();
  }

  /**
   * builds a new {@link CommandNode} instance.
   *
   * @return a new node instance.
   */
  @NotNull
  public abstract CommandNode build();
}
