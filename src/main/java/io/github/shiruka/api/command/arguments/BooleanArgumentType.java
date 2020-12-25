// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package io.github.shiruka.api.command.arguments;

import io.github.shiruka.api.command.TextReader;
import io.github.shiruka.api.command.context.CommandContext;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import io.github.shiruka.api.command.suggestion.Suggestions;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
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
    if ("true".startsWith(builder.getRemaining().toLowerCase(Locale.ROOT))) {
      builder.suggest("true");
    }
    if ("false".startsWith(builder.getRemaining().toLowerCase(Locale.ROOT))) {
      builder.suggest("false");
    }
    return builder.buildFuture();
  }
}
