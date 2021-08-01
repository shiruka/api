package net.shiruka.api.plugin;

import java.util.Collection;
import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine plugins.
 */
public interface Plugin {

  /**
   * obtains the description.
   *
   * @return description.
   */
  @NotNull
  Description getDescription();

  /**
   * runs after the plugin disable.
   */
  void onDisable();

  /**
   * runs after the plugin enable.
   */
  void onEnable();

  /**
   * runs after the plugin load.
   */
  void onLoad();

  /**
   * an interface to determine load order for plugins.
   */
  @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
  enum LoadOrder {
    /**
     * loads the plugin before all world loaded.
     */
    STARTUP("startup", "start", "before", "pre"),
    /**
     * loads the plugin after all worlds loaded.
     */
    POST_WORLD("post_world", "post-world", "post world", "after", "post");

    /**
     * the types.
     */
    @NotNull
    private final Collection<String> types;

    /**
     * ctor.
     *
     * @param types the types.
     */
    LoadOrder(@NotNull final String... types) {
      this(Set.of(types));
    }

    /**
     * checks if {@link #types} contains the given type.
     *
     * @param type the type to check.
     *
     * @return {@code true} if {@link #types} contains the given type.
     */
    public boolean is(@NotNull final String type) {
      return this.types.contains(type);
    }
  }

  /**
   * a simple record class that implements {@link Description}.
   */
  @NotNull
  record Description(
    @NotNull String id,
    @NotNull String name,
    @NotNull Plugin.LoadOrder loadOrder
  ) {

  }
}
