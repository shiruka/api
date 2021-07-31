package net.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception that thrown when attempting to load an invalid plugin file.
 */
public class UnknownDependencyException extends RuntimeException {

  /**
   * ctor.
   *
   * @param cause the throwable.
   */
  public UnknownDependencyException(@NotNull final Throwable cause) {
    super(cause);
  }

  /**
   * ctor.
   *
   * @param message the message.
   */
  public UnknownDependencyException(@NotNull final String message) {
    super(message);
  }

  /**
   * ctor.
   *
   * @param cause the cause.
   * @param message the message.
   */
  public UnknownDependencyException(@NotNull final Throwable cause, @NotNull final String message) {
    super(message, cause);
  }

  /**
   * ctor.
   */
  public UnknownDependencyException() {
  }
}
