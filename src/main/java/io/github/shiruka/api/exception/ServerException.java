package io.github.shiruka.api.exception;

import lombok.experimental.StandardException;
import org.jetbrains.annotations.NotNull;

/**
 * an exception class that represents wrapper for server exceptions.
 */
@StandardException
public class ServerException extends Exception {

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
