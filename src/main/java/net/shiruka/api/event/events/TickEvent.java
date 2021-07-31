package net.shiruka.api.event.events;

/**
 * an interface to determine tick events.
 */
public interface TickEvent extends Event {

  /**
   * obtains the tick.
   *
   * @return the tick.
   */
  int getTick();
}
