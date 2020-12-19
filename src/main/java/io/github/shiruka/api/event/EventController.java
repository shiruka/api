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

import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

/**
 * an event controller manages and handles dispatched
 * events and their corresponding listeners.
 */
public interface EventController {

  /**
   * dispatches the event to the event listener/handlers
   * that are registered under the event controller.
   *
   * @param event the event to dispatch.
   * @param <T> the event type.
   */
  <T extends Event> void dispatch(@NotNull T event);

  /**
   * dispatches the event to the event listener/handlers
   * that are registered under the event controller.
   *
   * @param event the event to dispatch.
   * @param callback the callback to execute when the controller finishes processing all listeners.
   * @param <T> the event type.
   */
  <T extends Event> void dispatch(@NotNull T event, @NotNull Consumer<T> callback);

  /**
   * registers the given listener object to receive
   * events dispatched by the controller.
   *
   * @param listener the listener to register.
   */
  void register(@NotNull Listener listener);

  /**
   * removes the given listener from being handling
   * events dispatched by the event controller.
   *
   * @param listener the listener to remove.
   */
  void unregister(@NotNull Class<? extends Listener> listener);
}
