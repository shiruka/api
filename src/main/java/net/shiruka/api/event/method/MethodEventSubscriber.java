package net.shiruka.api.event.method;

import java.lang.reflect.Method;
import java.util.Objects;
import net.shiruka.api.event.DispatchOrder;
import net.shiruka.api.event.EventExecutor;
import net.shiruka.api.event.EventSubscriber;
import net.shiruka.api.event.Listener;
import net.shiruka.api.event.events.Event;
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
   * the include cancelled.
   */
  private final boolean ignoreCancelled;

  /**
   * the listener.
   */
  @NotNull
  private final Listener listener;

  /**
   * the generic.
   */
  @Nullable
  private final Class<?> type;

  /**
   * ctpr.
   *
   * @param eventClass the event class.
   * @param method the method.
   * @param executor the executor.
   * @param listener the listener.
   * @param dispatchOrder the dispatch order.
   * @param ignoreCancelled the include cancelled.
   */
  MethodEventSubscriber(@NotNull final Class<? extends Event> eventClass, @NotNull final Method method,
                        @NotNull final EventExecutor executor, @NotNull final Listener listener,
                        @NotNull final DispatchOrder dispatchOrder, final boolean ignoreCancelled) {
    this.eventClass = eventClass;
    this.type = method.getParameterTypes()[0];
    this.executor = executor;
    this.listener = listener;
    this.dispatchOrder = dispatchOrder;
    this.ignoreCancelled = ignoreCancelled;
  }

  @Override
  public boolean consumeCancelledEvents() {
    return this.ignoreCancelled;
  }

  @Override
  public DispatchOrder dispatchOrder() {
    return this.dispatchOrder;
  }

  @Override
  public void invoke(@NotNull final Event event) throws Throwable {
    this.executor.invoke(this.listener, event);
  }

  @Nullable
  @Override
  public Class<?> type() {
    return this.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.eventClass, this.type, this.executor, this.listener, this.dispatchOrder,
      this.ignoreCancelled);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MethodEventSubscriber)) {
      return false;
    }
    final var that = (MethodEventSubscriber) obj;
    return Objects.equals(this.eventClass, that.eventClass)
      && Objects.equals(this.type, that.type)
      && Objects.equals(this.executor, that.executor)
      && Objects.equals(this.listener, that.listener)
      && Objects.equals(this.dispatchOrder, that.dispatchOrder)
      && Objects.equals(this.ignoreCancelled, that.ignoreCancelled);
  }

  @Override
  public String toString() {
    return "MethodEventSubscriber{" +
      "dispatchOrder=" + this.dispatchOrder +
      ", event=" + this.eventClass +
      ", executor=" + this.executor +
      ", generic=" + this.type +
      ", includeCancelled=" + this.ignoreCancelled +
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
