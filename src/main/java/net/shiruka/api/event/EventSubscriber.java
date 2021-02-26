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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a interface that represents an object that can handle a given type of event.
 */
@FunctionalInterface
public interface EventSubscriber {

  /**
   * gets if cancelled events should be posted to this subscriber.
   *
   * @return if cancelled events should be posted.
   */
  default boolean consumeCancelledEvents() {
    return true;
  }

  /**
   * gets the post order this subscriber should be called at.
   *
   * @return the post order of this subscriber.
   *
   * @see DispatchOrder
   */
  default DispatchOrder dispatchOrder() {
    return DispatchOrder.MIDDLE;
  }

  /**
   * invokes this event subscriber.
   * <p>
   * called by the event bus when a new event is "posted" to this subscriber.
   *
   * @param event the event that was posted.
   *
   * @throws Throwable any exception thrown during handling.
   */
  void invoke(@NotNull Event event) throws Throwable;

  /**
   * gets the generic type of this subscriber, if it is known.
   *
   * @return the generic type of the subscriber.
   */
  @Nullable
  default Class<?> type() {
    return null;
  }
}
