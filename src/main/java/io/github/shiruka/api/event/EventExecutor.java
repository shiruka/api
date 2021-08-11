package io.github.shiruka.api.event;

import io.github.shiruka.api.event.events.Event;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import org.jetbrains.annotations.NotNull;

/**
 * an interface that can invoke a defined method on a listener object when an event is posted.
 */
@FunctionalInterface
public interface EventExecutor {

  /**
   * creates an implementation for {@link Factory} using methods.
   *
   * @return a simple instance for {@link Factory}.
   */
  @NotNull
  static Factory createFactory() {
    return (object, method) -> {
      final var handle = MethodHandles.publicLookup()
        .unreflect(method)
        .bindTo(object);
      return (listener, event) -> handle.invoke(event);
    };
  }

  /**
   * invokes the appropriate method on the given listener to handle the event.
   *
   * @param listener the listener.
   * @param event the event.
   *
   * @throws Throwable if an exception occurred.
   */
  void invoke(@NotNull Listener listener, @NotNull Event event) throws Throwable;

  /**
   * factory for {@link EventExecutor}s.
   */
  @FunctionalInterface
  interface Factory {

    /**
     * creates an event executor.
     *
     * @param object the listener object.
     * @param method the method to call on the object.
     *
     * @return an event executor.
     *
     * @throws Exception if an exception occurred while creating an executor.
     */
    @NotNull
    EventExecutor create(@NotNull Object object, @NotNull Method method) throws Exception;
  }
}
