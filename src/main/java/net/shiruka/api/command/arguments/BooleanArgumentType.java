package net.shiruka.api.command.arguments;

import java.util.Collection;
import java.util.List;
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
    final var remaining = builder.getRemainingToLowerCase();
    if ("true".startsWith(remaining)) {
      builder.suggest("true");
    }
    if ("false".startsWith(remaining)) {
      builder.suggest("false");
    }
    return builder.buildFuture();
  }
}
