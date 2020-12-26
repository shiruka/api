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

package io.github.shiruka.api.command.context;

import io.github.shiruka.api.command.CommandNode;
import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import java.util.Collections;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents parse results.
 */
public final class ParseResults {

  /**
   * the builder.
   */
  @NotNull
  private final CommandContextBuilder builder;

  /**
   * the exceptions.
   */
  @NotNull
  private final Map<CommandNode, CommandSyntaxException> exceptions;

  /**
   * the reader.
   */
  @NotNull
  private final TextReader reader;

  /**
   * ctor.
   *
   * @param builder the builder.
   * @param reader the reader.
   * @param exceptions the exceptions.
   */
  public ParseResults(@NotNull final CommandContextBuilder builder, @NotNull final TextReader reader,
                      @NotNull final Map<CommandNode, CommandSyntaxException> exceptions) {
    this.builder = builder;
    this.reader = reader;
    this.exceptions = Collections.unmodifiableMap(exceptions);
  }

  /**
   * ctor.
   *
   * @param builder the builder.
   */
  public ParseResults(@NotNull final CommandContextBuilder builder) {
    this(builder, new TextReader(""), Collections.emptyMap());
  }

  /**
   * obtains the builder.
   *
   * @return builder.
   */
  @NotNull
  public CommandContextBuilder getBuilder() {
    return this.builder;
  }

  /**
   * obtains the exceptions.
   *
   * @return exceptions.
   */
  @NotNull
  public Map<CommandNode, CommandSyntaxException> getExceptions() {
    return this.exceptions;
  }

  /**
   * obtains the reader.
   *
   * @return reader.
   */
  @NotNull
  public TextReader getReader() {
    return this.reader;
  }
}
