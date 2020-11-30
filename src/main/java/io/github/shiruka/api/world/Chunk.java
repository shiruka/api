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

package io.github.shiruka.api.world;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents a Minecraft chunk, a 16x16 section of the world which is individually loaded as needed.
 */
public interface Chunk {

  /**
   * checks to see whether this chunk is usable.
   *
   * @return {@code true} to indicate that this chunk may be used, {@code false} if this chunk is being saved
   *   and cannot be used.
   */
  boolean canUse();

  /**
   * writes the chunk data to the given buffer for sending to players via the protocol.
   *
   * @param buf the buffer to write the chunk data.
   * @param continuous {@code true} if the entire chunk is sent bottom to top.
   */
  void write(@NotNull final ByteBuf buf, final boolean continuous);

  /**
   * generates the chunk.
   */
  void generate();

  /**
   * obtains the highest Y value at the given chunk relative X/Z coordinates.
   *
   * @param x the relative X.
   * @param z the relative Z.
   *
   * @return the highest Y value.
   */
  int getHighestY(int x, int z);

  /**
   * obtains the world of the chunk.
   *
   * @return the world of the chunk.
   */
  @NotNull
  World getWorld();

  /**
   * obtains the x coordinate at which this chunk is located.
   *
   * @return the x coordinate.
   */
  int getX();

  /**
   * obtains the z coordinate at which this chunk is located.
   *
   * @return the z coordinate.
   */
  int getZ();

  /**
   * awaits for the chunk ready getState to finish, indicating that the chunk has finished generation.
   *
   * @return the chunk, when ready.
   */
  Chunk waitReady();
}
