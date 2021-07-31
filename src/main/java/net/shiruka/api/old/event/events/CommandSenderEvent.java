package net.shiruka.api.old.event.events;

import net.shiruka.api.old.command.sender.CommandSender;
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
