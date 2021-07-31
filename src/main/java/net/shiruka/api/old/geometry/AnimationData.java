package net.shiruka.api.old.geometry;

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
