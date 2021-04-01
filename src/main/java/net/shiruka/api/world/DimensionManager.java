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

package net.shiruka.api.world;

import lombok.RequiredArgsConstructor;
import net.shiruka.api.base.Namespaced;
import net.shiruka.api.registry.Registry;
import net.shiruka.api.registry.Resourced;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents dimension managers.
 */
@RequiredArgsConstructor
public final class DimensionManager {

  /**
   * the over world.
   */
  private static final Resourced OVER_WORLD =
    Resourced.create(Registry.DIMENSION_TYPE, Namespaced.minecraft("over_world"));

  /**
   * the over world implementation.
   */
  @NotNull
  private static final DimensionManager OVER_WORLD_IMPL = new DimensionManager(-1, true);

  /**
   * the end.
   */
  private static final Resourced THE_END =
    Resourced.create(Registry.DIMENSION_TYPE, Namespaced.minecraft("the_end"));

  /**
   * the end implementation.
   */
  @NotNull
  private static final DimensionManager THE_END_IMPL = new DimensionManager(6000L, false);

  /**
   * the nether.
   */
  private static final Resourced THE_NETHER =
    Resourced.create(Registry.DIMENSION_TYPE, Namespaced.minecraft("the_nether"));

  /**
   * the nether implementation.
   */
  @NotNull
  private static final DimensionManager THE_NETHER_IMPL = new DimensionManager(18000L, false);

  /**
   * the fixed time.
   */
  private final long fixedTime;

  /**
   * the has skylight.
   */
  private final boolean hasSkylight;

  /**
   * checks if the dimension has skylight.
   *
   * @return {@code true} if this dimension has skylight.
   */
  public boolean hasSkylight() {
    return this.hasSkylight;
  }
}
