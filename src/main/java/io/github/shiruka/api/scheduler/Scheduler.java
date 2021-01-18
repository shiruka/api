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

package io.github.shiruka.api.scheduler;

import io.github.shiruka.api.plugin.Plugin;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine schedulers.
 */
public interface Scheduler {

  /**
   * executes the given runnable on the next tick.
   *
   * @param plugin the plugin to schedule.
   * @param runnable the runnable to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Plugin plugin, @NotNull Runnable runnable);

  /**
   * executes a runnable with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param runnable the runnable to schedule.
   * @param delay the delay to schedule.
   * @param timeUnit the time unit to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Plugin plugin, @NotNull Runnable runnable, long delay, @NotNull TimeUnit timeUnit);

  /**
   * executes a runnable with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param runnable the runnable to scheduler.
   * @param delay the delay to schedule.
   * @param period the period to schedule.
   * @param timeUnit the time unit to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Plugin plugin, @NotNull Runnable runnable, long delay, long period, @NotNull TimeUnit timeUnit);

  /**
   * runs the runnable in another thread.
   *
   * @param plugin the plugin to schedule.
   * @param runnable the runnable to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Plugin plugin, @NotNull Runnable runnable);

  /**
   * executes a runnable with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param runnable the runnable to schedule.
   * @param delay the delay to schedule.
   * @param period the period to schedule.
   * @param timeUnit the time unit to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Plugin plugin, @NotNull Runnable runnable, long delay, long period,
                     @NotNull TimeUnit timeUnit);

  /**
   * executes a runnable with a delay.
   *
   * @param plugin the plugin to schedule.
   * @param runnable the runnable to schedule.
   * @param delay the delay to schedule.
   * @param timeUnit the time unit to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Plugin plugin, @NotNull Runnable runnable, long delay, @NotNull TimeUnit timeUnit);
}
