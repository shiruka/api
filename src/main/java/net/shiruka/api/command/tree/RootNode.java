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
    super(parseResults -> true, null, null, false, false, context -> Collections.singleton(context.getSender()), null,
      Collections.emptySet(), null, null);
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
  public String getSmartUsage() {
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
