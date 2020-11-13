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

package io.github.shiruka.api.event;

import io.github.shiruka.api.tools.Events;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * represents an event.
 * <p>
 * all events require a static method named getHandlerList() which
 * returns the same {@link HandlerList} as {@link #getHandlers()}.
 */
public abstract class Event {

  /**
   * the asynchronous.
   */
  private final boolean async;

  /**
   * the event name.
   */
  @Nullable
  private String name;

  /**
   * ctor.
   *
   * @param async true indicates the event will fire asynchronously, false
   *   by default from default constructor.
   */
  protected Event(final boolean async) {
    this.async = async;
  }

  /**
   * ctor.
   */
  protected Event() {
    this(false);
  }

  /**
   * calls the event and tests if cancelled.
   *
   * @return false if event was cancelled, if cancellable. otherwise true.
   */
  public final boolean callEvent() {
    Events.callEvent(this);
    if (this instanceof Cancellable) {
      return !((Cancellable) this).isCancelled();
    }
    return true;
  }

  /**
   * by default, it is the event's class's {@linkplain Class#getSimpleName() simple name}.
   *
   * @return name of this event.
   */
  @NotNull
  public final String getEventName() {
    if (this.name == null) {
      this.name = this.getClass().getSimpleName();
    }
    return this.name;
  }

  /**
   * if the event is asynchronous or not.
   *
   * @return false by default, true if the event fires asynchronously.
   */
  public final boolean isAsynchronous() {
    return this.async;
  }

  /**
   * obtains the handler list.
   *
   * @return the handler list.
   */
  @NotNull
  public abstract HandlerList getHandlers();
}
