package net.shiruka.api.old.event.events.server;

import net.shiruka.api.old.event.events.TickEvent;

/**
 * called when server ticks.
 */
public interface ServerTickEndEvent extends TickEvent {

  /**
   * obtains the tick duration.
   *
   * @return tick duration.
   */
  double getDuration();

  /**
   * obtains the remaining time.
   *
   * @return remaining time.
   */
  long getRemaining();
}
