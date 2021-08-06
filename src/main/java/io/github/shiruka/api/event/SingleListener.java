package io.github.shiruka.api.event;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine singular listener.
 *
 * @param <T> type of the event.
 */
public interface SingleListener<T extends Event> extends Listener {

  /**
   * handles the event.
   *
   * @param event the event to handle.
   */
  void handle(@NotNull T event);
}
