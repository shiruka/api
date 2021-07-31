package net.shiruka.api.old.event.events.player;

import java.net.InetSocketAddress;
import java.util.Optional;
import java.util.UUID;
import net.shiruka.api.old.event.events.Event;
import net.shiruka.api.old.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * an event that invokes when a player has disconnected.
 */
public interface PlayerConnectionCloseEvent extends Event {

  /**
   * obtains the player address.
   *
   * @return player address.
   */
  @NotNull
  InetSocketAddress getAddress();

  /**
   * obtains the player name.
   *
   * @return player name.
   */
  @NotNull
  Text getName();

  /**
   * obtains the player unique id.
   *
   * @return player unique id.
   */
  @NotNull
  UUID getUniqueId();

  /**
   * obtains the player xbox unique id.
   *
   * @return player xbox unique id.
   */
  @NotNull
  Optional<String> getXboxUniqueId();
}
