package net.shiruka.api.metadata;

import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents fixed metadata values.
 */
public final class FixedMetadataValue extends LazyMetadataValue {

  /**
   * the value.
   */
  @NotNull
  private final Object value;

  /**
   * ctor.
   *
   * @param plugin the plugin.
   * @param value the value.
   */
  public FixedMetadataValue(@NotNull final Plugin plugin, @NotNull final Object value) {
    super(plugin);
    this.value = value;
  }

  @Override
  public void invalidate() {
    // ignored.
  }

  @NotNull
  @Override
  public Object value() {
    return this.value;
  }
}
