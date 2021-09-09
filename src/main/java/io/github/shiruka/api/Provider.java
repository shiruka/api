package io.github.shiruka.api;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine interface providers.
 */
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
    return this.provide(cls).orElseThrow(() ->
      new IllegalStateException("Provider for %s not found!"
        .formatted(cls.toString())));
  }

  /**
   * registers the object.
   *
   * @param object the object to register.
   */
  void register(@NotNull Object object);

  /**
   * a simple implementation for {@link Provider}.
   */
  @SuppressWarnings("unchecked")
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements Provider {

    /**
     * the implementations.
     */
    @NotNull
    private final Map<Class<?>, Object> implementations = new HashMap<>();

    @NotNull
    @Override
    public <T> Optional<T> provide(@NotNull final Class<? extends T> cls) {
      return Optional.ofNullable(this.implementations.get(cls))
        .map(o -> (T) o);
    }

    @Override
    public void register(@NotNull final Object object) {
      this.implementations.put(object.getClass(), object);
    }
  }
}
