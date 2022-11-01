package io.github.shiruka.api.event;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.github.shiruka.api.Shiruka;
import net.kyori.event.Cancellable;

/**
 * an interface to determine events.
 *
 * @see Cancellable
 */
public interface Event {
  /**
   * posts the event.
   *
   * @return {@code true} if the event IS NOT cancelled.
   */
  @CanIgnoreReturnValue
  default boolean postEvent() {
    return Shiruka.eventManager().post(this);
  }
}
