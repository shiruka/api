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

import net.shiruka.api.base.Namespaced;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents registries.
 */
public final class Registry {

  /**
   * the root namespaced.
   */
  public static final Namespaced ROOT_NAMESPACE = Namespaced.minecraft("root");

  /**
   * the root.
   */
  public static final Resourced ROOT = Registry.create(Registry.ROOT_NAMESPACE);

  /**
   * the world.
   */
  public static final Resourced WORLD = Registry.minecraft("world");

  /**
   * the resourced.
   */
  @NotNull
  private final Resourced resourced;

  /**
   * ctor.
   *
   * @param resourced the resourced.
   */
  private Registry(@NotNull final Resourced resourced) {
    this.resourced = resourced;
  }

  /**
   * creates a registry instance from the given {@code namespaced}.
   *
   * @param namespaced the namespaced to create.
   *
   * @return a newly created registry instance.
   */
  @NotNull
  public static Resourced create(@NotNull final Namespaced namespaced) {
    return Resourced.root(namespaced);
  }

  /**
   * creates a registry instance from the given {@code key}.
   *
   * @param key the key to create.
   *
   * @return a newly created registry instance.
   */
  @NotNull
  public static Resourced minecraft(@NotNull final String key) {
    return Registry.create(Namespaced.minecraft(key));
  }
}
