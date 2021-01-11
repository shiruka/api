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

package io.github.shiruka.api.util;

import com.eclipsesource.json.JsonArray;
import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents sematic versions.
 */
public final class SemanticVersion {

  /**
   * the major.
   */
  private final int major;

  /**
   * the minor.
   */
  private final int minor;

  /**
   * the patch.
   */
  private final int patch;

  /**
   * ctor.
   *
   * @param patch the patch.
   * @param minor the minor.
   * @param major the major.
   */
  public SemanticVersion(final int patch, final int minor, final int major) {
    this.patch = patch;
    this.minor = minor;
    this.major = major;
  }

  /**
   * creates a new semantic version instance from the json array.
   *
   * @param array the array to create.
   *
   * @return a new instance of {@code this}.
   *
   * @throws IllegalArgumentException if the given array's size less than 3.
   */
  @NotNull
  public static SemanticVersion load(@NotNull final JsonArray array) {
    Preconditions.checkArgument(array.size() >= 3, "The version must have at least 3 version number");
    return new SemanticVersion(array.get(0).asInt(), array.get(1).asInt(), array.get(2).asInt());
  }

  /**
   * obtains the major.
   *
   * @return major.
   */
  public int getMajor() {
    return this.major;
  }

  /**
   * obtains the minor.
   *
   * @return minor.
   */
  public int getMinor() {
    return this.minor;
  }

  /**
   * obtains the patch.
   *
   * @return patch.
   */
  public int getPatch() {
    return this.patch;
  }
}
