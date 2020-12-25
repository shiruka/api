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

package io.github.shiruka.api.command.exceptions;

import io.github.shiruka.api.command.TextReader;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

/**
 * a dynamic command implementation for {@link CommandException} by 1 parameter.
 */
public final class CeDynamic implements CommandException {

  /**
   * the function.
   */
  @NotNull
  private final Function<Object, String> function;

  /**
   * ctor.
   *
   * @param function the function.
   */
  public CeDynamic(@NotNull final Function<Object, String> function) {
    this.function = function;
  }

  /**
   * creates a command syntax exception.
   *
   * @param first the first to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException create(@NotNull final Object first) {
    return new CommandSyntaxException(this.function.apply(first), this);
  }

  /**
   * creates a command syntax exception.
   *
   * @param reader the reader to create.
   * @param first the first to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException createWithContext(@NotNull final TextReader reader, @NotNull final Object first) {
    return new CommandSyntaxException(reader.getCursor(), reader.getText(), this.function.apply(first), this);
  }
}
