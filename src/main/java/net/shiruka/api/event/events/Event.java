package net.shiruka.api.event.events;

import net.shiruka.api.Shiruka;

/**
 * this class represents the superinterface of all classes that are events.
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
