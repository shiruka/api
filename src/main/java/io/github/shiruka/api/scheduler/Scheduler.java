package io.github.shiruka.api.scheduler;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine schedulers.
 */
public interface Scheduler {

  /**
   * gets the implementation of async Scheduler.
   *
   * @return implementation of async Scheduler.
   */
  @NotNull
  static Async getAsync() {
    return Shiruka.getAsyncScheduler();
  }

  /**
   * gets the implementation of sync Scheduler.
   *
   * @return implementation of sync Scheduler.
   */
  @NotNull
  static Sync getSync() {
    return Shiruka.getSyncScheduler();
  }

  /**
   * cancels all the task of the plugin.
   *
   * @param plugin the plugin to cancel.
   */
  void cancelTasks(Plugin.Container plugin);

  /**
   * executes the given task.
   *
   * @param task the task to execute.
   */
  void execute(@NotNull Task task);

  /**
   * creates a task builder.
   *
   * @return a newly created task builder.
   */
  @NotNull
  Task.Builder newBuilder();

  /**
   * a marker interface to determine async schedulers.
   */
  interface Async extends Scheduler {

  }

  /**
   * a marker interface to determine sync schedulers.
   */
  interface Sync extends Scheduler {

  }
}
