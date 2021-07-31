package net.shiruka.api.old.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.shiruka.api.old.event.events.Cancellable;
import org.jetbrains.annotations.NotNull;

/**
 * this annotation should be marked on methods that calls when an event comes in.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

  /**
   * should not receive events even if they have been {@link Cancellable#isCancelled()}.
   *
   * @return {@code true} if the event ignores being cancelled.
   */
  boolean ignoreCancelled() default false;

  /**
   * the position of the listener in the dispatch sequence once the event has been fired.
   *
   * @return the event's {@link DispatchOrder}.
   */
  @NotNull
  DispatchOrder priority() default DispatchOrder.MIDDLE;
}
