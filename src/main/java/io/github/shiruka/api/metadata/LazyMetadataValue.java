/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.metadata;

import io.github.shiruka.api.plugin.Plugin;
import java.lang.ref.SoftReference;
import java.util.concurrent.Callable;
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
