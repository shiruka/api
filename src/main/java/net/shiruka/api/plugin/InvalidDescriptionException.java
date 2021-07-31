package net.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception that thrown when attempting to load an invalid {@link PluginDescriptionFile}.
 */
public final class InvalidDescriptionException extends Exception {

  /**
   * ctor.
   *
   * @param message the message to print.
   * @param cause the cause to print.
   */
  InvalidDescriptionException(@NotNull final Throwable cause, @NotNull final String message) {
    super(message, cause);
  }

  /**
   * ctor.
   *
   * @param message the message to print.
   */
  InvalidDescriptionException(@NotNull final String message) {
    super(message);
  }
}
