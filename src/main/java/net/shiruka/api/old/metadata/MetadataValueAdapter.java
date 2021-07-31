package net.shiruka.api.old.metadata;

import java.lang.ref.WeakReference;
import net.shiruka.api.old.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an abstract implementation for {@link MetadataValue}.
 */
public abstract class MetadataValueAdapter implements MetadataValue {

  /**
   * the plugin.
   */
  @NotNull
  protected final WeakReference<Plugin> plugin;

  /**
   * ctor.
   *
   * @param plugin the plugin.
   */
  protected MetadataValueAdapter(@NotNull final Plugin plugin) {
    this.plugin = new WeakReference<>(plugin);
  }

  @Nullable
  @Override
  public final Plugin plugin() {
    return this.plugin.get();
  }
}
