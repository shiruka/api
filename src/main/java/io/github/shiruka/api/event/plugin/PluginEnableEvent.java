package io.github.shiruka.api.event.plugin;

import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents plugin enable events.
 */
public final class PluginEnableEvent extends PluginEvent {

  /**
   * ctor.
   *
   * @param plugin the plugin.
   */
  public PluginEnableEvent(@NotNull final Plugin.Container plugin) {
    super(plugin);
  }
}
