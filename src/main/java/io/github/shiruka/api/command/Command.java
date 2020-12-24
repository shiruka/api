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

package io.github.shiruka.api.command;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.base.Named;
import io.github.shiruka.api.chat.ChatColor;
import io.github.shiruka.api.entity.Player;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine commands.
 */
public interface Command extends Named {

  /**
   * creates a new command to use for root.
   *
   * @param name the name to create.
   *
   * @return a new command instance.
   */
  @NotNull
  static Command create(@NotNull final String name) {
    return Command.literal(name);
  }

  /**
   * creates a new
   *
   * @param name the name to create.
   *
   * @return a new command instance.
   */
  @NotNull
  static Command literal(@NotNull final String name) {
    return Shiruka.getCommandManager().create(name)
      .literal(true);
  }

  /**
   * adds the given arguments under as sub command {@code this}.
   *
   * @param arguments the arguments to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  Command argument(@NotNull Command... arguments);

  /**
   * executes the command when it satisfies the conditions.
   *
   * @param context the context to execute.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  Command execute(@NotNull Consumer<CommandContext> context);

  /**
   * filters the command if the {@code predicate} returns false the {@link #execute(Consumer)} won't run.
   *
   * @param predicate the predicate to check.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  Command filter(@NotNull Predicate<CommandContext> predicate);

  /**
   * change the literal status of the command.
   *
   * @param literal the literal to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  Command literal(boolean literal);

  /**
   * makes the command only able to use {@link Player}s.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  default Command playerOnly() {
    return this.playerOnly(sender -> ChatColor.RED + "You can only use this command in the server as a Player!");
  }

  /**
   * makes the command only able to use {@link Player}s.
   *
   * @param errorMessage the error message to send if the {@link CommandContext#sender()} in not a {@link Player}.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  default Command playerOnly(@NotNull final Function<CommandSender, String> errorMessage) {
    return this.filter(context -> {
      final var sender = context.sender();
      if (sender instanceof Player) {
        return true;
      }
      sender.sendMessage(errorMessage.apply(sender));
      return false;
    });
  }
}
