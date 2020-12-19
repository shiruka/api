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

package io.github.shiruka.api.world.generators;

import io.github.shiruka.api.world.World;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * this class is the instance provider for all 3 generators required to generate world chunks and terrain.
 */
public interface GeneratorProvider {

  /**
   * obtains the set of feature generators for the world possessing this generator provider, given the options for
   * generation.
   *
   * @param world the world which to provide generators, used for gathering options for generation.
   *
   * @return the feature generators for the world.
   */
  @NotNull
  Set<FeatureGenerator> getFeatureGenerators(World world);

  /**
   * obtains the container that is used for running the generator classes.
   *
   * @return the container that runs teh generator.
   */
  @NotNull
  default GeneratorContainer getGenerationContainer() {
    return GeneratorContainer.DEFAULT;
  }

  /**
   * obtains the set of prop generators for the world possessing this generator provider, given the options for
   * generation.
   *
   * @param world the world which to provide generators, used for gathering options for generation.
   *
   * @return the prop generators for the world.
   */
  @NotNull
  Set<PropGenerator> getPropGenerators(World world);

  /**
   * obtains the terrain generator for the world possessing this generator provider, given the options for generation.
   *
   * @param world the world which to provide generators, used for gathering options for generation.
   *
   * @return the terrain generator for the world.
   */
  @NotNull
  TerrainGenerator getTerrainGenerator(World world);
}
