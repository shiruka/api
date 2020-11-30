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

import io.github.shiruka.api.world.generators.GeneratorProvider;
import org.jetbrains.annotations.NotNull;

/**
 * the options pertaining to how the chunks within the world
 * that uses these options will generate.
 */
public interface GeneratorOptions {

  /**
   * obtains the level type of the world, which also defines what generator the world uses.
   *
   * @return the world's level type.
   */
  @NotNull
  LevelType getLevelType();

  /**
   * obtains the option string used to generate super-flat and customized worlds.
   *
   * @return the layer options string.
   */
  @NotNull
  String getOptionString();

  /**
   * obtains the generator factory which provided the world with the facilities necessary for world generation.
   *
   * @return the factory responsible for providing generation facilitated.
   */
  @NotNull
  GeneratorProvider getProvider();

  /**
   * obtains the seed used to generate the terrain and features on the world.
   *
   * @return the world seed.
   */
  long getSeed();

  /**
   * determine whether world features such as villages, strongholds, and mine-shafts should be generated.
   *
   * @return {@code true} to indicate that features should be generated, {@code false} if not.
   */
  boolean isAllowFeatures();
}
