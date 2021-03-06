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

package net.shiruka.api.geometry;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents animation data.
 */
@Value(staticConstructor = "of")
public class AnimationData {

  /**
   * the expression type.
   */
  @NotNull
  AnimationExpressionType expressionType;

  /**
   * the frames.
   */
  float frames;

  /**
   * the image.
   */
  @NotNull
  ImageData image;

  /**
   * the texture type.
   */
  @NotNull
  AnimatedTextureType textureType;

  /**
   * ctor.
   *
   * @param frames the frames.
   * @param image the image.
   * @param textureType the texture type.
   *
   * @return an animation data instance.
   */
  @NotNull
  public static AnimationData of(final float frames, @NotNull final ImageData image,
                                 @NotNull final AnimatedTextureType textureType) {
    return AnimationData.of(AnimationExpressionType.LINEAR, frames, image, textureType);
  }
}
