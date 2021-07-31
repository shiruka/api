package net.shiruka.api.old.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception that thrown when attempting to load an invalid plugin file.
 */
public class InvalidPluginException extends Exception {

  /**
   * ctor.
   *
   * @param cause Exception that triggered this Exception
   */
  public InvalidPluginException(@NotNull final Throwable cause) {
    super(cause);
  }

  /**
   * ctor.
   */
  public InvalidPluginException() {
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   */
  public InvalidPluginException(@NotNull final String message, @NotNull final Throwable cause) {
    super(message, cause);
  }

  /**
   * ctor.
   *
   * @param message the message.
   */
  public InvalidPluginException(@NotNull final String message) {
    super(message);
  }
}
