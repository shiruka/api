/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
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

package io.github.shiruka.api.scheduler;

import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * generates tasks that are queued for execution in the specified method.
 */
public interface Scheduler {

  /**
   * run a task after a specified time has passed.
   *
   * @param plugin the plugin which the task is registered to.
   * @param async the task will be async or not.
   * @param delay the amount of ticks to wait until the task is executed.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  ScheduledTask later(@NotNull Plugin plugin, boolean async, long delay, @NotNull ScheduledRunnable runnable);

  /**
   * synchronously  run a task after a specified time has passed.
   *
   * @param plugin the plugin which the task is registered to.
   * @param delay the amount of ticks to wait until the task is executed.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask later(@NotNull final Plugin plugin, final long delay,
                              @NotNull final ScheduledRunnable runnable) {
    return this.later(plugin, false, delay, runnable);
  }

  /**
   * run a task after a specified time has passed.
   *
   * @param plugin the plugin which the task is registered to.
   * @param async the task will be async or not.
   * @param delay the amount of ticks to wait until the task is executed.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask later(@NotNull final Plugin plugin, final boolean async, final long delay,
                              @NotNull final Runnable runnable) {
    return this.later(plugin, async, delay, new ScheduledRunnable() {
      @Override
      public void run() {
        runnable.run();
      }
    });
  }

  /**
   * synchronously  run a task after a specified time has passed.
   *
   * @param plugin the plugin which the task is registered to.
   * @param delay the amount of ticks to wait until the task is executed.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask later(@NotNull final Plugin plugin, final long delay, @NotNull final Runnable runnable) {
    return this.later(plugin, false, delay, runnable);
  }

  /**
   * run a task repeatedly.
   *
   * @param plugin the plugin which the task is registered to.
   * @param async the task will be async or not.
   * @param delay the amount of ticks to wait until the task is executed.
   * @param initialInterval the amount of time between each execution which occurs repeatedly until cancelled.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  ScheduledTask repeat(@NotNull Plugin plugin, boolean async, long delay, long initialInterval,
                       @NotNull ScheduledRunnable runnable);

  /**
   * synchronously run a task repeatedly.
   *
   * @param plugin the plugin which the task is registered to.
   * @param delay the amount of ticks to wait until the task is executed.
   * @param initialInterval the amount of time between each execution which occurs repeatedly until cancelled.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask repeat(@NotNull final Plugin plugin, final long delay, final long initialInterval,
                               @NotNull final ScheduledRunnable runnable) {
    return this.repeat(plugin, false, delay, initialInterval, runnable);
  }

  /**
   * run a task repeatedly.
   *
   * @param plugin the plugin which the task is registered to.
   * @param async the task will be async or not.
   * @param delay the amount of ticks to wait until the task is executed.
   * @param initialInterval the amount of time between each execution which occurs repeatedly until cancelled.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask repeat(@NotNull final Plugin plugin, final boolean async, final long delay,
                               final long initialInterval, @NotNull final Runnable runnable) {
    return this.repeat(plugin, async, delay, initialInterval, new ScheduledRunnable() {
      @Override
      public void run() {
        runnable.run();
      }
    });
  }

  /**
   * synchronously run a task repeatedly.
   *
   * @param plugin the plugin which the task is registered to.
   * @param delay the amount of ticks to wait until the task is executed.
   * @param initialInterval the amount of time between each execution which occurs repeatedly until cancelled.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask repeat(@NotNull final Plugin plugin, final long delay, final long initialInterval,
                               @NotNull final Runnable runnable) {
    return this.repeat(plugin, false, delay, initialInterval, runnable);
  }

  /**
   * run a task after the next tick.
   *
   * @param plugin the plugin which the task is registered to.
   * @param runnable the runnable to perform the task.
   * @param async the task will be async or not.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  ScheduledTask run(@NotNull Plugin plugin, boolean async, @NotNull ScheduledRunnable runnable);

  /**
   * synchronously run a task after the next tick.
   *
   * @param plugin the plugin which the task is registered to.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask run(@NotNull final Plugin plugin, @NotNull final ScheduledRunnable runnable) {
    return this.run(plugin, false, runnable);
  }

  /**
   * run a task after the next tick.
   *
   * @param plugin the plugin which the task is registered to.
   * @param runnable the runnable to perform the task.
   * @param async the task will be async or not.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask run(@NotNull final Plugin plugin, final boolean async, @NotNull final Runnable runnable) {
    return this.run(plugin, async, new ScheduledRunnable() {
      @Override
      public void run() {
        runnable.run();
      }
    });
  }

  /**
   * synchronously run a task after the next tick.
   *
   * @param plugin the plugin which the task is registered to.
   * @param runnable the runnable to perform the task.
   *
   * @return the task which was wrapped by the scheduler.
   */
  @NotNull
  default ScheduledTask run(@NotNull final Plugin plugin, @NotNull final Runnable runnable) {
    return this.run(plugin, false, runnable);
  }
}
