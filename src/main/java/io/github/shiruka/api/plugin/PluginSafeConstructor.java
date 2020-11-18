package io.github.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

/**
 * an implementation for {@link SafeConstructor}.
 */
final class PluginSafeConstructor extends SafeConstructor {

  /**
   * ctor.
   */
  private PluginSafeConstructor() {
  }

  /**
   * initiates the plugin's safe constructor.
   *
   * @return {@code this}.
   */
  @NotNull
  static PluginSafeConstructor init() {
    final PluginSafeConstructor constructor = new PluginSafeConstructor();
    constructor.yamlConstructors.put(null, new AbstractConstruct() {
      @NotNull
      @Override
      public Object construct(@NotNull final Node node) {
        if (!node.getTag().startsWith("!@")) {
          return SafeConstructor.undefinedConstructor.construct(node);
        }
        return new PluginAwareness() {
          @Override
          public String toString() {
            return node.toString();
          }
        };
      }
    });
    constructor.yamlConstructors.put(new Tag("!@UTF8"), new AbstractConstruct() {
      @NotNull
      @Override
      public PluginAwareness.UTF8 construct(@NotNull final Node node) {
        return PluginAwareness.UTF8.UTF8;
      }
    });
    return constructor;
  }

  /**
   *
   */
  private static final class PluginAwareness {

    /**
     * the flag enum class.
     */
    private enum UTF8 {
      UTF8
    }
  }
}
