package net.shiruka.api.old.event.events.player;

import net.shiruka.api.old.event.events.PlayerEvent;
import net.shiruka.api.old.event.events.QuitMessageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * called when a player leaves a server.
 */
public interface PlayerQuitEvent extends PlayerEvent, QuitMessageEvent {

  /**
   * obtains the quit reason.
   *
   * @return quit reason.
   */
  @NotNull
  QuitReason getQuitReason();

  /**
   * an enum class that contains quit reasons.
   */
  enum QuitReason {
    /**
     * the player left on their own behalf.
     */
    DISCONNECTED,
    /**
     * the player was kicked from the server.
     */
    KICKED,
    /**
     * the player has timed out.
     */
    TIMED_OUT,
    /**
     * the player's connection has entered an erroneous state.
     */
    ERRONEOUS_STATE
  }
}
