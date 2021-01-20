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

package net.shiruka.api.pack;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that represents resource pack types.
 */
public enum ResourcePackType {
  /**
   * the invalid.
   */
  INVALID(0),
  /**
   * the resource.
   */
  RESOURCE(6),
  /**
   * the behavior.
   */
  BEHAVIOR(4),
  /**
   * the world template.
   */
  WORLD_TEMPLATE(8),
  /**
   * the addon.
   */
  ADDON(1),
  /**
   * the skins.
   */
  SKINS(7),
  /**
   * the cached.
   */
  CACHED(2),
  /**
   * the copy protected.
   */
  COPY_PROTECTED(3),
  /**
   * the persona piece.
   */
  PERSONA_PIECE(5);

  /**
   * the id.
   */
  @NotNull
  private final Number id;

  /**
   * ctor.
   *
   * @param id the id.
   */
  ResourcePackType(@NotNull final Number id) {
    this.id = id;
  }

  /**
   * obtains the id.
   *
   * @return id.
   */
  public byte getId() {
    return this.id.byteValue();
  }
}