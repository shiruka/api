package net.shiruka.api.old.command.tree;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import lombok.Getter;
import net.shiruka.api.old.command.ArgumentType;
import net.shiruka.api.old.command.Command;
import net.shiruka.api.old.command.CommandNode;
import net.shiruka.api.old.command.RedirectModifier;
import net.shiruka.api.old.command.Requirement;
import net.shiruka.api.old.command.SuggestionProvider;
import net.shiruka.api.old.command.TextReader;
import net.shiruka.api.old.command.context.CommandContext;
import net.shiruka.api.old.command.context.CommandContextBuilder;
import net.shiruka.api.old.command.context.ParseResults;
import net.shiruka.api.old.command.context.ParsedArgument;
import net.shiruka.api.old.command.exceptions.CommandSyntaxException;
import net.shiruka.api.old.command.suggestion.Suggestions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a simple dynamic implementation for {@link CommandNodeEnvelope}.
 *
 * @param <V> type of the argument value.
 */
public final class ArgumentNode<V> extends CommandNodeEnvelope {

  /**
   * the usage argument close.
   */
  private static final String USAGE_ARGUMENT_CLOSE = ">";

  /**
   * the usage argument open.
   */
  private static final String USAGE_ARGUMENT_OPEN = "<";

  /**
   * the default value.
   */
  @Nullable
  @Getter
  private final V defaultValue;

  /**
   * the name.
   */
  @NotNull
  @Getter
  private final String name;

  /**
   * the suggestion override.
   */
  @Nullable
  private final SuggestionProvider suggestions;

  /**
   * the type.
   */
  @NotNull
  @Getter
  private final ArgumentType<V> type;

  /**
   * ctor.
   *
   * @param contextRequirement the context requirement.
   * @param defaultCommandNode the default command node.
   * @param defaultValue the default value.
   * @param description the description.
   * @param fork the forks.
   * @param defaultNode the default node.
   * @param redirectModifier the redirect modifier.
   * @param redirect the redirect.
   * @param requirements the requirements.
   * @param command the command.
   * @param name the name.
   * @param suggestions the suggestion override.
   * @param type the type.
   * @param usage the usage.
   */
  public ArgumentNode(@NotNull final Predicate<ParseResults> contextRequirement,
                      @Nullable final CommandNode defaultCommandNode, @Nullable final V defaultValue,
                      @Nullable final String description, final boolean fork, final boolean defaultNode,
                      @Nullable final RedirectModifier redirectModifier, @Nullable final CommandNode redirect,
                      @NotNull final Set<Requirement> requirements, @Nullable final Command command,
                      @NotNull final String name, @Nullable final SuggestionProvider suggestions,
                      @NotNull final ArgumentType<V> type, @Nullable final String usage) {
    super(contextRequirement, defaultCommandNode, description, fork, defaultNode, redirectModifier, redirect,
      requirements, usage, command);
    this.defaultValue = defaultValue;
    this.name = name;
    this.suggestions = suggestions;
    this.type = type;
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return this.type.getExamples();
  }

  @NotNull
  @Override
  public String getKey() {
    return this.name;
  }

  @NotNull
  @Override
  public String getSmartUsage() {
    return ArgumentNode.USAGE_ARGUMENT_OPEN + this.name + ArgumentNode.USAGE_ARGUMENT_CLOSE;
  }

  @Override
  public boolean isValidInput(@NotNull final String input) throws CommandSyntaxException {
    final var reader = new TextReader(input);
    this.type.parse(reader);
    return !reader.canRead() || reader.peek() == ' ';
  }

  @Override
  public void parse(@NotNull final TextReader reader, @NotNull final CommandContextBuilder builder)
    throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var result = reader.canRead() || !this.isDefaultNode()
      ? this.type.parse(reader)
      : this.defaultValue;
    final var parsed = new ParsedArgument<>(start, reader.getCursor(), Objects.requireNonNull(result, "result"));
    builder.withArgument(this.name, parsed);
    builder.withNode(this, parsed.getRange());
  }

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder)
    throws CommandSyntaxException {
    if (this.suggestions == null) {
      return this.type.suggestions(context, builder);
    }
    return this.suggestions.getSuggestions(context, builder);
  }

  @Override
  public int hashCode() {
    var result = this.name.hashCode();
    result = 31 * result + this.type.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ArgumentNode<?>)) {
      return false;
    }
    final var that = (ArgumentNode<?>) obj;
    return this.name.equals(that.name) &&
      this.type.equals(that.type) &&
      super.equals(obj);
  }

  @Override
  public String toString() {
    return "<argument " + this.name + ":" + this.type + ">";
  }
}
