package io.github.shiruka.api.scheduler;

/**
 * an interface to determine scheduled tasks.
 */
public interface ScheduledTask {

  /**
   * cancels the task.
   */
  void cancel();
}
