package net.shiruka.api;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * a core interface for Shiru ka.
 */
public interface Shiruka {

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
   * provides the given class's implementation.
   *
   * @param cls the cls to provide.
   * @param <T> type of the provided class.
   *
   * @return provided implementation.
   */
  @NotNull
  static <T> Optional<T> provide(@NotNull final Class<? extends T> cls) {
    return Shiruka.getServer().getProvider().provide(cls);
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
    return Shiruka.getServer().getProvider().provideOrThrow(cls);
  }
}
