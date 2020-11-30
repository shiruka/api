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

package io.github.shiruka.api.base;

import io.github.shiruka.api.world.generators.GeneratorContext;
import io.github.shiruka.api.world.options.GeneratorOptions;
import org.jetbrains.annotations.NotNull;

/**
 * this class generates world features that are enabled by {@link GeneratorOptions#isAllowFeatures()}.
 */
public interface FeatureGenerator {

  /**
   * a feature generator is implemented by overriding this method and writing the changes to the context.
   *
   * @param chunkX the x coordinate of the chunk.
   * @param chunkZ the z coordinate of the chunk.
   * @param context the context.
   */
  void generate(int chunkX, int chunkZ, @NotNull GeneratorContext context);
}
