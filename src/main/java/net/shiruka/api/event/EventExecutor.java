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

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import net.shiruka.api.event.events.Event;
import org.jetbrains.annotations.NotNull;

/**
 * an interface that can invoke a defined method on a listener object when an event is posted.
 */
public interface EventExecutor {

  /**
   * creates an implementation for {@link Factory} using methods.
   *
   * @return a simple instance for {@link Factory}.
   */
  static Factory createFactory() {
    return (object, method) -> {
      final var handle = MethodHandles.publicLookup()
        .unreflect(method)
        .bindTo(object);
      return (listener, event) -> handle.invoke(event);
    };
  }

  /**
   * invokes the appropriate method on the given listener to handle the event.
   *
   * @param listener the listener.
   * @param event the event.
   *
   * @throws Throwable if an exception occurred.
   */
  void invoke(@NotNull Listener listener, @NotNull Event event) throws Throwable;

  /**
   * factory for {@link EventExecutor}s.
   */
  interface Factory {

    /**
     * creates an event executor.
     *
     * @param object the listener object.
     * @param method the method to call on the object.
     *
     * @return an event executor.
     *
     * @throws Exception if an exception occurred while creating an executor.
     */
    @NotNull
    EventExecutor create(@NotNull Object object, @NotNull Method method) throws Exception;
  }
}
