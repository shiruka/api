package io.github.shiruka.api.common;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.util.function.BiConsumer;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents object to object bi map.
 *
 * @param <K> type of the key.
 * @param <V> type of the value.
 */
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
public final class Object2ObjectBiMap<K, V> {

  /**
   * the backwards.
   */
  @NotNull
  private final Object2ObjectMap<V, K> backwards;

  /**
   * the forwards.
   */
  @NotNull
  private final Object2ObjectMap<K, V> forwards;

  /**
   * ctor.
   *
   * @param initialCapacity the initial capacity.
   * @param loadFactor the load factor.
   * @param defaultKey the default key.
   * @param defaultValue the default value.
   */
  public Object2ObjectBiMap(
    final int initialCapacity,
    final float loadFactor,
    @Nullable final K defaultKey,
    @Nullable final V defaultValue
  ) {
    this.forwards = new Object2ObjectOpenHashMap<>(initialCapacity, loadFactor);
    this.backwards =
      new Object2ObjectOpenHashMap<>(initialCapacity, loadFactor);
    this.forwards.defaultReturnValue(defaultValue);
    this.backwards.defaultReturnValue(defaultKey);
  }

  /**
   * ctor.
   *
   * @param initialCapacity the initial capacity.
   * @param loadFactor the load factor.
   * @param defaultKey the default key.
   */
  public Object2ObjectBiMap(
    final int initialCapacity,
    final float loadFactor,
    @Nullable final K defaultKey
  ) {
    this(initialCapacity, loadFactor, defaultKey, null);
  }

  /**
   * ctor.
   *
   * @param initialCapacity the initial capacity.
   * @param loadFactor the load factor.
   */
  public Object2ObjectBiMap(final int initialCapacity, final float loadFactor) {
    this(initialCapacity, loadFactor, null);
  }

  /**
   * ctor.
   *
   * @param defaultValue the default value.
   */
  public Object2ObjectBiMap(@Nullable final V defaultValue) {
    this(2, 0.5F, null, defaultValue);
  }

  /**
   * ctor.
   *
   * @param initialCapacity the initial capacity.
   */
  public Object2ObjectBiMap(final int initialCapacity) {
    this(initialCapacity, 0.5F);
  }

  /**
   * ctor.
   */
  public Object2ObjectBiMap() {
    this(2);
  }

  /**
   * checks if the key contains.
   *
   * @param key the key to check.
   *
   * @return {@code true} if the key contains.
   */
  public boolean containsKey(@NotNull final K key) {
    return this.forwards.containsKey(key);
  }

  /**
   * checks if the value contains.
   *
   * @param value the value to check.
   *
   * @return {@code true} if the value contains.
   */
  public boolean containsValue(@NotNull final V value) {
    return this.forwards.containsValue(value);
  }

  /**
   * runs the consumer for each value.
   *
   * @param consumer the consumer to run.
   */
  public void forEach(@NotNull final BiConsumer<K, V> consumer) {
    for (final var entry : Object2ObjectMaps.fastIterable(this.forwards)) {
      consumer.accept(entry.getKey(), entry.getValue());
    }
  }

  /**
   * gets the key at the value.
   *
   * @param value the value to get.
   *
   * @return key at the value.
   */
  @Nullable
  public K getKey(@NotNull final V value) {
    return this.backwards.get(value);
  }

  /**
   * gets the value at the key.
   *
   * @param key the key to get.
   *
   * @return value at the key.
   */
  @Nullable
  public V getValue(@NotNull final K key) {
    var value = this.forwards.get(key);
    if (value == null) {
      value = this.forwards.defaultReturnValue();
    }
    return value;
  }

  /**
   * obtains the keys.
   *
   * @return keys
   */
  @NotNull
  public ObjectCollection<K> keys() {
    return this.backwards.values();
  }

  /**
   * puts the value.
   *
   * @param key the ket to put.
   * @param value the value to put.
   */
  public void put(@NotNull final K key, @NotNull final V value) {
    this.forwards.put(key, value);
    this.backwards.put(value, key);
  }

  /**
   * removes the value.
   *
   * @param key the key to remove.
   *
   * @return {@code true} if the value removed successfully.
   */
  public boolean removeKey(@NotNull final K key) {
    if (!this.forwards.containsKey(key)) {
      return false;
    }
    final var value = this.forwards.get(key);
    if (!this.backwards.containsKey(value)) {
      return false;
    }
    this.forwards.remove(key);
    this.backwards.remove(value);
    return true;
  }

  /**
   * removes the value.
   *
   * @param value the value to remove.
   *
   * @return {@code true} if the value removed successfully.
   */
  public boolean removeValue(@NotNull final V value) {
    if (!this.backwards.containsKey(value)) {
      return false;
    }
    final var oldValue = this.backwards.get(value);
    if (!this.forwards.containsKey(oldValue)) {
      return false;
    }
    this.backwards.remove(value);
    this.forwards.remove(oldValue);
    return true;
  }

  /**
   * obtains the values.
   *
   * @return values.
   */
  @NotNull
  public ObjectCollection<V> values() {
    return this.forwards.values();
  }
}
