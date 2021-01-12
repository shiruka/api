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

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command results.
 */
public interface CommandResult {

  /**
   * an empty command result instance.
   *
   * @return empty result.
   */
  @NotNull
  static CommandResult empty() {
    return new CommandResult() {
      @NotNull
      @Override
      public CommandResult merge(@NotNull final CommandResult result) {
        return this;
      }
    };
  }

  /**
   * a succeed command result.
   *
   * @return succeed command result.
   *
   * @todo #1:15m Implement it.
   */
  @NotNull
  static CommandResult succeed() {
    return CommandResult.empty();
  }

  /**
   * merges the given result.
   *
   * @param result the result to merge.
   *
   * @return a new merged result.
   */
  @NotNull
  CommandResult merge(@NotNull CommandResult result);
}
