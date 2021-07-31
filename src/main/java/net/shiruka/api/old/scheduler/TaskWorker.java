package net.shiruka.api.old.scheduler;

import net.shiruka.api.old.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine task workers.
 */
public interface TaskWorker {

  /**
   * obtains the plugin.
   *
   * @return plugin.
   */
  @NotNull
  Plugin getOwner();

  /**
   * obtains the task id.
   *
   * @return task id.
   */
  int getTaskId();

  /**
   * obtains the thread.
   *
   * @return thread.
   */
  @NotNull
  Thread getThread();
}
