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

package net.shiruka.api.event.method;

import java.util.Arrays;
import java.util.function.BiConsumer;
import net.shiruka.api.event.EventController;
import net.shiruka.api.event.EventExecutor;
import net.shiruka.api.event.EventSubscriber;
import net.shiruka.api.event.Listener;
import net.shiruka.api.event.PostResult;
import net.shiruka.api.event.SimpleEventController;
import net.shiruka.api.event.events.Event;
import org.jetbrains.annotations.NotNull;

/**
 * A simple implementation of a method subscription adapter.
 */
public final class SimpleMethodAdapter implements MethodAdapter {

  /**
   * the event controller.
   */
  @NotNull
  private final EventController controller;

  /**
   * the factory.
   */
  @NotNull
  private final EventExecutor.Factory factory;

  /**
   * the method scanner.
   */
  @NotNull
  private final MethodScanner methodScanner;

  /**
   * ctor.
   *
   * @param controller the event controller.
   * @param factory the factory.
   * @param methodScanner the method scanner.
   */
  public SimpleMethodAdapter(@NotNull final EventController controller, @NotNull final EventExecutor.Factory factory,
                             @NotNull final MethodScanner methodScanner) {
    this.controller = controller;
    this.factory = factory;
    this.methodScanner = methodScanner;
  }

  /**
   * ctor.
   *
   * @param controller the event controller.
   * @param factory the factory.
   */
  public SimpleMethodAdapter(@NotNull final EventController controller, @NotNull final EventExecutor.Factory factory) {
    this(controller, factory, MethodScanner.createDefault());
  }

  /**
   * ctor.
   *
   * @param factory the factory.
   */
  public SimpleMethodAdapter(@NotNull final EventExecutor.Factory factory) {
    this(new SimpleEventController(), factory);
  }

  /**
   * ctor.
   */
  public SimpleMethodAdapter() {
    this(EventExecutor.createFactory());
  }

  @Override
  public void call(@NotNull final Event event) {
    this.controller.call(event)
      .thenAccept(PostResult::raise);
  }

  @Override
  public void register(@NotNull final Listener listener) {
    this.findSubscribers(listener, this.controller::register);
  }

  @Override
  public void unregister(@NotNull final Listener listener) {
    this.controller.unregister(h ->
      h instanceof MethodEventSubscriber &&
        ((MethodEventSubscriber) h).listener() == listener);
  }

  /**
   * finds the subscribers of the given listener instance.
   *
   * @param listener the listener to find.
   * @param consumer the consumer to run when a subscriber is found.
   */
  private void findSubscribers(@NotNull final Listener listener,
                               @NotNull final BiConsumer<Class<? extends Event>, EventSubscriber> consumer) {
    Arrays.stream(listener.getClass().getDeclaredMethods())
      .filter(method -> this.methodScanner.shouldRegister(listener, method))
      .filter(method -> {
        if (method.getParameterCount() != 1) {
          throw new SubscriberGenerationException(String.format(
            "Unable to create an event subscriber for method '%s'. Method must have only one parameter.",
            method));
        }
        return true;
      })
      .forEach(method -> {
        final var methodParameterType = method.getParameterTypes()[0];
        if (!Event.class.isAssignableFrom(methodParameterType)) {
          throw new SubscriberGenerationException(String.format(
            "Unable to create an event subscriber for method '%s'. " +
              "Method parameter type '%s' does not extend event type '%s",
            method, methodParameterType, Event.class));
        }
        try {
          final var executor = this.factory.create(listener, method);
          //noinspection unchecked
          final var eventClass = (Class<? extends Event>) methodParameterType;
          final var subscriber = new MethodEventSubscriber(eventClass, method, executor, listener,
            this.methodScanner.dispatchOrder(listener, method),
            this.methodScanner.consumeCancelledEvents(listener, method));
          consumer.accept(eventClass, subscriber);
        } catch (final Exception e) {
          throw new SubscriberGenerationException(String.format(
            "Encountered an exception while creating an event subscriber for method '%s'",
            method), e);
        }
      });
  }
}
