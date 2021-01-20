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

package net.shiruka.api.command;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command results.
 *
 * @todo #1:15m Implement succeed and failed correctly method.
 */
public interface CommandResult {

  /**
   * an empty command result instance.
   *
   * @return empty result.
   */
  @NotNull
  static CommandResult empty() {
    return CommandResult.of(0);
  }

  /**
   * a command result.
   *
   * @return command result.
   */
  @NotNull
  static CommandResult of() {
    return CommandResult.of(1);
  }

  /**
   * a command result.
   *
   * @param amount the amount to create
   *
   * @return command result.
   */
  @NotNull
  static CommandResult of(final int amount) {
    return new SimpleCommandResult(amount);
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

  /**
   * obtains the result.
   *
   * @return result.
   */
  int result();

  /**
   * a simple command result implementation.
   */
  final class SimpleCommandResult implements CommandResult {

    /**
     * the result.
     */
    private int result;

    /**
     * ctor.
     *
     * @param result the result.
     */
    public SimpleCommandResult(final int result) {
      this.result = result;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.result);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || this.getClass() != obj.getClass()) {
        return false;
      }
      final var that = (SimpleCommandResult) obj;
      return this.result == that.result;
    }

    @Override
    public String toString() {
      return "SimpleCommandResult{" +
        "result=" + this.result +
        '}';
    }

    @NotNull
    @Override
    public CommandResult merge(@NotNull final CommandResult result) {
      this.result += result.result();
      return this;
    }

    @Override
    public int result() {
      return this.result;
    }
  }
}
