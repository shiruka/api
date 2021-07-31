package net.shiruka.api.metadata;

import java.util.List;
import java.util.Optional;
import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine things that able to store metadata.
 */
public interface Metadatable {

  /**
   * finds first the metadata values from the implementing object's metadata store.
   *
   * @param key the key to get.
   *
   * @return the first requested metadata value for the key.
   *
   * @see #getMetadata(String)
   */
  @NotNull
  default Optional<MetadataValue> getFirstMetadata(@NotNull final String key) {
    return this.getMetadata(key).stream().findFirst();
  }

  /**
   * returns a list of previously set metadata values from the implementing object's metadata store.
   *
   * @param key the key to get.
   *
   * @return a list of values, one for each plugin that has set the requested value.
   */
  @NotNull
  List<MetadataValue> getMetadata(@NotNull String key);

  /**
   * tests to see whether the implementing object contains the given metadata value in its metadata store.
   *
   * @param key the key to check.
   *
   * @return the existence of the metadataKey within subject.
   */
  boolean hasMetadata(@NotNull String key);

  /**
   * removes all the given metadata value from the implementing object's metadata store.
   *
   * @param key the key to remove.
   */
  void removeAllMetadata(@NotNull String key);

  /**
   * removes the given metadata value from the implementing object's metadata store.
   *
   * @param key the key to remove.
   * @param plugin the value to remove.
   */
  void removeMetadata(@NotNull String key, @NotNull Plugin plugin);

  /**
   * sets a fixed metadata value in the implementing object's metadata store.
   *
   * @param key the key to set.
   * @param plugin the plugin to set.
   * @param value the value to set.
   */
  default void setFixedMetadata(@NotNull final String key, @NotNull final Plugin plugin, @NotNull final Object value) {
    this.setMetadata(key, new FixedMetadataValue(plugin, value));
  }

  /**
   * sets a metadata value in the implementing object's metadata store.
   *
   * @param key the key to set.
   * @param value the value to set.
   */
  void setMetadata(@NotNull String key, @NotNull MetadataValue value);
}
