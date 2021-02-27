/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
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

package net.shiruka.api.registry;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import net.shiruka.api.base.Namespaced;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resources.
 */
public final class Resourced {

  /**
   * the cache.
   */
  private static final Map<String, Resourced> CACHE = Collections.synchronizedMap(Maps.newIdentityHashMap());

  /**
   * the key.
   */
  @NotNull
  private final Namespaced key;

  /**
   * the value.
   */
  @NotNull
  private final Namespaced value;

  /**
   * ctor.
   *
   * @param key the key.
   * @param value the value.
   */
  private Resourced(@NotNull final Namespaced key, @NotNull final Namespaced value) {
    this.key = key;
    this.value = value;
  }

  /**
   * creates a root resource.
   *
   * @param value the value to create.
   *
   * @return a newly created root resource.
   */
  @NotNull
  public static Resourced root(@NotNull final Namespaced value) {
    return Resourced.create(Registry.ROOT_NAMESPACE, value);
  }

  /**
   * creates a resource.
   *
   * @param key the key to create.
   * @param value the value to create.
   *
   * @return a newly created resource.
   */
  @NotNull
  private static Resourced create(@NotNull final Namespaced key, @NotNull final Namespaced value) {
    return Resourced.CACHE.computeIfAbsent((key + ":" + value).intern(), cache -> new Resourced(key, value));
  }

  /**
   * obtains the key.
   *
   * @return key.
   */
  @NotNull
  public Namespaced getKey() {
    return this.key;
  }

  /**
   * obtains the value.
   *
   * @return value.
   */
  @NotNull
  public Namespaced getValue() {
    return this.value;
  }
}
