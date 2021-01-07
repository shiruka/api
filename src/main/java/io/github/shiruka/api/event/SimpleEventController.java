/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.event;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.reflect.TypeToken;
import io.github.shiruka.api.events.Event;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
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
    if (!subscriber.consumeCancelledEvents() &&
      event instanceof Cancellable && ((Cancellable) event).cancelled()) {
      return false;
    }
    final var type = subscriber.type();
    if (type == null) {
      return false;
    }
    return type.isAssignableFrom(event.getClass());
  }

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
          exceptions = new HashMap<>();
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
        final var list = new ArrayList<EventSubscriber>();
        final var types = Objects.requireNonNull(Registry.CLASS_HIERARCHY.getUnchecked(eventClass));
        synchronized (this.lock) {
          types.stream()
            .map(this.subscribers::get)
            .forEach(list::addAll);
        }
        list.sort(Comparator.comparingInt(value -> value.dispatchOrder().getOrder().intValue()));
        return list;
      }));

    /**
     * registers the given event with the given subscriber.
     *
     * @param eventClass the event class to register.
     * @param subscriber the subscriber to register.
     */
    private void register(@NotNull final Class<? extends Event> eventClass, @NotNull final EventSubscriber subscriber) {
      synchronized (this.lock) {
        this.subscribers.put(eventClass, subscriber);
        this.cache.invalidateAll();
      }
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
    private void unregisterMatching(@NotNull final Predicate<EventSubscriber> predicate) {
      synchronized (this.lock) {
        if (this.subscribers.values().removeIf(predicate)) {
          this.cache.invalidateAll();
        }
      }
    }
  }
}
