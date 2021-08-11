package io.github.shiruka.api.event;

import io.github.shiruka.api.event.events.Event;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine generic singular listener.
 *
 * @param <T> type of the event.
 */
public interface GenericListener<T extends Event> extends Listener {

  /**
   * handles the event.
   *
   * @param event the event to handle.
   */
  void handle(@NotNull T event);
}
