package net.shiruka.api.event.events;

import net.shiruka.api.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine player events.
 */
public interface EntityEvent extends Event {

  /**
   * obtains the entity.
   *
   * @return the entity.
   */
  @NotNull
  Entity getEntity();
}
