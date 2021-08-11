package io.github.shiruka.api.event;

/**
 * the order in which events that are dispatched by the server's {@link EventController} are transmitted to their
 * respective listeners.
 */
public interface DispatchOrder {

  /**
   * a listener marked with this order will be invoked sometime after the first listeners and the middle listeners.
   */
  int EARLY = -50;

  /**
   * a listener marked with this order is attempted to be invoked as soon as possible.
   */
  int FIRST = -100;

  /**
   * listeners marked with this method attempt to be invoked after all listeners.
   */
  int LAST = 100;

  /**
   * a listener marked with this order will be invoked sometime between the middle and the last listener.
   */
  int LATE = 50;

  /**
   * the default order.
   */
  int MIDDLE = 0;
}
