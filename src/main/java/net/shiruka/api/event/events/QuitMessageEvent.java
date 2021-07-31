package net.shiruka.api.event.events;

import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine quit message events.
 */
public interface QuitMessageEvent extends Event {

  /**
   * obtains the quit message.
   *
   * @return quit message.
   */
  @NotNull
  Text getQuitMessage();

  /**
   * sets the quit message.
   *
   * @param message the message to set.
   */
  void setQuitMessage(@NotNull Text message);

  /**
   * sets the quit message.
   *
   * @param message the message to set.
   */
  default void setQuitMessage(@NotNull final String message) {
    this.setQuitMessage(() -> message);
  }
}
