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

import java.util.function.BiFunction;
import net.shiruka.api.command.CommandException;
import net.shiruka.api.command.TextReader;
import org.jetbrains.annotations.NotNull;

/**
 * a dynamic command implementation for {@link CommandException} by 2 parameters.
 */
public final class CeDynamic2 implements CommandException<BiFunction<Object, Object, String>> {

  /**
   * the function.
   */
  @NotNull
  private final BiFunction<Object, Object, String> function;

  /**
   * ctor.
   *
   * @param function the function.
   */
  public CeDynamic2(@NotNull final BiFunction<Object, Object, String> function) {
    this.function = function;
  }

  /**
   * creates a command syntax exception.
   *
   * @param first the first to create.
   * @param second the second to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException create(@NotNull final Object first, @NotNull final Object second) {
    return new CommandSyntaxException(this.function.apply(first, second), this);
  }

  /**
   * creates a command syntax exception.
   *
   * @param reader the reader to create.
   * @param first the first to create.
   * @param second the second to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException createWithContext(@NotNull final TextReader reader, @NotNull final Object first,
                                                  @NotNull final Object second) {
    return new CommandSyntaxException(reader.getCursor(), reader.getText(), this.function.apply(first, second),
      this);
  }

  @NotNull
  @Override
  public BiFunction<Object, Object, String> getValue() {
    return this.function;
  }
}
