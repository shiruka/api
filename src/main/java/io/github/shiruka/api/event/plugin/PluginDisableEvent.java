package io.github.shiruka.api.event.plugin;

import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents plugin disable events.
 */
public final class PluginDisableEvent extends PluginEvent {

  /**
   * ctor.
   *
   * @param plugin the plugin.
   */
  public PluginDisableEvent(@NotNull final Plugin.Container plugin) {
    super(plugin);
  }
}
