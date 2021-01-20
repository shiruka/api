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

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.suggestion.Suggestions;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine argument types.
 *
 * @param <V> type of the argument.
 */
public interface ArgumentType<V> {

  /**
   * obtains the examples.
   *
   * @return examples.
   */
  @NotNull
  default Collection<String> getExamples() {
    return Collections.emptyList();
  }

  /**
   * parses the given reader into the {@code V} value.
   *
   * @param reader the reader to parse.
   *
   * @return the parsed {@code V} value.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  V parse(@NotNull TextReader reader) throws CommandSyntaxException;

  /**
   * collects the suggestion list.
   *
   * @param context the context to collect.
   * @param builder the builder to collect.
   *
   * @return collected suggestion list.
   */
  @NotNull
  default CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                     @NotNull final Suggestions.Builder builder) {
    return Suggestions.empty();
  }
}
