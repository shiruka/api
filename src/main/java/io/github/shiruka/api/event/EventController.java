package io.github.shiruka.api.event;

import io.github.shiruka.api.event.events.Event;
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
   * @return post result.
   */
  @NotNull
  PostResult call(@NotNull Event event);

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
