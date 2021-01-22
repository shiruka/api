/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package net.shiruka.api.scheduler;

import java.util.List;
import java.util.function.Consumer;
import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine schedulers.
 */
public interface Scheduler {

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
   * @param tick the tick to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task schedule(@NotNull final Plugin plugin, @NotNull final Runnable job, final long tick) {
    return this.schedule(plugin, task -> job.run(), tick);
  }

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to scheduler.
   * @param tick the tick to schedule.
   * @param period the period to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task schedule(@NotNull final Plugin plugin, @NotNull final Runnable job, final long tick, final long period) {
    return this.schedule(plugin, task -> job.run(), tick, period);
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
   * @param tick the tick to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Plugin plugin, @NotNull Consumer<Task> job, long tick);

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to scheduler.
   * @param tick the tick to schedule.
   * @param period the period to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Plugin plugin, @NotNull Consumer<Task> job, long tick, long period);

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
   * @param tick the tick to schedule.
   * @param period the period to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task scheduleAsync(@NotNull final Plugin plugin, @NotNull final Runnable job, final long tick,
                             final long period) {
    return this.scheduleAsync(plugin, task -> job.run(), tick, period);
  }

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   * @param tick the tick to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  default Task scheduleAsync(@NotNull final Plugin plugin, @NotNull final Runnable job, final long tick) {
    return this.scheduleAsync(plugin, task -> job.run(), tick);
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
   * @param tick the tick to schedule.
   * @param period the period to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Plugin plugin, @NotNull Consumer<Task> job, long tick, long period);

  /**
   * executes the given {@code job} with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param job the job to schedule.
   * @param tick the tick to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Plugin plugin, @NotNull Consumer<Task> job, long tick);
}
