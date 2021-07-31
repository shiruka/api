package net.shiruka.api.event;

import net.shiruka.api.event.events.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a interface that represents an object that can handle a given type of event.
 */
@FunctionalInterface
public interface EventSubscriber {

  /**
   * gets if cancelled events should be posted to this subscriber.
   *
   * @return if cancelled events should be posted.
   */
  default boolean consumeCancelledEvents() {
    return true;
  }

  /**
   * gets the post order this subscriber should be called at.
   *
   * @return the post order of this subscriber.
   *
   * @see DispatchOrder
   */
  default DispatchOrder dispatchOrder() {
    return DispatchOrder.MIDDLE;
  }

  /**
   * invokes this event subscriber.
   * <p>
   * called by the event bus when a new event is "posted" to this subscriber.
   *
   * @param event the event that was posted.
   *
   * @throws Throwable any exception thrown during handling.
   */
  void invoke(@NotNull Event event) throws Throwable;

  /**
   * gets the generic type of this subscriber, if it is known.
   *
   * @return the generic type of the subscriber.
   */
  @Nullable
  default Class<?> type() {
    return null;
  }
}
