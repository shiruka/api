package io.github.shiruka.api.event.events.server;

import io.github.shiruka.api.event.Event;
import io.github.shiruka.api.exception.ServerException;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents server exception events.
 */
public record ServerExceptionEvent(
  @NotNull ServerException exception
) implements Event {

}
