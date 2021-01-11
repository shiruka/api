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

package io.github.shiruka.api.world;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine world managers.
 */
public interface WorldManager {

  /**
   * creates a chunk data for use in a generator.
   *
   * @param world the world to create.
   *
   * @return a new chunk data for the world.
   */
  @NotNull
  ChunkData createChunkData(@NotNull World world);

  /**
   * creates a chunk data for use in a generator, that is populated by the vanilla generator for that world.
   *
   * @param world the world to create.
   * @param x the x to create.
   * @param z the z to create.
   *
   * @return a new chunk data for the world.
   */
  @NotNull
  ChunkData createNativeChunkData(@NotNull World world, int x, int z);

  /**
   * creates a new world from the given {@code worldCreator}.
   *
   * @param worldCreator the world creator to create.
   *
   * @return a newly created world.
   */
  @NotNull
  Optional<World> createWorld(@NotNull WorldCreator worldCreator);
}
