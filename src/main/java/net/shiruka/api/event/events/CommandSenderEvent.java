package net.shiruka.api.event.events;

import net.shiruka.api.command.sender.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command sender events.
 */
public interface CommandSenderEvent extends Event {

  /**
   * obtains the command sender.
   *
   * @return sender.
   */
  @NotNull
  CommandSender getSender();
}
