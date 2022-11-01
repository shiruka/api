package io.github.shiruka.api.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
   * registers the given subscriber.
   *
   * @param subscriber the subscriber to register.
   */
  void register(@NotNull Object subscriber);

  /**
   * unregisters the given subscriber.
   *
   * @param subscriber the subscriber to unregister.
   */
  void unregister(@NotNull Object subscriber);

  /**
   * a simple implementation of {@link EventManager}.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements EventManager {

    @Override
    public void post(@NotNull final Event event) {}

    @Override
    public void register(@NotNull final Object subscriber) {}

    @Override
    public void unregister(@NotNull final Object subscriber) {}
  }
}
