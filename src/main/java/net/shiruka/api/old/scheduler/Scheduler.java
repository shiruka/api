package net.shiruka.api.old.scheduler;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import net.shiruka.api.old.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine schedulers.
 */
public interface Scheduler {

  /**
   * calls a method on the main thread and returns a Future object.
   *
   * @param plugin the plugin to call.
   * @param task the task to call.
   * @param <T> callable's return type.
   *
   * @return future object related to the task.
   */
  @NotNull <T> Future<T> callSyncMethod(@NotNull Plugin plugin, @NotNull Callable<T> task);

  /**
   * removes task from scheduler.
   *
   * @param taskId the task id to remove.
   */
  void cancelTask(int taskId);

  /**
   * removes all tasks associated with a particular plugin from the scheduler.
   *
   * @param plugin the plugin to remove.
   */
  void cancelTasks(@NotNull Plugin plugin);

  /**
   * obtains a list of all active workers.
   *
   * @return active workers.
   */
  @NotNull
  List<TaskWorker> getActiveWorkers();

  /**
   * obtains a list of all pending tasks.
   *
   * @return active workers.
   */
  @NotNull
  List<Task> getPendingTasks();

  /**
   * checks if the task currently running.
   *
   * @param taskId the task id to check.
   *
   * @return {@code true} if the task is currently running.
   */
  boolean isCurrentlyRunning(int taskId);

  /**
   * checks if the task queued to be run later.
   *
   * @param taskId the task id to check.
   *
   * @return {@code true} if the task is queued to be run.
   */
  boolean isQueued(int taskId);

  /**
   * executes the given {@code job} on the next tick.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task schedule(@NotNull final Plugin plugin, @NotNull final Runnable job) {
    return this.schedule(plugin, task -> job.run());
  }

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   * @param delay the delay to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task schedule(@NotNull final Plugin plugin, @NotNull final Runnable job, final long delay) {
    return this.schedule(plugin, task -> job.run(), delay);
  }

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to scheduler.
   * @param delay the delay to schedule.
   * @param period the period to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task schedule(@NotNull final Plugin plugin, @NotNull final Runnable job, final long delay,
                        final long period) {
    return this.schedule(plugin, task -> job.run(), delay, period);
  }

  /**
   * executes the given {@code job} on the next tick.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Plugin plugin, @NotNull Consumer<Task> job);

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   * @param delay the delay to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Plugin plugin, @NotNull Consumer<Task> job, long delay);

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to scheduler.
   * @param delay the delay to schedule.
   * @param period the period to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Plugin plugin, @NotNull Consumer<Task> job, long delay, long period);

  /**
   * runs the given {@code job} in another thread.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task scheduleAsync(@NotNull final Plugin plugin, @NotNull final Runnable job) {
    return this.scheduleAsync(plugin, task -> job.run());
  }

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   * @param delay the delay to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task scheduleAsync(@NotNull final Plugin plugin, @NotNull final Runnable job, final long delay) {
    return this.scheduleAsync(plugin, task -> job.run(), delay);
  }

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   * @param delay the delay to schedule.
   * @param period the period to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task scheduleAsync(@NotNull final Plugin plugin, @NotNull final Runnable job, final long delay,
                             final long period) {
    return this.scheduleAsync(plugin, task -> job.run(), delay, period);
  }

  /**
   * runs the given {@code job} in another thread.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Plugin plugin, @NotNull Consumer<Task> job);

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   * @param delay the delay to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Plugin plugin, @NotNull Consumer<Task> job, long delay);

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   * @param delay the delay to schedule.
   * @param period the period to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Plugin plugin, @NotNull Consumer<Task> job, long delay, long period);
}
