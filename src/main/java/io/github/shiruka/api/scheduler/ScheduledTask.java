package io.github.shiruka.api.scheduler;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine scheduled tasks.
 */
public interface ScheduledTask {

  /**
   * cancels the task.
   */
  void cancel();

  /**
   * obtains the status.
   *
   * @return status.
   */
  @NotNull
  Status status();

  /**
   * obtains the task.
   *
   * @return task.
   */
  @NotNull
  Task task();

  /**
   * an enum class that contains status of scheduled tasks.
   */
  enum Status {
    /**
     * the running.
     */
    RUNNING,
    /**
     * the cancelled.
     */
    CANCELLED,
    /**
     * the finished.
     */
    FINISHED
  }
}
