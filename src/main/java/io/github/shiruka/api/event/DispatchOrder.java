package io.github.shiruka.api.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * the order in which events that are dispatched by the server's {@link EventController} are transmitted to their
 * respective listeners.
 */
@Getter
@RequiredArgsConstructor
public enum DispatchOrder {
  /**
   * a listener marked with this order is attempted to be invoked as soon as possible.
   */
  FIRST(-2),
  /**
   * a listener marked with this order will be invoked sometime after the first listeners and the middle listeners.
   */
  EARLY(-1),
  /**
   * the default order.
   */
  MIDDLE(0),
  /**
   * a listener marked with this order will be invoked sometime between the middle and the last listener.
   */
  LATE(1),
  /**
   * listeners marked with this method attempt to be invoked after all listeners.
   */
  LAST(2);

  /**
   * the order.
   */
  @NotNull
  private final Number order;
}
