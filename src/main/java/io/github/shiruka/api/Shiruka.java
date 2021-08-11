package io.github.shiruka.api;

import io.github.shiruka.api.event.EventManager;
import io.github.shiruka.api.plugin.Plugin;
import io.github.shiruka.api.scheduler.Scheduler;
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
  static Scheduler.Async getAsyncScheduler() {
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
  static EventManager getEventManager() {
    return Shiruka.provideOrThrow(EventManager.class);
  }

  /**
   * obtains the logger.
   *
   * @return logger.
   */
  @NotNull
  static Logger getLogger() {
    return Shiruka.getServer().getLogger();
  }

  /**
   * obtains the plugin manager.
   *
   * @return plugin manager.
   *
   * @throws NoSuchElementException if the plugin manager not found.
   */
  @NotNull
  static Plugin.Manager getPluginManager() {
    return Shiruka.provideOrThrow(Plugin.Manager.class);
  }

  /**
   * obtains the provider.
   *
   * @return provider.
   */
  @NotNull
  static Provider getProvider() {
    return Shiruka.getServer().getProvider();
  }

  /**
   * obtains the server.
   *
   * @return server.
   */
  @NotNull
  static Server getServer() {
    return Implementation.getServer();
  }

  /**
   * sets the server.
   *
   * @param server the server to set.
   *
   * @throws IllegalStateException if the server is already set.
   */
  static void setServer(@NotNull final Server server) {
    Implementation.setServer(server);
  }

  /**
   * gets the implementation of sync Scheduler.
   *
   * @return implementation of sync Scheduler.
   */
  @NotNull
  static Scheduler.Sync getSyncScheduler() {
    return Shiruka.provideOrThrow(Scheduler.Sync.class);
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
    return Shiruka.getProvider().provide(cls);
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
    return Shiruka.getProvider().provideOrThrow(cls);
  }
}
