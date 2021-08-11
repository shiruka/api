package io.github.shiruka.api.event;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.reflect.TypeToken;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import lombok.Synchronized;
import org.jetbrains.annotations.NotNull;

/**
 * a simple implementation for {@link EventController}.
 */
public final class SimpleEventController implements EventController {

  /**
   * the registry.
   */
  private final Registry registry = new Registry();

  /**
   * tests if the {@code event} should be posted to the {@code subscriber}.
   *
   * @param event the event.
   * @param subscriber the subscriber.
   *
   * @return true if the event should be posted.
   */
  private static boolean shouldPost(@NotNull final Event event, @NotNull final EventSubscriber subscriber) {
    if (!subscriber.isIgnoreCancelled() &&
      event instanceof Cancellable cancellable &&
      cancellable.cancelled()) {
      return false;
    }
    final var type = subscriber.getType();
    return type != null &&
      type.isAssignableFrom(event.getClass());
  }

  @NotNull
  @Override
  public CompletableFuture<PostResult> call(@NotNull final Event event) {
    Map<EventSubscriber, Throwable> exceptions = null;
    for (final var subscriber : this.registry.subscribers(event.getClass())) {
      if (!SimpleEventController.shouldPost(event, subscriber)) {
        continue;
      }
      try {
        subscriber.invoke(event);
      } catch (final Throwable e) {
        if (exceptions == null) {
          exceptions = new Object2ObjectOpenHashMap<>();
        }
        exceptions.put(subscriber, e);
      }
    }
    if (exceptions == null) {
      return CompletableFuture.completedFuture(PostResult.success());
    }
    return CompletableFuture.completedFuture(PostResult.failure(exceptions));
  }

  @Override
  public void register(@NotNull final Class<? extends Event> eventClass, @NotNull final EventSubscriber subscriber) {
    this.registry.register(eventClass, subscriber);
  }

  @Override
  public void unregister(@NotNull final EventSubscriber subscriber) {
    this.registry.unregister(subscriber);
  }

  @Override
  public void unregister(@NotNull final Predicate<EventSubscriber> predicate) {
    this.registry.unregisterMatching(predicate);
  }

  /**
   * a subscriber registry.
   */
  private static final class Registry {

    /**
     * a cache of class --> a set of interfaces and classes that the class is or is a subtype of.
     */
    @SuppressWarnings({"unchecked", "UnstableApiUsage"})
    private static final LoadingCache<Class<?>, Set<Class<?>>> CLASS_HIERARCHY = CacheBuilder.newBuilder()
      .weakKeys()
      .build(CacheLoader.from(key -> (Set<Class<?>>) TypeToken.of(key).getTypes().rawTypes()));

    /**
     * the lock.
     */
    private final Object lock = new Object();

    /**
     * a raw, unresolved multimap of class --> event subscriber.
     */
    private final SetMultimap<Class<?>, EventSubscriber> subscribers = LinkedHashMultimap.create();

    /**
     * a cache containing a link between an event class, and the event subscribers which should be passed the
     * given type of event.
     */
    private final LoadingCache<Class<? extends Event>, List<EventSubscriber>> cache = CacheBuilder.newBuilder()
      .initialCapacity(85)
      .build(CacheLoader.from(eventClass -> {
        final var list = new ObjectArrayList<EventSubscriber>();
        final var types = Objects.requireNonNull(Registry.CLASS_HIERARCHY.getUnchecked(eventClass));
        synchronized (this.lock) {
          types.stream()
            .map(this.subscribers::get)
            .forEach(list::addAll);
        }
        list.sort(Comparator.comparingInt(value -> value.getDispatchOrder().getOrder().intValue()));
        return list;
      }));

    /**
     * registers the given event with the given subscriber.
     *
     * @param eventClass the event class to register.
     * @param subscriber the subscriber to register.
     */
    @Synchronized("lock")
    private void register(@NotNull final Class<? extends Event> eventClass, @NotNull final EventSubscriber subscriber) {
      this.subscribers.put(eventClass, subscriber);
      this.cache.invalidateAll();
    }

    /**
     * obtains subscriber from the given class.
     *
     * @param eventClass the event class to obtain.
     *
     * @return a list of subscribers.
     */
    @NotNull
    private List<EventSubscriber> subscribers(@NotNull final Class<? extends Event> eventClass) {
      return this.cache.getUnchecked(eventClass);
    }

    /**
     * unregisters the given subscriber.
     *
     * @param subscriber the subscriber to unregister.
     */
    private void unregister(@NotNull final EventSubscriber subscriber) {
      this.unregisterMatching(h -> h.equals(subscriber));
    }

    /**
     * unregisters all subscribers matching the {@code predicate}.
     *
     * @param predicate the predicate to test subscribers for removal.
     */
    @Synchronized("lock")
    private void unregisterMatching(@NotNull final Predicate<EventSubscriber> predicate) {
      if (this.subscribers.values().removeIf(predicate)) {
        this.cache.invalidateAll();
      }
    }
  }
}
