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

package net.shiruka.api.event;

import net.shiruka.api.event.events.Event;

/**
 * represents an event that can be cancelled and thus cause the dispatcher to take a different course of action than
 * was initially planned.
 */
public interface Cancellable extends Event {

  /**
   * calls the event itself.
   *
   * @return {@code true} if the event is not cancelled.
   */
  @Override
  default boolean callEvent() {
    return Event.super.callEvent() &&
      !this.isCancelled();
  }

  /**
   * obtains the cancel state of the event.
   *
   * @return {@code true} if the event has been cancelled.
   */
  boolean isCancelled();

  /**
   * cancels state of the event.
   *
   * @param cancelled the cancelled to set.
   */
  void setCancelled(boolean cancelled);
}
