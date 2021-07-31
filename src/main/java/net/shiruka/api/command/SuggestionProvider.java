package net.shiruka.api.command;

import java.util.concurrent.CompletableFuture;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.suggestion.Suggestions;
import org.jetbrains.annotations.NotNull;

/**
 * a functional interface to determine suggestion providers.
 */
@FunctionalInterface
public interface SuggestionProvider {

  /**
   * collects the suggestions.
   *
   * @param context the context to suggest.
   * @param builder the builder to suggest.
   *
   * @return completed suggestions future.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  CompletableFuture<Suggestions> getSuggestions(@NotNull CommandContext context,
                                                @NotNull Suggestions.Builder builder) throws CommandSyntaxException;
}
