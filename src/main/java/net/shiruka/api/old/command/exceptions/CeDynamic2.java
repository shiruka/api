package net.shiruka.api.old.command.exceptions;

import java.util.function.BiFunction;
import net.shiruka.api.old.command.CommandException;
import net.shiruka.api.old.command.TextReader;
import org.jetbrains.annotations.NotNull;

/**
 * a dynamic command implementation for {@link CommandException} by 2 parameters.
 */
public final class CeDynamic2 implements CommandException<BiFunction<Object, Object, String>> {

  /**
   * the function.
   */
  @NotNull
  private final BiFunction<Object, Object, String> function;

  /**
   * ctor.
   *
   * @param function the function.
   */
  public CeDynamic2(@NotNull final BiFunction<Object, Object, String> function) {
    this.function = function;
  }

  /**
   * creates a command syntax exception.
   *
   * @param first the first to create.
   * @param second the second to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException create(@NotNull final Object first, @NotNull final Object second) {
    return new CommandSyntaxException(this.function.apply(first, second), this);
  }

  /**
   * creates a command syntax exception.
   *
   * @param reader the reader to create.
   * @param first the first to create.
   * @param second the second to create.
   *
   * @return a new command syntax exception instance.
   */
  @NotNull
  public CommandSyntaxException createWithContext(@NotNull final TextReader reader, @NotNull final Object first,
                                                  @NotNull final Object second) {
    return new CommandSyntaxException(reader.getCursor(), reader.getText(), this.function.apply(first, second),
      this);
  }

  @NotNull
  @Override
  public BiFunction<Object, Object, String> getValue() {
    return this.function;
  }
}
