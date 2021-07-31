package net.shiruka.api.event.events.server.exception;

import java.lang.annotation.Native;
import java.util.Objects;
import net.shiruka.api.scheduler.Task;
import org.jetbrains.annotations.NotNull;

/**
 * thrown when a plugin's scheduler fails with an exception.
 */
public class ServerSchedulerException extends ServerPluginException {

  /**
   * the task.
   */
  @Native
  private final Task task;

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param task the task.
   */
  public ServerSchedulerException(@NotNull final String message, @NotNull final Throwable cause,
                                  @NotNull final Task task) {
    super(message, cause, Objects.requireNonNull(task.getOwner(), "task's owner"));
    this.task = task;
  }

  /**
   * ctor.
   *
   * @param cause the cause.
   * @param task the task.
   */
  public ServerSchedulerException(@NotNull final Throwable cause, @NotNull final Task task) {
    super(cause, Objects.requireNonNull(task.getOwner(), "task's owner"));
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
  protected ServerSchedulerException(@NotNull final String message, @NotNull final Throwable cause,
                                     final boolean enableSuppression, final boolean writableStackTrace,
                                     @NotNull final Task task) {
    super(message, cause, enableSuppression, writableStackTrace,
      Objects.requireNonNull(task.getOwner(), "task's owner"));
    this.task = task;
  }

  /**
   * obtains the task.
   *
   * @return task.
   */
  @NotNull
  public Task getTask() {
    return this.task;
  }
}
