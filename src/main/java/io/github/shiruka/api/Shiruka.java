package io.github.shiruka.api;

import io.github.shiruka.api.event.EventManager;
import io.github.shiruka.api.plugin.Plugin;
import io.github.shiruka.api.scheduler.Scheduler;
import io.github.shiruka.api.scheduler.Task;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * a core interface for Shiru ka.
 */
public interface Shiruka {

  /**
   * gets the implementation of async Scheduler.
   *
   * @return implementation of async Scheduler.
   */
  @NotNull
  static Scheduler.Async asyncScheduler() {
    return Shiruka.provideOrThrow(Scheduler.Async.class);
  }

  /**
   * obtains the event manager.
   *
   * @return event manager.
   *
   * @throws NoSuchElementException if the event manager not found.
   */
  @NotNull
  static EventManager eventManager() {
    return Shiruka.provideOrThrow(EventManager.class);
  }

  /**
   * obtains the logger.
   *
   * @return logger.
   */
  @NotNull
  static Logger logger() {
    return Shiruka.server().logger();
  }

  /**
   * obtains the plugin manager.
   *
   * @return plugin manager.
   *
   * @throws NoSuchElementException if the plugin manager not found.
   */
  @NotNull
  static Plugin.Manager pluginManager() {
    return Shiruka.provideOrThrow(Plugin.Manager.class);
  }

  /**
   * provides the given class's implementation.
   *
   * @param cls the cls to provide.
   * @param <T> type of the provided class.
   *
   * @return provided implementation.
   */
  @NotNull
  static <T> Optional<T> provide(@NotNull final Class<? extends T> cls) {
    return Shiruka.provider().provide(cls);
  }

  /**
   * provides the given class's implementation.
   *
   * @param cls the cls to provide.
   * @param <T> type of the provided class.
   *
   * @return provided implementation.
   *
   * @throws NoSuchElementException if the provider not found.
   */
  @NotNull
  static <T> T provideOrThrow(@NotNull final Class<? extends T> cls) {
    return Shiruka.provider().provideOrThrow(cls);
  }

  /**
   * obtains the provider.
   *
   * @return provider.
   */
  @NotNull
  static Provider provider() {
    return Shiruka.server().provider();
  }

  /**
   * obtains the server.
   *
   * @return server.
   */
  @NotNull
  static Server server() {
    return Implementation.server();
  }

  /**
   * sets the server.
   *
   * @param server the server to set.
   *
   * @throws IllegalStateException if the server is already set.
   */
  static void server(@NotNull final Server server) {
    Implementation.server(server);
  }

  /**
   * gets the implementation of sync Scheduler.
   *
   * @return implementation of sync Scheduler.
   */
  @NotNull
  static Scheduler.Sync syncScheduler() {
    return Shiruka.provideOrThrow(Scheduler.Sync.class);
  }
}
