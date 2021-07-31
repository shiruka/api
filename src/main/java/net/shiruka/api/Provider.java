package net.shiruka.api;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine interface providers.
 */
@SuppressWarnings("unchecked")
public interface Provider {

  /**
   * creates a simple provider.
   *
   * @return a newly created simple provider.
   */
  @NotNull
  static Provider create() {
    return new Impl();
  }

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
   * @param <T> type of the provided object.
   */
  <T> void register(@NotNull T t);

  /**
   * a simple implementation for {@link Provider}.
   */
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements Provider {

    /**
     * the implementations.
     */
    @NotNull
    private final Map<Class<?>, Object> implementations = new ConcurrentHashMap<>();

    @NotNull
    @Override
    public <T> Optional<T> provide(@NotNull final Class<? extends T> cls) {
      return Optional.ofNullable(this.implementations.get(cls))
        .map(o -> (T) o);
    }

    @Override
    public <T> void register(@NotNull final T t) {
      this.implementations.put(t.getClass(), t);
    }
  }
}
