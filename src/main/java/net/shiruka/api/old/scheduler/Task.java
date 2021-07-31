package net.shiruka.api.old.scheduler;

import net.shiruka.api.old.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine tasks.
 */
public interface Task {

  /**
   * will attempt to cancel this task.
   */
  void cancel();

  /**
   * obtains the plugin.
   *
   * @return plugin.
   */
  @Nullable
  Plugin getOwner();

  /**
   * obtains the task id.
   *
   * @return task id.
   */
  int getTaskId();

  /**
   * checks if the task is cancelled.
   *
   * @return {@code true} if the task has been cancelled.
   */
  boolean isCancelled();

  /**
   * checks if the task is sync.
   *
   * @return {@code true} if the task is run by main thread.
   */
  boolean isSync();
}
