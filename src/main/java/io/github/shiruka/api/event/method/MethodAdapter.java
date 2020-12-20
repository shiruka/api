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

import io.github.shiruka.api.event.Event;
import io.github.shiruka.api.event.EventController;
import io.github.shiruka.api.event.Listener;
import java.lang.reflect.Method;
import org.jetbrains.annotations.NotNull;

/**
 * a subscription adapter for {@link EventController} which supports defining event subscribers as methods in a class.
 */
public interface MethodAdapter {

  /**
   * calls the event to the event listener/handlers that are registered under the event controller.
   *
   * @param event the event to dispatch.
   */
  void call(@NotNull Event event);

  /**
   * registers all methods determined to be {@link MethodScanner#shouldRegister(Listener, Method)} on the
   * {@code listener} to receive events.
   *
   * @param listener the listener.
   */
  void register(@NotNull Listener listener);

  /**
   * unregisters all methods on a registered {@code listener}.
   *
   * @param listener the listener.
   */
  void unregister(@NotNull Listener listener);
}
