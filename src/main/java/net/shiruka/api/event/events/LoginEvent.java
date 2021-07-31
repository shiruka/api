package net.shiruka.api.event.events;

import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine login events.
 */
public interface LoginEvent extends Event {

  /**
   * allows the player to join.
   */
  default void allow() {
    if (this instanceof LoginResultEvent) {
      ((LoginResultEvent) this).setLoginResult(LoginResultEvent.LoginResult.ALLOWED);
    }
  }

  /**
   * kicks the player with the given kick message.
   *
   * @param text the text to disallow.
   * @param result the result to disallow.
   */
  default void disallow(@NotNull final LoginResultEvent.LoginResult result, @NotNull final Text text) {
    if (this instanceof LoginResultEvent) {
      ((LoginResultEvent) this).setLoginResult(result);
    }
    if (this instanceof KickMessageEvent) {
      ((KickMessageEvent) this).setKickMessage(text);
    }
  }
}
