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

package io.github.shiruka.api.command;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.base.Named;
import io.github.shiruka.api.command.builder.LiteralBuilder;
import io.github.shiruka.api.plugin.Plugin;
import java.util.Arrays;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command manager.
 */
public interface CommandManager {

  /**
   * runs the given input.
   * <p>
   * {@code command} can start with {@code /} or not. it does not matter.
   *
   * @param command the command to run.
   */
  default void execute(@NotNull final String command) {
    this.execute(command, Shiruka.getConsoleCommandSender());
  }

  /**
   * runs the given input.
   * <p>
   * {@code command} can start with {@code /} or not. it does not matter.
   *
   * @param command the command to run.
   * @param sender the sender to execute.
   */
  void execute(@NotNull String command, @NotNull CommandSender sender);

  /**
   * registers the given commands.
   *
   * @param plugin the plugin to register.
   * @param commands the commands to register.
   *
   * @throws IllegalArgumentException if any of given command is already registered.
   */
  void register(@NotNull Plugin plugin, @NotNull CommandNode... commands);

  /**
   * registers the given commands.
   *
   * @param plugin the plugin to register.
   * @param builders the builders to register.
   *
   * @throws IllegalArgumentException if any of given command is already registered.
   */
  default void register(@NotNull final Plugin plugin, @NotNull final LiteralBuilder... builders) {
    this.register(plugin, Arrays.stream(builders)
      .map(LiteralBuilder::build)
      .toArray(CommandNode[]::new));
  }

  /**
   * obtains the registered command map.
   *
   * @param plugin the plugin to obtain.
   *
   * @return registered command map.
   */
  @NotNull
  Map<String, CommandNode> registered(@NotNull Plugin plugin);

  /**
   * unregisters the given commands.
   *
   * @param commands the commands to unregister.
   */
  void unregister(@NotNull String... commands);

  /**
   * unregister the given commands.
   *
   * @param commands the commands to unregister.
   */
  default void unregister(@NotNull final CommandNode... commands) {
    this.unregister(Arrays.stream(commands)
      .map(Named::getName)
      .toArray(String[]::new));
  }
}
