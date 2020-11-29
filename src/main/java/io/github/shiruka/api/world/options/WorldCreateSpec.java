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

package io.github.shiruka.api.world.options;

import org.jetbrains.annotations.NotNull;

/**
 * the options for creating a new world.
 */
public final class WorldCreateSpec {

  /**
   * the default instance of the world creator specification.
   */
  private static final WorldCreateSpec DEFAULT = new WorldCreateSpec(true);

  /**
   * Whether this spec uses the default world options
   */
  private final boolean def;

  /**
   * the dimension.
   */
  private Dimension dimension = Dimension.OVER_WORLD;

  /**
   * ctor.
   *
   * @param def the default spec.
   */
  private WorldCreateSpec(final boolean def) {
    this.def = def;
  }

  /**
   * create a new custom world options specification that can be passed to the server world loader to
   * create a custom world.
   *
   * @return a new custom world specification.
   */
  @NotNull
  public static WorldCreateSpec custom() {
    return new WorldCreateSpec(false);
  }

  /**
   * uses the default world options to build the world.
   *
   * @return the default world specification.
   */
  @NotNull
  public static WorldCreateSpec getDefaultOptions() {
    return WorldCreateSpec.DEFAULT;
  }

  /**
   * obtains dimension of the world.
   *
   * @return dimension of the world.
   */
  @NotNull
  public Dimension getDimension() {
    return this.dimension;
  }

  /**
   * sets the world's dimension.
   *
   * @param dimension the dimension to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setDimension(@NotNull final Dimension dimension) {
    this.dimension = dimension;
    return this;
  }

  /**
   * determines whether this option specification is uses all default values or not.
   *
   * @return {@code true} for default values.
   */
  public boolean isDefault() {
    return this.def;
  }
}
