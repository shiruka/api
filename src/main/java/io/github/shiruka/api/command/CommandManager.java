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
import java.util.Arrays;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command manager.
 */
public interface CommandManager {

  /**
   * registers the given commands.
   *
   * @param commands the commands to register.
   */
  void register(@NotNull CommandHead... commands);

  /**
   * obtains the registered command map.
   *
   * @return registered command map.
   */
  @NotNull
  Map<String, CommandHead> registered();

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
  default void unregister(@NotNull final CommandHead... commands) {
    this.unregister(Arrays.stream(commands)
      .map(Named::name)
      .toArray(String[]::new));
  }
}
