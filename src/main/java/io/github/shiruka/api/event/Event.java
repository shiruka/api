package io.github.shiruka.api.event;

import io.github.shiruka.api.Shiruka;

/**
 * an interface to determine events.
 */
public interface Event {

  /**
   * calls the event itself.
   *
   * @return {@code true}.
   */
  default boolean callEvent() {
    Shiruka.getEventManager().call(this);
    return true;
  }

  /**
   * checks if the event is async.
   *
   * @return {@code false} by default, {@code true} if the event fires asynchronously.
   */
  default boolean isAsync() {
    return false;
  }
}
