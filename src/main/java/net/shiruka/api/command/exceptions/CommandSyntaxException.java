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

package net.shiruka.api.command.exceptions;

import net.shiruka.api.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an exception that thrown when a command has syntax error.
 */
public final class CommandSyntaxException extends Exception {

  /**
   * the context amount.
   */
  private static final int CONTEXT_AMOUNT = 10;

  /**
   * the cursor.
   */
  private final int cursor;

  /**
   * the input.
   */
  @Nullable
  private final String input;

  /**
   * the message.
   */
  @NotNull
  private final String message;

  /**
   * the type.
   */
  @NotNull
  private final CommandException type;

  /**
   * ctor.
   *
   * @param cursor the cursor.
   * @param input the input.
   * @param message the message.
   * @param type the type.
   */
  public CommandSyntaxException(final int cursor, @Nullable final String input, @NotNull final String message,
                                @NotNull final CommandException type) {
    super(message, null, true, true);
    this.cursor = cursor;
    this.input = input;
    this.message = message;
    this.type = type;
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param type the type.
   */
  public CommandSyntaxException(@NotNull final String message, @NotNull final CommandException type) {
    this(-1, null, message, type);
  }

  /**
   * obtains the context of the error.
   *
   * @return collected error message.
   */
  @Nullable
  public String getContext() {
    if (this.input == null || this.cursor < 0) {
      return null;
    }
    final var builder = new StringBuilder();
    final var cursor = Math.min(this.input.length(), this.cursor);
    if (cursor > CommandSyntaxException.CONTEXT_AMOUNT) {
      builder.append("...");
    }
    return builder
      .append(this.input, Math.max(0, cursor - CommandSyntaxException.CONTEXT_AMOUNT), cursor)
      .toString();
  }

  /**
   * obtains the cursor.
   *
   * @return cursor.
   */
  public int getCursor() {
    return this.cursor;
  }

  /**
   * obtains the input.
   *
   * @return input.
   */
  @Nullable
  public String getInput() {
    return this.input;
  }

  /**
   * obtains the message.
   *
   * @return collected message.
   */
  @Override
  public String getMessage() {
    var message = this.message;
    final var context = this.getContext();
    if (context != null) {
      message += " at position " + this.cursor + ": " + context;
    }
    return message;
  }

  /**
   * obtains the message.
   *
   * @return message.
   */
  @NotNull
  public String getRawMessage() {
    return this.message;
  }

  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  public CommandException getType() {
    return this.type;
  }
}
