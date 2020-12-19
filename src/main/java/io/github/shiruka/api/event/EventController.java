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

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

/**
 * an event controller manages and handles dispatched events and their corresponding listeners.
 */
public interface EventController {

  /**
   * calls the event to the event listener/handlers that are registered under the event controller.
   *
   * @param event the event to dispatch.
   *
   * @return a completable future instance.
   */
  CompletableFuture<PostResult> call(@NotNull Event event);

  /**
   * registers the given {@code subscriber} to receive events.
   *
   * @param eventClass the event class to register.
   * @param subscriber the subscriber to register.
   */
  void register(@NotNull Class<? extends Event> eventClass, @NotNull EventSubscriber subscriber);

  /**
   * unregisters a previously registered {@code subscriber}.
   *
   * @param subscriber the subscriber.
   */
  void unregister(@NotNull EventSubscriber subscriber);

  /**
   * unregisters all subscribers matching the {@code predicate}.
   *
   * @param predicate the predicate to test subscribers for removal.
   */
  void unregister(@NotNull Predicate<EventSubscriber> predicate);
}
