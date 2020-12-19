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

import io.github.shiruka.api.event.*;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an implementation for {@link EventSubscriber}.
 */
public final class MethodEventSubscriber implements EventSubscriber {

  /**
   * the dispatch order.
   */
  @NotNull
  private final DispatchOrder dispatchOrder;

  /**
   * the event class.
   */
  @NotNull
  private final Class<? extends Event> eventClass;

  /**
   * the executor.
   */
  @NotNull
  private final EventExecutor executor;

  /**
   * the generic.
   */
  @Nullable
  private final Type generic;

  /**
   * the include cancelled.
   */
  private final boolean includeCancelled;

  /**
   * the listener.
   */
  @NotNull
  private final Listener listener;

  /**
   * ctpr.
   *
   * @param eventClass the event class.
   * @param method the method.
   * @param executor the executor.
   * @param listener the listener.
   * @param dispatchOrder the dispatch order.
   * @param includeCancelled the include cancelled.
   */
  MethodEventSubscriber(@NotNull final Class<? extends Event> eventClass, @NotNull final Method method,
                        @NotNull final EventExecutor executor, @NotNull final Listener listener,
                        @NotNull final DispatchOrder dispatchOrder, final boolean includeCancelled) {
    this.eventClass = eventClass;
    this.generic = Event.class.isAssignableFrom(this.eventClass)
      ? MethodEventSubscriber.genericType(method.getGenericParameterTypes()[0])
      : null;
    this.executor = executor;
    this.listener = listener;
    this.dispatchOrder = dispatchOrder;
    this.includeCancelled = includeCancelled;
  }

  /**
   * obtains generic type of the given type.
   *
   * @param type the type to get.
   *
   * @return {@code null} if {@code type} is not {@link ParameterizedType},
   *   otherwise returns the first actual type argument.
   */
  @Nullable
  private static Type genericType(@NotNull final Type type) {
    if (type instanceof ParameterizedType) {
      return ((ParameterizedType) type).getActualTypeArguments()[0];
    }
    return null;
  }

  @Override
  public boolean consumeCancelledEvents() {
    return this.includeCancelled;
  }

  @Override
  public DispatchOrder dispatchOrder() {
    return this.dispatchOrder;
  }

  @Nullable
  @Override
  public Type genericType() {
    return this.generic;
  }

  @Override
  public void invoke(@NotNull final Event event) throws Throwable {
    this.executor.invoke(this.listener, event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.eventClass, this.generic, this.executor, this.listener, this.dispatchOrder,
      this.includeCancelled);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MethodEventSubscriber)) {
      return false;
    }
    final MethodEventSubscriber that = (MethodEventSubscriber) obj;
    return Objects.equals(this.eventClass, that.eventClass)
      && Objects.equals(this.generic, that.generic)
      && Objects.equals(this.executor, that.executor)
      && Objects.equals(this.listener, that.listener)
      && Objects.equals(this.dispatchOrder, that.dispatchOrder)
      && Objects.equals(this.includeCancelled, that.includeCancelled);
  }

  @Override
  public String toString() {
    return "MethodEventSubscriber{" +
      "dispatchOrder=" + this.dispatchOrder +
      ", event=" + this.eventClass +
      ", executor=" + this.executor +
      ", generic=" + this.generic +
      ", includeCancelled=" + this.includeCancelled +
      ", listener=" + this.listener +
      '}';
  }

  /**
   * obtains the listener.
   *
   * @return the listener.
   */
  @NotNull
  Listener listener() {
    return this.listener;
  }
}
