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

package net.shiruka.api.events.server.exception;

import java.lang.annotation.Native;
import java.util.Objects;
import net.shiruka.api.scheduler.Task;
import org.jetbrains.annotations.NotNull;

/**
 * thrown when a plugin's scheduler fails with an exception.
 */
public class ServerSchedulerException extends ServerPluginException {

  /**
   * the task.
   */
  @Native
  private final Task task;

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param task the task.
   */
  public ServerSchedulerException(@NotNull final String message, @NotNull final Throwable cause,
                                  @NotNull final Task task) {
    super(message, cause, Objects.requireNonNull(task.getOwner(), "task's owner"));
    this.task = task;
  }

  /**
   * ctor.
   *
   * @param cause the cause.
   * @param task the task.
   */
  public ServerSchedulerException(@NotNull final Throwable cause, @NotNull final Task task) {
    super(cause, Objects.requireNonNull(task.getOwner(), "task's owner"));
    this.task = task;
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param enableSuppression the enable suppression.
   * @param writableStackTrace the writable stack trace.
   * @param task the task.
   */
  protected ServerSchedulerException(@NotNull final String message, @NotNull final Throwable cause,
                                     final boolean enableSuppression, final boolean writableStackTrace,
                                     @NotNull final Task task) {
    super(message, cause, enableSuppression, writableStackTrace,
      Objects.requireNonNull(task.getOwner(), "task's owner"));
    this.task = task;
  }

  /**
   * obtains the task.
   *
   * @return task.
   */
  @NotNull
  public Task getTask() {
    return this.task;
  }
}
