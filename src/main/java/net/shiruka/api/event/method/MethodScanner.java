package net.shiruka.api.event.method;

import java.lang.reflect.Method;
import net.shiruka.api.event.DispatchOrder;
import net.shiruka.api.event.EventHandler;
import net.shiruka.api.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine which methods on a listener should be registered as subscribers,
 * and what properties they should have.
 */
public interface MethodScanner {

  /**
   * default method scanner's instance.
   *
   * @return a new default instance of {@code this}.
   */
  static MethodScanner createDefault() {
    return new MethodScanner() {
    };
  }

  /**
   * gets if cancelled events should be posted to the resultant subscriber.
   *
   * @param listener the listener.
   * @param method the method.
   *
   * @return if cancelled events should be posted.
   */
  default boolean consumeCancelledEvents(@NotNull final Listener listener, @NotNull final Method method) {
    return method.isAnnotationPresent(EventHandler.class) &&
      method.getAnnotation(EventHandler.class).ignoreCancelled();
  }

  /**
   * gets the post order the resultant subscriber should be called at.
   *
   * @param listener the listener.
   * @param method the method.
   *
   * @return the post order of this subscriber.
   *
   * @see DispatchOrder
   */
  @NotNull
  default DispatchOrder dispatchOrder(@NotNull final Listener listener, @NotNull final Method method) {
    return method.isAnnotationPresent(EventHandler.class)
      ? method.getAnnotation(EventHandler.class).priority()
      : DispatchOrder.MIDDLE;
  }

  /**
   * gets if the factory should generate a subscriber for this method.
   *
   * @param listener the listener being scanned.
   * @param method the method declaration being considered.
   *
   * @return if a subscriber should be registered.
   */
  default boolean shouldRegister(@NotNull final Listener listener, @NotNull final Method method) {
    return method.getAnnotation(EventHandler.class) != null;
  }
}
