package io.github.shiruka.api.event.events;

/**
 * represents an event that can be cancelled and thus cause the dispatcher to take a different course of action than
 * was initially planned.
 */
public interface Cancellable extends Event {
  /**
   * calls the event itself.
   *
   * @return {@code true} if the event is cancelled.
   */
  @Override
  default boolean callEvent() {
    return Event.super.callEvent() && this.cancelled();
  }

  /**
   * obtains the cancel state of the event.
   *
   * @return {@code true} if the event has been cancelled.
   */
  boolean cancelled();

  /**
   * cancels state of the event.
   *
   * @param cancelled the cancelled to set.
   */
  void cancelled(boolean cancelled);
}
