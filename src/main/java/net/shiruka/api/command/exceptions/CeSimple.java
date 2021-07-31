package net.shiruka.api.command.exceptions;

import net.shiruka.api.command.CommandException;
import net.shiruka.api.command.TextReader;
import org.jetbrains.annotations.NotNull;

/**
 * a simple implementation for {@link CommandException}.
 */
public final class CeSimple implements CommandException<String> {

  /**
   * the message.
   */
  @NotNull
  private final String message;

  /**
   * ctor.
   *
   * @param message the message.
   */
  public CeSimple(@NotNull final String message) {
    this.message = message;
  }

  /**
   * creates a command syntax exception.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException create() {
    return new CommandSyntaxException(this.message, this);
  }

  /**
   * creates a command syntax exception with context.
   *
   * @param reader the reader to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException createWithContext(@NotNull final TextReader reader) {
    return new CommandSyntaxException(reader.getCursor(), reader.getText(), this.message, this);
  }

  @NotNull
  @Override
  public String getValue() {
    return this.message;
  }

  @NotNull
  @Override
  public String toString() {
    return this.message;
  }
}
