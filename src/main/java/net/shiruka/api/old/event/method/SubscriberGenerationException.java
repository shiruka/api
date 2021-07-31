package net.shiruka.api.old.event.method;

import java.lang.reflect.Method;
import net.shiruka.api.old.event.EventSubscriber;
import org.jetbrains.annotations.NotNull;

/**
 * exception thrown when a {@link EventSubscriber} cannot be generated for a {@link Method} at runtime.
 */
final class SubscriberGenerationException extends RuntimeException {

  /**
   * ctor.
   *
   * @param message the message.
   */
  SubscriberGenerationException(@NotNull final String message) {
    super(message);
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   */
  SubscriberGenerationException(@NotNull final String message, @NotNull final Throwable cause) {
    super(message, cause);
  }
}
