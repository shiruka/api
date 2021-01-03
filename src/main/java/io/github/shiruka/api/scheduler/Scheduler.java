/*
 * Copyright (c) 2020, GoMint, BlackyPaw and geNAZt
 *
 * This code is licensed under the BSD license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.github.shiruka.api.scheduler;

import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine schedulers.
 */
public interface Scheduler {

  /**
   * executes the given runnable on the next tick.
   *
   * @param runnable the runnable to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Runnable runnable);

  /**
   * executes a runnable with a delay.
   *
   * @param runnable the runnable to schedule.
   * @param delay the delay to schedule.
   * @param timeUnit the time unit to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Runnable runnable, long delay, @NotNull TimeUnit timeUnit);

  /**
   * executes a runnable with a delay.
   *
   * @param runnable the runnable to scheduler.
   * @param delay the delay to schedule.
   * @param period the period to schedule.
   * @param timeUnit the time unit to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task schedule(@NotNull Runnable runnable, long delay, long period, @NotNull TimeUnit timeUnit);

  /**
   * runs the runnable in another thread.
   *
   * @param runnable the runnable to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Runnable runnable);

  /**
   * executes a runnable with a delay.
   *
   * @param runnable the runnable to schedule.
   * @param delay the delay to schedule.
   * @param period the period to schedule.
   * @param timeUnit the time unit to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Runnable runnable, long delay, long period, @NotNull TimeUnit timeUnit);

  /**
   * executes a runnable with a delay.
   *
   * @param runnable the runnable to schedule.
   * @param delay the delay to schedule.
   * @param timeUnit the time unit to schedule.
   *
   * @return scheduled task.
   */
  @NotNull
  Task scheduleAsync(@NotNull Runnable runnable, long delay, @NotNull TimeUnit timeUnit);
}
