package io.github.shiruka.api.event.events;

import io.github.shiruka.api.Shiruka;

/**
 * an interface to determine events.
 */
public interface Event {
  /**
   * checks if the event is async.
   *
   * @return {@code false} by default, {@code true} if the event fires asynchronously.
   */
  default boolean async() {
    return false;
  }

  /**
   * calls the event itself.
   *
   * @return {@code true}.
   */
  default boolean callEvent() {
    Shiruka.eventManager().call(this);
    return true;
  }
}
