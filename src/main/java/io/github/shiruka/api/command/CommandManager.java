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

import io.github.shiruka.api.base.Named;
import io.github.shiruka.api.plugin.Plugin;
import java.util.Arrays;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command manager.
 */
public interface CommandManager {

  /**
   * creates a new instance of {@link Command}.
   *
   * @param name the name to create.
   *
   * @return a new command builder instance.
   */
  @NotNull
  Command create(@NotNull String name);

  /**
   * runs the given input.
   * <p>
   * {@code command} can start with {@code /} or not. it does not matter.
   *
   * @param command the command to run.
   */
  void exec(@NotNull String command);

  /**
   * registers the given commands.
   *
   * @param plugin the plugin to register.
   * @param commands the commands to register.
   *
   * @throws IllegalArgumentException if any of given command is already registered.
   */
  void register(@NotNull Plugin plugin, @NotNull Command... commands);

  /**
   * obtains the registered command map.
   *
   * @param plugin the plugin to obtain.
   *
   * @return registered command map.
   */
  @NotNull
  Map<String, Command> registered(@NotNull Plugin plugin);

  /**
   * unregister the given commands.
   *
   * @param commands the commands to unregister.
   */
  default void unregister(@NotNull final Command... commands) {
    this.unregister(Arrays.stream(commands)
      .map(Named::name)
      .toArray(String[]::new));
  }

  /**
   * unregisters the given commands.
   *
   * @param commands the commands to unregister.
   */
  void unregister(@NotNull String... commands);
}
