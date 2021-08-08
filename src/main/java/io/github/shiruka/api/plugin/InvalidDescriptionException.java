package io.github.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception class that thrown when attempting to load an invalid plugin description file.
 */
public final class InvalidDescriptionException extends Exception {

  /**
   * ctor.
   *
   * @param message the message.
   * @param args the args.
   */
  public InvalidDescriptionException(@NotNull final String message, @NotNull final Object... args) {
    super(message.formatted(args));
  }

  /**
   * ctor.
   *
   * @param cause the throwable.
   */
  public InvalidDescriptionException(@NotNull final Throwable cause) {
    super(cause);
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the throwable.
   */
  public InvalidDescriptionException(@NotNull final String message, @NotNull final Throwable cause) {
    super(message, cause);
  }
}
