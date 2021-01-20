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

package net.shiruka.api.command.arguments;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import net.shiruka.api.command.ArgumentType;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.suggestion.Suggestions;
import org.jetbrains.annotations.NotNull;

/**
 * a simple boolean argument type implementation for {@link ArgumentType}.
 */
public final class BooleanArgumentType implements ArgumentType<Boolean> {

  /**
   * the examples.
   */
  private static final Collection<String> EXAMPLES = List.of("true", "false");

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return BooleanArgumentType.EXAMPLES;
  }

  @NotNull
  @Override
  public Boolean parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    return reader.readBoolean();
  }

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder) {
    final var remaining = builder.getRemaining().toLowerCase(Locale.ROOT);
    if ("true".startsWith(remaining)) {
      builder.suggest("true");
    }
    if ("false".startsWith(remaining)) {
      builder.suggest("false");
    }
    return builder.buildFuture();
  }
}
