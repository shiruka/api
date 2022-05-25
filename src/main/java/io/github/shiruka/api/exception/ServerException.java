package io.github.shiruka.api.exception;

import org.jetbrains.annotations.NotNull;

/**
 * an exception class that represents wrapper for server exceptions.
 */
public class ServerException extends Exception {

  /**
   * ctor.
   */
  public ServerException() {
    super();
  }

  /**
   * ctor.
   *
   * @param message the message.
   */
  public ServerException(@NotNull final String message) {
    super(message);
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   */
  public ServerException(
    @NotNull final String message,
    @NotNull final Throwable cause
  ) {
    super(message, cause);
  }

  /**
   * ctor.
   *
   * @param cause the cause.
   */
  public ServerException(@NotNull final Throwable cause) {
    super(cause);
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param enableSuppression the enable suppression.
   * @param writableStackTrace the writable stack trace.
   */
  public ServerException(
    @NotNull final String message,
    @NotNull final Throwable cause,
    final boolean enableSuppression,
    final boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
