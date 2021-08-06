package io.github.shiruka.api.event;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine singular listener.
 */
public interface SingleListener<T extends Event> extends Listener {

  /**
   * handles the event.
   *
   * @param event the event to handle.
   */
  void handle(@NotNull final T event);
}
