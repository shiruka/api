package net.shiruka.api.command.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import lombok.Getter;
import net.shiruka.api.command.Command;
import net.shiruka.api.command.CommandException;
import net.shiruka.api.command.CommandNode;
import net.shiruka.api.command.RedirectModifier;
import net.shiruka.api.command.Requirement;
import net.shiruka.api.command.TextRange;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.context.ParseResults;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.suggestion.Suggestions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a simple literal implementation for {@link CommandNodeEnvelope}.
 */
public final class LiteralNode extends CommandNodeEnvelope {

  /**
   * the aliases.
   */
  @NotNull
  @Getter
  private final List<LiteralNode> aliases;

  /**
   * the literal.
   */
  @NotNull
  @Getter
  private final String literal;

  /**
   * ctor.
   *
   * @param aliases the aliases.
   * @param contextRequirement the context requirement.
   * @param defaultCommandNode the default command node.
   * @param description the description.
   * @param fork the forks.
   * @param defaultNode the default node.
   * @param redirectModifier the redirect modifier.
   * @param redirect the redirect.
   * @param requirements the requirement.
   * @param command the command.
   * @param literal the literal.
   * @param usage the usage.
   */
  public LiteralNode(@NotNull final List<LiteralNode> aliases,
                     @NotNull final Predicate<ParseResults> contextRequirement,
                     @Nullable final CommandNode defaultCommandNode, @Nullable final String description,
                     final boolean fork, final boolean defaultNode, @Nullable final RedirectModifier redirectModifier,
                     @Nullable final CommandNode redirect, @NotNull final Set<Requirement> requirements,
                     @Nullable final Command command, @NotNull final String literal, @Nullable final String usage) {
    super(contextRequirement, defaultCommandNode, description, fork, defaultNode, redirectModifier, redirect,
      requirements, usage, command);
    this.aliases = Collections.unmodifiableList(aliases);
    this.literal = literal.toLowerCase(Locale.ROOT);
  }

  @NotNull
  @Override
  public Collection<String> getExamples() {
    return Collections.singleton(this.literal);
  }

  @NotNull
  @Override
  public String getKey() {
    return this.literal;
  }

  @NotNull
  @Override
  public String getName() {
    return this.literal;
  }

  @NotNull
  @Override
  public String getSmartUsage() {
    return this.literal;
  }

  @Override
  public boolean isValidInput(@NotNull final String input) {
    return this.parse(new TextReader(input)) > -1;
  }

  @Override
  public void parse(@NotNull final TextReader reader, @NotNull final CommandContextBuilder builder)
    throws CommandSyntaxException {
    final var start = reader.getCursor();
    final var end = this.parse(reader);
    if (end > -1) {
      builder.withNode(this, TextRange.between(start, end));
      return;
    }
    throw CommandException.LITERAL_INCORRECT.createWithContext(reader, this.literal);
  }

  @NotNull
  @Override
  public CompletableFuture<Suggestions> suggestions(@NotNull final CommandContext context,
                                                    @NotNull final Suggestions.Builder builder)
    throws CommandSyntaxException {
    if (this.literal.regionMatches(true, 0, builder.getRemaining(), 0, builder.getRemaining().length())) {
      return builder.suggest(this.literal).buildFuture();
    }
    return Suggestions.empty();
  }

  @Override
  public int hashCode() {
    var result = this.literal.hashCode();
    result = 31 * result + super.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof LiteralNode)) {
      return false;
    }
    final var that = (LiteralNode) obj;
    return this.literal.equals(that.literal) &&
      super.equals(obj);
  }

  @Override
  public String toString() {
    return "<literal " + this.literal + ">";
  }

  /**
   * parses the given reader.
   *
   * @param reader the reader to read.
   *
   * @return {@code -1} if the reader couldn't read.
   */
  private int parse(@NotNull final TextReader reader) {
    final var start = reader.getCursor();
    if (reader.canRead(this.literal.length())) {
      final var end = start + this.literal.length();
      if (reader.getText().substring(start, end).equals(this.literal)) {
        reader.setCursor(end);
        if (!reader.canRead() || reader.peek() == ' ') {
          return end;
        }
        reader.setCursor(start);
      }
    } else if (!reader.canRead() && this.isDefaultNode()) {
      return start;
    }
    return -1;
  }
}
