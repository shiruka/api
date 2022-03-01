package io.github.shiruka.api.common;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.util.Objects;
import java.util.function.ObjIntConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents int to object bi map.
 *
 * @param <T> type of the object.
 */
public final class Int2ObjectBiMap<T> {

  /**
   * the backwards.
   */
  @NotNull
  private final Object2IntMap<T> backwards;

  /**
   * the forwards.
   */
  @NotNull
  private final Int2ObjectMap<T> forwards;

  /**
   * ctor.
   *
   * @param initialCapacity the initial capacity.
   * @param loadFactor the load factor.
   * @param defaultKey the default key.
   * @param defaultValue the default value.
   */
  public Int2ObjectBiMap(final int initialCapacity, final float loadFactor, final int defaultKey,
                         @Nullable final T defaultValue) {
    this.forwards = new Int2ObjectOpenHashMap<>(initialCapacity, loadFactor);
    this.backwards = new Object2IntOpenHashMap<>(initialCapacity, loadFactor);
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
  public Int2ObjectBiMap(final int initialCapacity, final float loadFactor, final int defaultKey) {
    this(initialCapacity, loadFactor, defaultKey, null);
  }

  /**
   * ctor.
   *
   * @param initialCapacity the initial capacity.
   * @param loadFactor the load factor.
   */
  public Int2ObjectBiMap(final int initialCapacity, final float loadFactor) {
    this(initialCapacity, loadFactor, -1);
  }

  /**
   * ctor.
   *
   * @param defaultValue the default value.
   */
  public Int2ObjectBiMap(final T defaultValue) {
    this(2, 0.5F, -1, defaultValue);
  }

  /**
   * ctor.
   *
   * @param initialCapacity the initial capacity.
   */
  public Int2ObjectBiMap(final int initialCapacity) {
    this(initialCapacity, 0.5F);
  }

  /**
   * ctor.
   */
  public Int2ObjectBiMap() {
    this(2);
  }

  /**
   * checks if the key contains.
   *
   * @param key the key to check.
   *
   * @return {@code true} if the key contains.
   */
  public boolean containsKey(final int key) {
    return this.forwards.containsKey(key);
  }

  /**
   * checks if the value contains.
   *
   * @param value the value to check.
   *
   * @return {@code true} if the value contains.
   */
  public boolean containsValue(@NotNull final T value) {
    return this.forwards.containsValue(value);
  }

  /**
   * runs the consumer for each value.
   *
   * @param consumer the consumer to run.
   */
  public void forEach(@NotNull final ObjIntConsumer<T> consumer) {
    for (final var entry : Int2ObjectMaps.fastIterable(this.forwards)) {
      consumer.accept(entry.getValue(), entry.getIntKey());
    }
  }

  /**
   * gets the key at the value.
   *
   * @param value the value to get.
   *
   * @return key at the value.
   */
  public int get(@NotNull final T value) {
    return this.backwards.getInt(value);
  }

  /**
   * gets the value at the key.
   *
   * @param key the key to get.
   *
   * @return value at the key.
   */
  public T get(final int key) {
    T value = this.forwards.get(key);
    if (value == null) {
      value = this.forwards.defaultReturnValue();
    }
    return value;
  }

  @Override
  public int hashCode() {
    return this.forwards.hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final var that = (Int2ObjectBiMap<?>) obj;
    return Objects.equals(this.forwards, that.forwards) &&
      Objects.equals(this.backwards, that.backwards);
  }

  @Override
  public String toString() {
    return this.forwards.toString();
  }

  /**
   * obtains the keys.
   *
   * @return keys
   */
  @NotNull
  public IntCollection keys() {
    return this.backwards.values();
  }

  /**
   * puts the value.
   *
   * @param key the ket to put.
   * @param value the value to put.
   */
  public void put(final int key, @NotNull final T value) {
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
  public boolean remove(final int key) {
    if (!this.forwards.containsKey(key)) {
      return false;
    }
    final var value = this.forwards.get(key);
    if (!this.backwards.containsKey(value)) {
      return false;
    }
    this.forwards.remove(key);
    this.backwards.removeInt(value);
    return true;
  }

  /**
   * removes the value.
   *
   * @param value the value to remove.
   *
   * @return {@code true} if the value removed successfully.
   */
  public boolean remove(@NotNull final T value) {
    if (!this.backwards.containsKey(value)) {
      return false;
    }
    final var oldValue = this.backwards.getInt(value);
    if (!this.forwards.containsKey(oldValue)) {
      return false;
    }
    this.backwards.removeInt(oldValue);
    this.forwards.remove(oldValue);
    return true;
  }

  /**
   * obtains the values.
   *
   * @return values.
   */
  @NotNull
  public ObjectCollection<T> values() {
    return this.forwards.values();
  }
}
