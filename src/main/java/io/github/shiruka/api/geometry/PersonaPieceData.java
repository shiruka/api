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

package io.github.shiruka.api.geometry;

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents persona piece data.
 */
public final class PersonaPieceData {

  /**
   * the id.
   */
  @NotNull
  private final String id;

  /**
   * the is default.
   */
  private final boolean isDefault;

  /**
   * the pack id.
   */
  @NotNull
  private final String packId;

  /**
   * the product id.
   */
  @NotNull
  private final String productId;

  /**
   * the type.
   */
  @NotNull
  private final String type;

  /**
   * ctor.
   *
   * @param id the id.
   * @param isDefault the id default.
   * @param packId the pack id.
   * @param productId the product id.
   * @param type the type.
   */
  public PersonaPieceData(@NotNull final String id, final boolean isDefault, @NotNull final String packId,
                          @NotNull final String productId, @NotNull final String type) {
    this.id = id;
    this.isDefault = isDefault;
    this.packId = packId;
    this.productId = productId;
    this.type = type;
  }

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  public String getId() {
    return this.id;
  }

  /**
   * obtains the pack id.
   *
   * @return pack id.
   */
  @NotNull
  public String getPackId() {
    return this.packId;
  }

  /**
   * obtains the product id.
   *
   * @return product id.
   */
  @NotNull
  public String getProductId() {
    return this.productId;
  }

  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  public String getType() {
    return this.type;
  }

  /**
   * obtains the is default.
   *
   * @return is default.
   */
  public boolean isDefault() {
    return this.isDefault;
  }
}
