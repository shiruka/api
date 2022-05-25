package io.github.shiruka.api.exception;

import io.github.shiruka.api.scheduler.ScheduledTask;
import java.util.Objects;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents server scheduler exceptions.
 */
@Getter
@Accessors(fluent = true)
public final class ServerSchedulerException extends ServerPluginException {

  /**
   * the task.
   */
  @NotNull
  private final ScheduledTask task;

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param task the task.
   */
  public ServerSchedulerException(
    @NotNull final String message,
    @NotNull final Throwable cause,
    @NotNull final ScheduledTask task
  ) {
    super(
      message,
      cause,
      Objects.requireNonNull(task.task().plugin(), "plugin")
    );
    this.task = task;
  }

  /**
   * ctor.
   *
   * @param cause the cause.
   * @param task the task.
   */
  public ServerSchedulerException(
    final Throwable cause,
    final ScheduledTask task
  ) {
    super(cause, Objects.requireNonNull(task.task().plugin(), "plugin"));
    this.task = task;
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param enableSuppression the enable suppression.
   * @param writableStackTrace the writable stack trace.
   * @param task the task.
   */
  public ServerSchedulerException(
    final String message,
    final Throwable cause,
    final boolean enableSuppression,
    final boolean writableStackTrace,
    final ScheduledTask task
  ) {
    super(
      message,
      cause,
      enableSuppression,
      writableStackTrace,
      Objects.requireNonNull(task.task().plugin(), "plugin")
    );
    this.task = task;
  }
}
