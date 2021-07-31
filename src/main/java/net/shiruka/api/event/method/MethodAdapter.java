package net.shiruka.api.event.method;

import java.lang.reflect.Method;
import net.shiruka.api.event.EventController;
import net.shiruka.api.event.Listener;
import net.shiruka.api.event.events.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a subscription adapter for {@link EventController} which supports defining event subscribers as methods in a class.
 */
public interface MethodAdapter {

  /**
   * calls the event to the event listener/handlers that are registered under the event controller.
   *
   * @param event the event to dispatch.
   */
  void call(@NotNull Event event);

  /**
   * registers all methods determined to be {@link MethodScanner#shouldRegister(Listener, Method)} on the
   * {@code listener} to receive events.
   *
   * @param listener the listener.
   */
  void register(@NotNull Listener listener);

  /**
   * unregisters all methods on a registered {@code listener}.
   *
   * @param listener the listener.
   */
  void unregister(@NotNull Listener listener);
}
