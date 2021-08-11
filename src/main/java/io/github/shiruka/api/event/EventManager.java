package io.github.shiruka.api.event;

import io.github.shiruka.api.event.events.Event;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine event factory that helps to create and call events.
 */
public interface EventManager {

  /**
   * calls the given event.
   *
   * @param event the event to call.
   */
  void call(@NotNull Event event);

  /**
   * registers the given listener.
   *
   * @param listener the listener to register.
   */
  void register(@NotNull Listener listener);

  /**
   * unregisters the given listener.
   *
   * @param listener the listener to unregister.
   */
  void unregister(@NotNull Listener listener);
}
