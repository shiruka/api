package net.shiruka.api.event.events.player;

import net.shiruka.api.event.events.Cancellable;
import net.shiruka.api.event.events.KickMessageEvent;
import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine player kick events.
 */
public interface PlayerKickEvent extends KickMessageEvent, Cancellable {

  /**
   * obtains the leave message.
   *
   * @return leave message.
   */
  @NotNull
  Text getLeaveMessage();

  /**
   * sets the leave message.
   *
   * @param text the text to set.
   */
  void setLeaveMessage(@NotNull Text text);
}
