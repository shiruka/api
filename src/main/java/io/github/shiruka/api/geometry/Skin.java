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

package io.github.shiruka.api.geometry;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents skins.
 */
public final class Skin {

  /**
   * the pixel size.
   */
  private static final int PIXEL_SIZE = 4;

  /**
   * the double skin size.
   */
  static final int DOUBLE_SKIN_SIZE = 64 * 64 * Skin.PIXEL_SIZE;

  /**
   * the single skin size.
   */
  static final int SINGLE_SKIN_SIZE = 64 * 32 * Skin.PIXEL_SIZE;

  /**
   * the skin 128x128 size.
   */
  static final int SKIN_128_128_SIZE = 128 * 128 * Skin.PIXEL_SIZE;

  /**
   * the skin 128x64 size.
   */
  static final int SKIN_128_64_SIZE = 128 * 64 * Skin.PIXEL_SIZE;

  /**
   * the animation data.
   */
  @NotNull
  private final String animationData;

  /**
   * the animations.
   */
  @NotNull
  private final List<AnimationData> animations;

  /**
   * the arm size.
   */
  @NotNull
  private final String armSize;

  /**
   * the cape data.
   */
  @NotNull
  private final ImageData capeData;

  /**
   * the cape id.
   */
  @NotNull
  private final String capeId;

  /**
   * the cape on classic.
   */
  private final boolean capeOnClassic;

  /**
   * the full skin id.
   */
  @NotNull
  private final String fullSkinId;

  /**
   * the geometry data.
   */
  @NotNull
  private final String geometryData;

  /**
   * the geometry name.
   */
  @NotNull
  private final String geometryName;

  /**
   * the persona.
   */
  private final boolean persona;

  /**
   * the persona pieces.
   */
  @NotNull
  private final List<PersonaPieceData> personaPieces;

  /**
   * the premium.
   */
  private final boolean premium;

  /**
   * the skin color.
   */
  @NotNull
  private final String skinColor;

  /**
   * the skin data.
   */
  @NotNull
  private final ImageData skinData;

  /**
   * the skin id.
   */
  @NotNull
  private final String skinId;

  /**
   * the skin resource patch.
   */
  @NotNull
  private final String skinResourcePatch;

  /**
   * the tint colors.
   */
  @NotNull
  private final List<PersonaPieceTintData> tintColors;

  /**
   * ctor.
   *
   * @param animationData the animation data.
   * @param animations the animations.
   * @param armSize the arm size.
   * @param capeData the cape data.
   * @param capeId the cape id.
   * @param capeOnClassic the cape on classic.
   * @param fullSkinId the full skin id.
   * @param geometryData the geometry data.
   * @param geometryName the geometry name.
   * @param persona the persona.
   * @param personaPieces the persona pieces.
   * @param premium the premium.
   * @param skinColor the skin color.
   * @param skinData the skin data.
   * @param skinId the skin id.
   * @param skinResourcePatch the skin resource patch.
   * @param tintColors the tint colors.
   */
  private Skin(@NotNull final String animationData, @NotNull final List<AnimationData> animations,
               @NotNull final String armSize, @NotNull final ImageData capeData, @NotNull final String capeId,
               final boolean capeOnClassic, @NotNull final String fullSkinId, @NotNull final String geometryData,
               @NotNull final String geometryName, final boolean persona,
               @NotNull final List<PersonaPieceData> personaPieces, final boolean premium,
               @NotNull final String skinColor, @NotNull final ImageData skinData, @NotNull final String skinId,
               @NotNull final String skinResourcePatch, @NotNull final List<PersonaPieceTintData> tintColors) {
    this.animationData = animationData;
    this.animations = animations;
    this.armSize = armSize;
    this.capeData = capeData;
    this.capeId = capeId;
    this.capeOnClassic = capeOnClassic;
    this.fullSkinId = fullSkinId;
    this.geometryData = geometryData;
    this.geometryName = geometryName;
    this.persona = persona;
    this.personaPieces = personaPieces;
    this.premium = premium;
    this.skinColor = skinColor;
    this.skinData = skinData;
    this.skinId = skinId;
    this.skinResourcePatch = skinResourcePatch;
    this.tintColors = tintColors;
  }
}
