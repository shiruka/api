package io.github.shiruka.api.event.events;

import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine plugin events.
 */
public interface PluginEvent extends Event {
  /**
   * obtains the plugin.
   *
   * @return plugin.
   */
  @NotNull
  Plugin.Container plugin();
}
