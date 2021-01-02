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

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a wrapper over {@link java.lang.Runnable} that provides access to the scheduling facilities after scheduled.
 */
public abstract class ScheduledRunnable implements Runnable {

  /**
   * the current id.
   */
  private static final AtomicInteger currentId = new AtomicInteger();

  /**
   * the id.
   */
  private final int id;

  /**
   * the task.
   */
  @Nullable
  private volatile ScheduledTask task;

  /**
   * ctor.
   */
  public ScheduledRunnable() {
    this.id = ScheduledRunnable.currentId.addAndGet(1);
  }

  /**
   * the scheduled representation of the runnable.
   *
   * @return the {@link ScheduledTask} object held within the scheduling implementation.
   */
  @NotNull
  public final ScheduledTask asScheduledTask() {
    return Objects.requireNonNull(this.task);
  }

  /**
   * cancels the task and removes from execution. See {@link ScheduledTask#cancel()}
   */
  public final void cancel() {
    Objects.requireNonNull(this.task).cancel();
  }

  /**
   * used internally to refer to this runnable, probably shouldn't be used by plugins.
   *
   * @return the id.
   */
  public final int id() {
    return this.id;
  }

  /**
   * gets how long between runs this is supposed to wait if it is a repeating task.
   *
   * @return interval value.
   */
  public final long interval() {
    return Objects.requireNonNull(this.task).interval();
  }

  /**
   * sets the task.
   *
   * @param task the task to set.
   */
  public final void markSchedule(@NotNull final ScheduledTask task) {
    this.task = task;
  }

  /**
   * sets how long this runnable should wait between executions if this is a repeating task.
   *
   * @param interval the interval to set.
   */
  public final void setInterval(final long interval) {
    Objects.requireNonNull(this.task).setInterval(interval);
  }

  /**
   * runs after this runnable has finished.
   */
  public void afterRun() {
  }

  /**
   * guaranteed to be run before this Runnable.
   */
  public void beforeRun() {
  }
}
