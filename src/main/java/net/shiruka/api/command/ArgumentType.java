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
