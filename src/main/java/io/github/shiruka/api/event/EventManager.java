package io.github.shiruka.api.event;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kyori.event.Cancellable;
import net.kyori.event.EventBus;
import net.kyori.event.EventSubscriber;
import net.kyori.event.PostOrders;
import org.apache.commons.lang3.function.FailableConsumer;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine event factory that helps to create and call events.
 */
public interface EventManager {
  /**
   * creates a simple event manager.
   *
   * @return event manager.
   */
  @NotNull
  static EventManager simple() {
    return new Impl();
  }

  /**
   * posts the given event.
   *
   * @param event the event to post.
   *
   * @return {@code true} if the event IS NOT cancelled.
   */
  @CanIgnoreReturnValue
  boolean post(@NotNull Event event);

  /**
   * registers the event.
   *
   * @param eventClass the event class to register.
   * @param run the run to register.
   * @param <E> type of the event class.
   *
   * @return listener.
   *
   * @see PostOrders#NORMAL
   */
  @NotNull
  default <E extends Event> EventListener<E> register(
    @NotNull final Class<E> eventClass,
    @NotNull final FailableConsumer<E, Throwable> run
  ) {
    return this.register(eventClass, PostOrders.NORMAL, run);
  }

  /**
   * registers the event.
   *
   * @param eventClass the event class to register.
   * @param postOrder the post order to register.
   * @param run the run to register.
   * @param <E> type of the event class.
   *
   * @return listener.
   *
   * @see PostOrders
   */
  @NotNull
  default <E extends Event> EventListener<E> register(
    @NotNull final Class<E> eventClass,
    final int postOrder,
    @NotNull final FailableConsumer<E, Throwable> run
  ) {
    return this.register(eventClass, postOrder, true, run);
  }

  /**
   * registers the event.
   *
   * @param eventClass the event class to register.
   * @param postOrder the post order to register.
   * @param acceptsCancelled the accepts cancelled to register.
   * @param run the run to register.
   * @param <E> type of the event class.
   *
   * @return listener.
   *
   * @see PostOrders
   */
  @NotNull
  default <E extends Event> EventListener<E> register(
    @NotNull final Class<E> eventClass,
    final int postOrder,
    final boolean acceptsCancelled,
    @NotNull final FailableConsumer<E, Throwable> run
  ) {
    return this.register(
        eventClass,
        new EventListener<>(run, postOrder, acceptsCancelled)
      );
  }

  /**
   * registers the event.
   *
   * @param eventClass the event class to register.
   * @param listener the listener to register.
   * @param <E> type of the event class.
   *
   * @return listener.
   *
   * @see PostOrders
   */
  @NotNull
  <E extends Event> EventListener<E> register(
    @NotNull Class<E> eventClass,
    @NotNull EventListener<E> listener
  );

  /**
   * unregisters the listener.
   *
   * @param listener the listener to unregister.
   */
  default void unregister(
    @NotNull final EventListener<? extends Event> listener
  ) {
    this.unregisterIf(subscriber -> subscriber.equals(listener));
  }

  /**
   * unregisters if the predicate returns {@code true}.
   *
   * @param predicate the predicate to unregister.
   */
  void unregisterIf(
    @NotNull Predicate<EventSubscriber<? super Event>> predicate
  );

  /**
   * a simple implementation of {@link EventManager}.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements EventManager {

    /**
     * the event bus.
     */
    private final EventBus<Event> eventBus = EventBus.create(Event.class);

    @Override
    public boolean post(@NotNull final Event event) {
      try {
        this.eventBus.post(event).raise();
      } catch (final Exception e) {
        e.printStackTrace();
      }
      return !(event instanceof Cancellable c) || !c.cancelled();
    }

    @NotNull
    @Override
    public <E extends Event> EventListener<E> register(
      @NotNull final Class<E> eventClass,
      @NotNull final EventListener<E> listener
    ) {
      this.eventBus.subscribe(eventClass, listener);
      return listener;
    }

    @Override
    public void unregisterIf(
      @NotNull final Predicate<EventSubscriber<? super Event>> predicate
    ) {
      this.eventBus.unsubscribeIf(predicate);
    }
  }
}
