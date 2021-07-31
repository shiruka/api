package net.shiruka.api.old.command.sender;

import java.text.MessageFormat;
import net.shiruka.api.old.base.Named;
import net.shiruka.api.old.permission.Permissible;
import net.shiruka.api.old.text.Text;
import net.shiruka.api.old.text.TranslatedText;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command senders.
 */
public interface CommandSender extends Named, Permissible {

  /**
   * sends the given message to the command sender.
   *
   * @param message the message to send.
   */
  default void sendMessage(@NotNull final TranslatedText message) {
    this.sendMessage(message.asString());
  }

  /**
   * sends the given message to the command sender.
   *
   * @param message the message to send.
   * @param params the params to send.
   */
  default void sendMessage(@NotNull final Text message, @NotNull final Object... params) {
    if (message instanceof TranslatedText) {
      this.sendMessage((TranslatedText) message);
    } else {
      this.sendMessage(MessageFormat.format(message.asString(), params));
    }
  }

  /**
   * sends the given message to the command sender.
   *
   * @param message the message to send.
   */
  void sendMessage(@NotNull String message);
}
