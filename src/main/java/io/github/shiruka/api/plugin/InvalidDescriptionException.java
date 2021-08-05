package io.github.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception class that thrown when something wrong with the plugin file.
 */
public final class InvalidDescriptionException extends Exception {

  /**
   * ctor.
   *
   * @param message the message.
   * @param args the args.
   */
  public InvalidDescriptionException(@NotNull final String message, @NotNull final Object... args) {
    super(String.format(message, args));
  }

  /**
   * ctor.
   *
   * @param throwable the throwable.
   */
  public InvalidDescriptionException(@NotNull final Throwable throwable) {
    super(throwable);
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param throwable the throwable.
   */
  public InvalidDescriptionException(@NotNull final String message, @NotNull final Throwable throwable) {
    super(message, throwable);
  }
}
