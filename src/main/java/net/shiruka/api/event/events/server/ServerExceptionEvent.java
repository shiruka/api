package net.shiruka.api.event.events.server;

import net.shiruka.api.event.events.Event;
import net.shiruka.api.event.events.server.exception.ServerException;
import org.jetbrains.annotations.NotNull;

/**
 * called whenever an exception is thrown in a recoverable section of the server.
 */
public interface ServerExceptionEvent extends Event {

  /**
   * obtains the server exception.
   *
   * @return server exception.
   */
  @NotNull
  ServerException getServerException();
}
