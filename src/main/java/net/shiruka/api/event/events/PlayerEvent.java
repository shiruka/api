package net.shiruka.api.event.events;

import net.shiruka.api.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine player events.
 */
public interface PlayerEvent extends EntityEvent {

  /**
   * obtains the player.
   *
   * @return the player.
   */
  @NotNull
  @Override
  Player getEntity();

  /**
   * obtains the player.
   *
   * @return the player.
   */
  @NotNull
  default Player getPlayer() {
    return this.getEntity();
  }
}
