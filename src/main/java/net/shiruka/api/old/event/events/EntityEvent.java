package net.shiruka.api.old.event.events;

import net.shiruka.api.old.entity.Entity;
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
