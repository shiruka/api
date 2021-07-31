package net.shiruka.api.old.metadata;

import java.util.Optional;
import java.util.concurrent.Callable;
import net.shiruka.api.old.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine metadata values.
 */
public interface MetadataValue {

  /**
   * creates a new {@link FixedMetadataValue} instance.
   *
   * @param plugin the plugin to create.
   * @param object the object to create.
   *
   * @return a new fixed metadata value instance.
   */
  @NotNull
  static FixedMetadataValue fixed(@NotNull final Plugin plugin, @NotNull final Object object) {
    return new FixedMetadataValue(plugin, object);
  }

  /**
   * creates a new {@link LazyMetadataValue} instance.
   *
   * @param plugin the plugin to create.
   * @param value the value to create.
   *
   * @return a new lazy metadata value instance.
   */
  @NotNull
  static LazyMetadataValue lazy(@NotNull final Plugin plugin, @NotNull final Callable<Object> value) {
    return new LazyMetadataValue(plugin, value);
  }

  /**
   * creates a new {@link LazyMetadataValue} instance.
   *
   * @param plugin the plugin to create.
   * @param strategy the strategy to create.
   * @param value the value to create.
   *
   * @return a new lazy metadata value instance.
   */
  @NotNull
  static LazyMetadataValue lazy(@NotNull final Plugin plugin, @NotNull final CacheStrategy strategy,
                                @NotNull final Callable<Object> value) {
    return new LazyMetadataValue(plugin, strategy, value);
  }

  /**
   * attempts to convert the value of this metadata item into a boolean.
   *
   * @return the value as a boolean.
   */
  default boolean asBoolean() {
    final var value = this.value();
    if (value instanceof Boolean) {
      return (Boolean) value;
    }
    if (value instanceof Number) {
      return ((Number) value).byteValue() != 0;
    }
    if (value instanceof String) {
      return Boolean.parseBoolean((String) value);
    }
    return true;
  }

  /**
   * attempts to convert the value of this metadata item into a Number.
   *
   * @return the value as a Number.
   */
  @NotNull
  default Optional<Number> asNumber() {
    final var value = this.value();
    if (value instanceof Number) {
      return Optional.of((Number) value);
    }
    return Optional.empty();
  }

  /**
   * attempts to convert the value of this metadata item into a Number.
   *
   * @return the value as a Number.
   *
   * @throws UnsupportedOperationException if the value is not a {@link Number}.
   */
  @NotNull
  default Number asNumberOrThrow() {
    return this.asNumber().orElseThrow(UnsupportedOperationException::new);
  }

  /**
   * attempts to convert the value of this metadata item into a string.
   *
   * @return the value as a string.
   */
  @NotNull
  default String asString() {
    final var value = this.value();
    if (value == null) {
      return "";
    }
    return value.toString();
  }

  /**
   * invalidates this metadata item, forcing it to recompute when next accessed.
   */
  void invalidate();

  /**
   * returns the {@link Plugin} that created this metadata item.
   *
   * @return the plugin that owns this metadata value. Could be null if the plugin was already unloaded.
   */
  @Nullable
  Plugin plugin();

  /**
   * fetches the value of this metadata item.
   *
   * @return the metadata value.
   */
  @Nullable
  Object value();
}
