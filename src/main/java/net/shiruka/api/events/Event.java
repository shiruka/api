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

package net.shiruka.api.events;

import net.shiruka.api.Shiruka;
import net.shiruka.api.event.Cancellable;

/**
 * this class represents the superinterface of all classes that are events.
 */
public interface Event {

  /**
   * calls the event itself.
   *
   * @return {@code true} if the event isn't a {@link Cancellable} or the event is a {@link Cancellable} and not
   *   cancelled.
   */
  default boolean callEvent() {
    Shiruka.getEventManager().call(this);
    if (this instanceof Cancellable) {
      return !((Cancellable) this).isCancelled();
    }
    return true;
  }

  /**
   * checks if the event is async.
   *
   * @return {@code false} by default, {@code true} if the event fires asynchronously.
   */
  default boolean isAsync() {
    return false;
  }
}
