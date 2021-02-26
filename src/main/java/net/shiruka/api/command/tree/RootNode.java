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

package net.shiruka.api.command.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.suggestion.Suggestions;
import org.jetbrains.annotations.NotNull;

/**
 * a root command node implementation for {@link CommandNodeEnvelope}.
 */
public final class RootNode extends CommandNodeEnvelope {

  /**
   * ctor.
   */
  public RootNode() {
    super(Collections.emptyList(), null, null, false, false, context -> Collections.singleton(context.getSender()),
      null, Collections.emptySet(), null);
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return Collections.emptyList();
  }

  @NotNull
  @Override
  public String getKey() {
    return "";
  }

  @NotNull
  @Override
  public String getName() {
    return "";
  }

  @NotNull
  @Override
  public String getUsage() {
    return "";
  }

  @Override
  public boolean isValidInput(@NotNull final String input) {
    return false;
  }

  @Override
  public void parse(@NotNull final TextReader reader, @NotNull final CommandContextBuilder builder)
    throws CommandSyntaxException {
  }

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder) throws CommandSyntaxException {
    return Suggestions.empty();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    return this == obj ||
      obj instanceof RootNode && super.equals(obj);
  }

  @Override
  public String toString() {
    return "<root>";
  }
}
