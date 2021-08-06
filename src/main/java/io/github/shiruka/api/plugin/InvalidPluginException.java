package io.github.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception class that thrown when attempting to load an invalid plugin file.
 */
public final class InvalidPluginException extends Exception {

  /**
   * ctor.
   *
   * @param cause the cause.
   */
  public InvalidPluginException(@NotNull final Throwable cause) {
    super(cause);
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param args the args.
   */
  public InvalidPluginException(@NotNull final String message, @NotNull final Object... args) {
    super(message.formatted(args));
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param args the args.
   */
  public InvalidPluginException(@NotNull final String message, @NotNull final Throwable cause,
                                @NotNull final Object... args) {
    super(message.formatted(args), cause);
  }
}
