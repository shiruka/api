package net.shiruka.api;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine interface providers.
 */
public interface Provider {

  /**
   * provides the given class's implementation.
   *
   * @param cls the cls to provide.
   * @param <T> type of the provided class.
   *
   * @return provided implementation.
   */
  @NotNull <T> Optional<T> provide(@NotNull Class<? extends T> cls);

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
  default <T> T provideOrThrow(@NotNull final Class<? extends T> cls) {
    return this.provide(cls).orElseThrow();
  }

  /**
   * registers the object.
   *
   * @param t the object to register.
   */
  <T> void register(@NotNull T t);
}
