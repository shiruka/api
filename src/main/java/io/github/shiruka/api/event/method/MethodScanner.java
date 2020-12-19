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

package io.github.shiruka.api.event.method;

import io.github.shiruka.api.event.DispatchOrder;
import io.github.shiruka.api.event.EventHandler;
import io.github.shiruka.api.event.Listener;
import java.lang.reflect.Method;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine which methods on a listener should be registered as subscribers,
 * and what properties they should have.
 */
public interface MethodScanner {

  /**
   * default method scanner's instance.
   *
   * @return a new default instance of {@code this}.
   */
  static MethodScanner createDefault() {
    return new MethodScanner() {
    };
  }

  /**
   * gets if cancelled events should be posted to the resultant subscriber.
   *
   * @param listener the listener.
   * @param method the method.
   *
   * @return if cancelled events should be posted.
   */
  default boolean consumeCancelledEvents(@NotNull final Listener listener, @NotNull final Method method) {
    return method.isAnnotationPresent(EventHandler.class) &&
      method.getAnnotation(EventHandler.class).ignoreCancelled();
  }

  /**
   * gets the post order the resultant subscriber should be called at.
   *
   * @param listener the listener.
   * @param method the method.
   *
   * @return the post order of this subscriber.
   *
   * @see io.github.shiruka.api.event.DispatchOrder
   */
  @NotNull
  default DispatchOrder dispatchOrder(@NotNull final Listener listener, @NotNull final Method method) {
    return method.isAnnotationPresent(EventHandler.class)
      ? method.getAnnotation(EventHandler.class).priority()
      : DispatchOrder.MIDDLE;
  }

  /**
   * gets if the factory should generate a subscriber for this method.
   *
   * @param listener the listener being scanned.
   * @param method the method declaration being considered.
   *
   * @return if a subscriber should be registered.
   */
  default boolean shouldRegister(@NotNull final Listener listener, @NotNull final Method method) {
    return method.getAnnotation(EventHandler.class) != null;
  }
}
