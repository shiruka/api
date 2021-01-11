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

import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents persona piece tint data.
 */
public final class PersonaPieceTintData {

  /**
   * the colors.
   */
  @NotNull
  private final List<String> colors;

  /**
   * the type.
   */
  @NotNull
  private final String type;

  /**
   * ctor.
   *
   * @param colors the colors.
   * @param type the type.
   */
  public PersonaPieceTintData(@NotNull final List<String> colors, @NotNull final String type) {
    this.colors = Collections.unmodifiableList(colors);
    this.type = type;
  }

  /**
   * obtains the colors.
   *
   * @return colors.
   */
  @NotNull
  public List<String> getColors() {
    return this.colors;
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
}
