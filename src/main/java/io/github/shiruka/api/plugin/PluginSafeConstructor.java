package io.github.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.Node;

/**
 * an implementation for {@link SafeConstructor}.
 */
final class PluginSafeConstructor extends SafeConstructor {

  /**
   * initiates the plugin's safe constructor.
   */
  void init() {
    this.yamlConstructors.put(null, new AbstractConstruct() {
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
    for (final var flag : PluginAwareness.Flags.values()) {
      this.yamlConstructors.put(new Tag("!@" + flag.name()), new AbstractConstruct() {
        @NotNull
        @Override
        public PluginAwareness.Flags construct(@NotNull final Node node) {
          return flag;
        }
      });
    }
  }
}
