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

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
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

  /**
   * creates a new instance of {@code this}.
   *
   * @param capeData the cape data.
   * @param geometryData the geometry data.
   * @param geometryName the geometry name.
   * @param premium the premium.
   * @param skinData the skin data.
   * @param skinId the skin id.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  public static Skin from(@NotNull final ImageData capeData, @NotNull final String geometryData,
                          @NotNull final String geometryName, final boolean premium, @NotNull final ImageData skinData,
                          @NotNull final String skinId) {
    skinData.checkLegacySkinSize();
    capeData.checkLegacyCapeSize();
    final var skinResourcePatch = Skin.convertLegacyGeometryName(geometryName);
    return new Skin("", Collections.emptyList(), "wide", capeData, "", false,
      "", geometryData, geometryName, false, Collections.emptyList(), premium, "#0",
      skinData, skinId, skinResourcePatch, Collections.emptyList());
  }

  /**
   * creates a new instance of {@code this}.
   *
   * @param animationData the animation data.
   * @param animations the animations.
   * @param capeData the cape data.
   * @param capeId the cape id.
   * @param capeOnClassic the cape on classic.
   * @param fullSkinId the full skin id.
   * @param geometryData the geometry data.
   * @param persona the persona.
   * @param premium the premium.
   * @param skinData the skin data.
   * @param skinId the skin id.
   * @param skinResourcePatch the skin resource patch.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  public static Skin from(@NotNull final String animationData, @NotNull final List<AnimationData> animations,
                          @NotNull final ImageData capeData, @NotNull final String capeId, final boolean capeOnClassic,
                          @NotNull final String fullSkinId, @NotNull final String geometryData, final boolean persona,
                          final boolean premium, @NotNull final ImageData skinData, @NotNull final String skinId,
                          @NotNull final String skinResourcePatch) {
    return Skin.from(animationData, Collections.unmodifiableList(new ObjectArrayList<>(animations)), "wide",
      capeData, capeId, capeOnClassic, fullSkinId, geometryData, persona, Collections.emptyList(),
      premium, "#0", skinData, skinId, skinResourcePatch, Collections.emptyList());
  }

  /**
   * creates a new instance of {@code this}.
   *
   * @param animationData the animation data.
   * @param animations the animations.
   * @param armSize the arm size.
   * @param capeData the cape data.
   * @param capeId the cape id.
   * @param capeOnClassic the cape on classic.
   * @param fullSkinId the full skin id.
   * @param geometryData the geometry data.
   * @param persona the persona.
   * @param personaPieces the persona pieces.
   * @param premium the premium.
   * @param skinColor the skin color.
   * @param skinData the skin data.
   * @param skinId the skin id.
   * @param skinResourcePatch the skin resource patch.
   * @param tintColors the tint colors.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  public static Skin from(@NotNull final String animationData, @NotNull final List<AnimationData> animations,
                          @NotNull final String armSize, @NotNull final ImageData capeData,
                          @NotNull final String capeId, final boolean capeOnClassic, @NotNull final String fullSkinId,
                          @NotNull final String geometryData, final boolean persona,
                          @NotNull final List<PersonaPieceData> personaPieces, final boolean premium,
                          @NotNull final String skinColor, @NotNull final ImageData skinData,
                          @NotNull final String skinId, @NotNull final String skinResourcePatch,
                          @NotNull final List<PersonaPieceTintData> tintColors) {
    final var geometryName = Skin.convertSkinPatchToLegacy(skinResourcePatch);
    return new Skin(animationData, Collections.unmodifiableList(new ObjectArrayList<>(animations)), armSize, capeData,
      capeId, capeOnClassic, fullSkinId, geometryData, geometryName, persona, personaPieces, premium, skinColor,
      skinData, skinId, skinResourcePatch, tintColors);
  }

  /**
   * converts the given geometry name to the legacy one.
   *
   * @param geometryName the geometry name to convert.
   *
   * @return a legacy geometry name.
   */
  @NotNull
  private static String convertLegacyGeometryName(@NotNull final String geometryName) {
    return "{\"geometry\" : {\"default\" : \"" + JSONValue.escape(geometryName) + "\"}}";
  }

  /**
   * converts he given skin resource patch to the legacy one.
   *
   * @param skinResourcePatch the skin resource patch to convert.
   *
   * @return a legacy skin resource patch.
   */
  @NotNull
  private static String convertSkinPatchToLegacy(@NotNull final String skinResourcePatch) {
    Preconditions.checkArgument(Skin.validateSkinResourcePatch(skinResourcePatch), "Invalid skin resource patch");
    final var object = (JSONObject) JSONValue.parse(skinResourcePatch);
    final var geometry = (JSONObject) object.get("geometry");
    return (String) geometry.get("default");
  }

  /**
   * checks if the given skin resource patch is valid.
   *
   * @param skinResourcePatch the skin resource patch to test.
   *
   * @return {@code true} if the given skin resource patch is valid.
   */
  private static boolean validateSkinResourcePatch(@NotNull final String skinResourcePatch) {
    try {
      final var object = (JSONObject) JSONValue.parse(skinResourcePatch);
      final var geometry = (JSONObject) object.get("geometry");
      return geometry.containsKey("default") && geometry.get("default") instanceof String;
    } catch (final ClassCastException | NullPointerException e) {
      return false;
    }
  }

  /**
   * checks if the skin is valid.
   *
   * @return {@code true} if the skin is valid.
   */
  public boolean isValid() {
    final var isSkinValid = !this.skinId.trim().isEmpty() &&
      this.skinData.getWidth() >= 64 &&
      this.skinData.getHeight() >= 32 &&
      this.skinData.getImage().length >= Skin.SINGLE_SKIN_SIZE;
    final var isSkinResourceValid = Skin.validateSkinResourcePatch(this.skinResourcePatch);
    return isSkinValid && isSkinResourceValid;
  }
}
