package net.shiruka.api.old.event.events;

import net.shiruka.api.old.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine kick message events.
 */
public interface KickMessageEvent extends Event {

  /**
   * obtains the kick message.
   *
   * @return kick message.
   */
  @NotNull
  Text getKickMessage();

  /**
   * sets the kick message.
   *
   * @param message the message to set.
   */
  void setKickMessage(@NotNull Text message);

  /**
   * sets the kick message.
   *
   * @param message the message to set.
   */
  default void setKickMessage(@NotNull final String message) {
    this.setKickMessage(() -> message);
  }
}
