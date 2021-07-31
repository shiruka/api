package net.shiruka.api.old.registry;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import net.shiruka.api.old.base.Namespaced;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resources.
 */
public final class Resourced {

  /**
   * the cache.
   */
  private static final Map<String, Resourced> CACHE = Collections.synchronizedMap(Maps.newIdentityHashMap());

  /**
   * the key.
   */
  @NotNull
  private final Namespaced key;

  /**
   * the value.
   */
  @NotNull
  private final Namespaced value;

  /**
   * ctor.
   *
   * @param key the key.
   * @param value the value.
   */
  private Resourced(@NotNull final Namespaced key, @NotNull final Namespaced value) {
    this.key = key;
    this.value = value;
  }

  /**
   * creates a resource.
   *
   * @param key the key to create.
   * @param value the value to create.
   *
   * @return a newly created resource.
   */
  @NotNull
  public static Resourced create(@NotNull final Namespaced key, @NotNull final Namespaced value) {
    return Resourced.CACHE.computeIfAbsent((key + ":" + value).intern(), cache -> new Resourced(key, value));
  }

  /**
   * creates a resource.
   *
   * @param key the key to create.
   * @param value the value to create.
   *
   * @return a newly created resource.
   */
  @NotNull
  public static Resourced create(@NotNull final Resourced key, @NotNull final Namespaced value) {
    return Resourced.create(key.getValue(), value);
  }

  /**
   * creates a root resource.
   *
   * @param value the value to create.
   *
   * @return a newly created root resource.
   */
  @NotNull
  public static Resourced root(@NotNull final Namespaced value) {
    return Resourced.create(Registry.ROOT_NAMESPACE, value);
  }

  /**
   * obtains the key.
   *
   * @return key.
   */
  @NotNull
  public Namespaced getKey() {
    return this.key;
  }

  /**
   * obtains the value.
   *
   * @return value.
   */
  @NotNull
  public Namespaced getValue() {
    return this.value;
  }
}
