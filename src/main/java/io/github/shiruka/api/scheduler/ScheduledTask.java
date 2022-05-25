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
   * obtains the id.
   *
   * @return id.
   */
  int id();

  /**
   * obtains the task.
   *
   * @return task.
   */
  @NotNull
  Task task();
}
