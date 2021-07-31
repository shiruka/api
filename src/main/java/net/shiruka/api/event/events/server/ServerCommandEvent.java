package net.shiruka.api.event.events.server;

import net.shiruka.api.event.events.Cancellable;
import net.shiruka.api.event.events.CommandSenderEvent;
import org.jetbrains.annotations.NotNull;

/**
 * called when a command is run by a non-player.
 */
public interface ServerCommandEvent extends Cancellable, CommandSenderEvent {

  /**
   * obtains the command.
   *
   * @return command.
   */
  @NotNull
  String getCommand();

  /**
   * sets the command.
   *
   * @param command the command to set.
   */
  void setCommand(@NotNull String command);
}
