package net.shiruka.api.old.registry;

import net.shiruka.api.old.base.Namespaced;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents registries.
 */
public final class Registry {

  /**
   * the root namespaced.
   */
  public static final Namespaced ROOT_NAMESPACE = Namespaced.minecraft("root");

  /**
   * the root.
   */
  public static final Resourced ROOT = Registry.create(Registry.ROOT_NAMESPACE);

  /**
   * the dimension type.
   */
  public static final Resourced DIMENSION_TYPE = Registry.minecraft("dimension_type");

  /**
   * the world.
   */
  public static final Resourced WORLD = Registry.minecraft("world");

  /**
   * the resourced.
   */
  @NotNull
  private final Resourced resourced;

  /**
   * ctor.
   *
   * @param resourced the resourced.
   */
  private Registry(@NotNull final Resourced resourced) {
    this.resourced = resourced;
  }

  /**
   * creates a registry instance from the given {@code namespaced}.
   *
   * @param namespaced the namespaced to create.
   *
   * @return a newly created registry instance.
   */
  @NotNull
  public static Resourced create(@NotNull final Namespaced namespaced) {
    return Resourced.root(namespaced);
  }

  /**
   * creates a registry instance from the given {@code key}.
   *
   * @param key the key to create.
   *
   * @return a newly created registry instance.
   */
  @NotNull
  public static Resourced minecraft(@NotNull final String key) {
    return Registry.create(Namespaced.minecraft(key));
  }
}
