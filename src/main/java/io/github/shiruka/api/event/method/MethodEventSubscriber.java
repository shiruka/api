package io.github.shiruka.api.event.method;

import io.github.shiruka.api.event.EventExecutor;
import io.github.shiruka.api.event.EventSubscriber;
import io.github.shiruka.api.event.Listener;
import io.github.shiruka.api.event.events.Event;
import java.lang.reflect.Method;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an implementation for {@link EventSubscriber}.
 */
@ToString
@Accessors(fluent = true)
@EqualsAndHashCode
public final class MethodEventSubscriber implements EventSubscriber {

  /**
   * the accepts cancelled.
   */
  @Getter
  private final boolean acceptsCancelled;

  /**
   * the dispatch order.
   */
  @Getter
  private final int dispatchOrder;

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
   * the listener.
   */
  @NotNull
  @Getter(AccessLevel.PACKAGE)
  private final Listener listener;

  /**
   * the generic.
   */
  @Nullable
  @Getter
  private final Class<?> type;

  /**
   * ctor.
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
                        final int dispatchOrder, final boolean ignoreCancelled) {
    this.eventClass = eventClass;
    this.type = method.getParameterTypes()[0];
    this.executor = executor;
    this.listener = listener;
    this.dispatchOrder = dispatchOrder;
    this.acceptsCancelled = ignoreCancelled;
  }

  @Override
  public void invoke(@NotNull final Event event) throws Throwable {
    this.executor.invoke(this.listener, event);
  }
}
