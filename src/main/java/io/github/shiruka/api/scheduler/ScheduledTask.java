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
 * a class that represents scheduled tasks which implemented inside the scheduler to manage the wrapping for
 * {@link ScheduledRunnable}
 */
public interface ScheduledTask extends Runnable {

  /**
   * cancels the task, only needed for repeating tasks. Scheduled later and run tasks are auto cancelled.
   */
  void cancel();

  /**
   * gets the interval set or created by the task.
   *
   * @return the interval, as defined in {@link ScheduledTask#setInterval(long)}.
   */
  long interval();

  /**
   * the plugin that scheduled the task, or passed in when scheduled.
   *
   * @return the scheduling plugin.
   */
  @NotNull
  Plugin owner();

  /**
   * the execution runnable, invoked when the task is scheduled to occur.
   *
   * @return the runnable that is run at scheduled time.
   */
  @NotNull
  ScheduledRunnable runnable();

  /**
   * interval is the ticks left of a specific action for repeating and delayed tasks.
   *
   * @param interval the interval to set the task to.
   */
  void setInterval(long interval);

  /**
   * the task scheduling type.
   *
   * @return the type the task is scheduled according to.
   */
  @NotNull
  TaskType type();
}
