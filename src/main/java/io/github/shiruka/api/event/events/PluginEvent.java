package io.github.shiruka.api.event.events;

import io.github.shiruka.api.plugin.Plugin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * an abstract class that represents plugin events.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PluginEvent implements io.github.shiruka.api.event.PluginEvent {

  /**
   * the plugin.
   */
  @NotNull
  private final Plugin.Container plugin;
}
