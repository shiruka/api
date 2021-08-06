package io.github.shiruka.api.event.events.server;

import io.github.shiruka.api.event.Event;
import io.github.shiruka.api.exception.ServerException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents server exception events.
 */
@Getter
@RequiredArgsConstructor
public final class ServerExceptionEvent implements Event {

  /**
   * the exception.
   */
  @NotNull
  private final ServerException exception;
}
