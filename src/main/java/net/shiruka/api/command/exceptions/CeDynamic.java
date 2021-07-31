package net.shiruka.api.command.exceptions;

import java.util.function.Function;
import net.shiruka.api.command.CommandException;
import net.shiruka.api.command.TextReader;
import org.jetbrains.annotations.NotNull;

/**
 * a dynamic command implementation for {@link CommandException} by 1 parameter.
 */
public final class CeDynamic implements CommandException<Function<Object, String>> {

  /**
   * the function.
   */
  @NotNull
  private final Function<Object, String> function;

  /**
   * ctor.
   *
   * @param function the function.
   */
  public CeDynamic(@NotNull final Function<Object, String> function) {
    this.function = function;
  }

  /**
   * creates a command syntax exception.
   *
   * @param first the first to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException create(@NotNull final Object first) {
    return new CommandSyntaxException(this.function.apply(first), this);
  }

  /**
   * creates a command syntax exception.
   *
   * @param reader the reader to create.
   * @param first the first to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException createWithContext(@NotNull final TextReader reader, @NotNull final Object first) {
    return new CommandSyntaxException(reader.getCursor(), reader.getText(), this.function.apply(first), this);
  }

  @NotNull
  @Override
  public Function<Object, String> getValue() {
    return this.function;
  }
}
