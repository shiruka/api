package io.github.shiruka.api.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine event factory that helps to create and call events.
 */
public interface EventManager {
  /**
   * creates a simple event manager.
   *
   * @return event manager.
   */
  @NotNull
  static EventManager simple() {
    return new Impl();
  }

  /**
   * posts the given event.
   *
   * @param event the event to post.
   */
  void post(@NotNull Event event);

  /**
   * registers the given listener.
   *
   * @param listener the listener to register.
   */
  void register(@NotNull Object listener);

  /**
   * unregisters the given listener.
   *
   * @param listener the listener to unregister.
   */
  void unregister(@NotNull Object listener);

  /**
   * a simple implementation of {@link EventManager}.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements EventManager {

    /**
     * the event bus.
     */
    private final EventBus eventBus = EventBus.builder().build();

    @Override
    public void post(@NotNull final Event event) {
      this.eventBus.post(event);
    }

    @Override
    public void register(@NotNull final Object listener) {
      this.eventBus.register(listener);
    }

    @Override
    public void unregister(@NotNull final Object listener) {
      this.eventBus.unregister(listener);
    }
  }
}
