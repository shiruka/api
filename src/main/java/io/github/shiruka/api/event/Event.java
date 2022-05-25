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
    Shiruka.eventManager().post(this);
    return true;
  }
}
