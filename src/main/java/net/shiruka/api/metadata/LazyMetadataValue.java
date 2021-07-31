package net.shiruka.api.metadata;

import java.lang.ref.SoftReference;
import java.util.concurrent.Callable;
import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents lazy metadata values.
 */
public class LazyMetadataValue extends MetadataValueAdapter {

  /**
   * the actually null.
   */
  private static final Object ACTUALLY_NULL = new Object();

  /**
   * the cache strategy.
   */
  @Nullable
  private final CacheStrategy strategy;

  /**
   * the lazy value.
   */
  @Nullable
  private final Callable<Object> value;

  /**
   * the internal value.
   */
  @Nullable
  private SoftReference<Object> internalValue;

  /**
   * ctor.
   *
   * @param plugin the plugin.
   * @param strategy the strategy.
   * @param value the value.
   */
  public LazyMetadataValue(@NotNull final Plugin plugin, @NotNull final CacheStrategy strategy,
                           @NotNull final Callable<Object> value) {
    super(plugin);
    this.internalValue = new SoftReference<>(null);
    this.value = value;
    this.strategy = strategy;
  }

  /**
   * ctor.
   *
   * @param plugin the plugin.
   * @param value the value.
   */
  public LazyMetadataValue(@NotNull final Plugin plugin, @NotNull final Callable<Object> value) {
    this(plugin, CacheStrategy.CACHE_AFTER_FIRST_EVAL, value);
  }

  /**
   * ctor.
   *
   * @param plugin the plugin.
   */
  protected LazyMetadataValue(@NotNull final Plugin plugin) {
    super(plugin);
    this.internalValue = null;
    this.strategy = null;
    this.value = null;
  }

  @Override
  public synchronized void invalidate() {
    if (this.internalValue != null &&
      this.strategy != CacheStrategy.CACHE_ETERNALLY) {
      this.internalValue.clear();
    }
  }

  @Nullable
  @Override
  public Object value() {
    if (this.internalValue == null || this.value == null) {
      return null;
    }
    if (this.strategy != CacheStrategy.NEVER_CACHE && this.internalValue.get() != null) {
      return null;
    }
    try {
      var lazyValue = this.value.call();
      if (lazyValue == null) {
        lazyValue = LazyMetadataValue.ACTUALLY_NULL;
      }
      this.internalValue = new SoftReference<>(lazyValue);
    } catch (final Exception e) {
      throw new MetadataEvaluationException(e);
    }
    final var internal = this.internalValue.get();
    if (internal == LazyMetadataValue.ACTUALLY_NULL) {
      return null;
    }
    return internal;
  }
}
