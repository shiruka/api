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

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents animation data.
 */
public final class AnimationData {

  /**
   * the expression type.
   */
  @NotNull
  private final AnimationExpressionType expressionType;

  /**
   * the frames.
   */
  private final float frames;

  /**
   * the image.
   */
  @NotNull
  private final ImageData image;

  /**
   * the texture type.
   */
  @NotNull
  private final AnimatedTextureType textureType;

  /**
   * ctor.
   *
   * @param expressionType the expression type.
   * @param frames the frames.
   * @param image the image.
   * @param textureType the texture type.
   */
  public AnimationData(@NotNull final AnimationExpressionType expressionType, final float frames,
                       @NotNull final ImageData image, @NotNull final AnimatedTextureType textureType) {
    this.expressionType = expressionType;
    this.frames = frames;
    this.image = image;
    this.textureType = textureType;
  }

  /**
   * ctor.
   *
   * @param frames the frames.
   * @param image the image.
   * @param textureType the texture type.
   */
  public AnimationData(final float frames, @NotNull final ImageData image,
                       @NotNull final AnimatedTextureType textureType) {
    this(AnimationExpressionType.LINEAR, frames, image, textureType);
  }

  /**
   * obtains the expression type.
   *
   * @return expression type.
   */
  @NotNull
  public AnimationExpressionType getExpressionType() {
    return this.expressionType;
  }

  /**
   * obtains the frames.
   *
   * @return frames.
   */
  public float getFrames() {
    return this.frames;
  }

  /**
   * obtains the image.
   *
   * @return image.
   */
  @NotNull
  public ImageData getImage() {
    return this.image;
  }

  /**
   * obtains the texture type.
   *
   * @return texture type.
   */
  @NotNull
  public AnimatedTextureType getTextureType() {
    return this.textureType;
  }
}
