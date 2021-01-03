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

import java.util.Locale;

/**
 * type of task that will be run by the scheduler, how it will be run, when it will be scheduled.
 */
public enum TaskType {
  /**
   * asynchronously runs the task the next tick.
   */
  ASYNC_RUN,
  /**
   * asynchronously runs the task later.
   */
  ASYNC_LATER,
  /**
   * asynchronously runs the task repeatedly, until stopped.
   */
  ASYNC_REPEAT,
  /**
   * synchronously runs the task the next tick.
   */
  SYNC_RUN,
  /**
   * synchronously runs the task later.
   */
  SYNC_LATER,
  /**
   * synchronously runs the task repeatedly, until stopped.
   */
  SYNC_REPEAT;

  /**
   * checks if {@code this} is an async task type.
   *
   * @return {@code true} if {@code this} is an async task type.
   */
  public boolean isAsync() {
    return this.name().toLowerCase(Locale.ROOT).contains("async");
  }

  /**
   * checks if {@code this} is an repeat task type.
   *
   * @return {@code true} if {@code this} is an repeat task type.
   */
  public boolean isRepeat() {
    return this.name().toLowerCase(Locale.ROOT).contains("repeat");
  }
}
