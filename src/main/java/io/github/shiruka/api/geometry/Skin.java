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
import java.util.Objects;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
  @Nullable
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
  @Nullable
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
  @Nullable
  private final ImageData skinData;

  /**
   * the skin id.
   */
  @Nullable
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
               final boolean capeOnClassic, @NotNull final String fullSkinId, @Nullable final String geometryData,
               @NotNull final String geometryName, final boolean persona,
               @NotNull final List<PersonaPieceData> personaPieces, final boolean premium,
               @NotNull final String skinColor, @Nullable final ImageData skinData, @Nullable final String skinId,
               @NotNull final String skinResourcePatch, @NotNull final List<PersonaPieceTintData> tintColors) {
    this.animationData = animationData;
    this.animations = Collections.unmodifiableList(animations);
    this.armSize = armSize;
    this.capeData = capeData;
    this.capeId = capeId;
    this.capeOnClassic = capeOnClassic;
    this.fullSkinId = fullSkinId;
    this.geometryData = geometryData;
    this.geometryName = geometryName;
    this.persona = persona;
    this.personaPieces = Collections.unmodifiableList(personaPieces);
    this.premium = premium;
    this.skinColor = skinColor;
    this.skinData = skinData;
    this.skinId = skinId;
    this.skinResourcePatch = skinResourcePatch;
    this.tintColors = Collections.unmodifiableList(tintColors);
  }

  /**
   * creates a new {@link Builder} instance.
   *
   * @return a new builder instance.
   */
  @NotNull
  public static Builder builder() {
    return new Builder();
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
                          @NotNull final String fullSkinId, @Nullable final String geometryData, final boolean persona,
                          final boolean premium, @Nullable final ImageData skinData, @Nullable final String skinId,
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
                          @Nullable final String geometryData, final boolean persona,
                          @NotNull final List<PersonaPieceData> personaPieces, final boolean premium,
                          @NotNull final String skinColor, @Nullable final ImageData skinData,
                          @Nullable final String skinId, @NotNull final String skinResourcePatch,
                          @NotNull final List<PersonaPieceTintData> tintColors) {
    final var geometryName = Skin.convertSkinPatchToLegacy(skinResourcePatch);
    return new Skin(animationData, Collections.unmodifiableList(new ObjectArrayList<>(animations)), armSize, capeData,
      capeId, capeOnClassic, fullSkinId, geometryData, geometryName, persona, personaPieces, premium, skinColor,
      skinData, skinId, skinResourcePatch, tintColors);
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
    Preconditions.checkArgument(Skin.validateSkinResourcePatch(skinResourcePatch),
      "Invalid skin resource patch");
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
   * obtains the animation data.
   *
   * @return animation data.
   */
  @NotNull
  public String getAnimationData() {
    return this.animationData;
  }

  /**
   * obtains the animations.
   *
   * @return animations.
   */
  @Nullable
  public List<AnimationData> getAnimations() {
    return this.animations;
  }

  /**
   * obtains the arm size.
   *
   * @return arm size.
   */
  @NotNull
  public String getArmSize() {
    return this.armSize;
  }

  /**
   * obtains the cape data.
   *
   * @return cape data.
   */
  @NotNull
  public ImageData getCapeData() {
    return this.capeData;
  }

  /**
   * obtains the cape id.
   *
   * @return cape id.
   */
  @NotNull
  public String getCapeId() {
    return this.capeId;
  }

  /**
   * obtains the full skin id.
   *
   * @return full skin id.
   */
  @NotNull
  public String getFullSkinId() {
    return this.fullSkinId;
  }

  /**
   * obtains the geometry data.
   *
   * @return geometry data.
   */
  @Nullable
  public String getGeometryData() {
    return this.geometryData;
  }

  /**
   * obtains the geometry name.
   *
   * @return geometry name.
   */
  @NotNull
  public String getGeometryName() {
    return this.geometryName;
  }

  /**
   * obtains the persona pieces.
   *
   * @return persona pieces.
   */
  @NotNull
  public List<PersonaPieceData> getPersonaPieces() {
    return this.personaPieces;
  }

  /**
   * obtains the skin color.
   *
   * @return skin color.
   */
  @NotNull
  public String getSkinColor() {
    return this.skinColor;
  }

  /**
   * obtains the skin data.
   *
   * @return skin data.
   */
  @Nullable
  public ImageData getSkinData() {
    return this.skinData;
  }

  /**
   * obtains the skin id.
   *
   * @return skin id.
   */
  @Nullable
  public String getSkinId() {
    return this.skinId;
  }

  /**
   * obtains the skin resource patch.
   *
   * @return skin resource patch.
   */
  @NotNull
  public String getSkinResourcePatch() {
    return this.skinResourcePatch;
  }

  /**
   * obtains the tint colors.
   *
   * @return tint colors.
   */
  @NotNull
  public List<PersonaPieceTintData> getTintColors() {
    return this.tintColors;
  }

  /**
   * obtains the cape on classic.
   *
   * @return cape on classic.
   */
  public boolean isCapeOnClassic() {
    return this.capeOnClassic;
  }

  /**
   * obtains the the persona.
   *
   * @return persona.
   */
  public boolean isPersona() {
    return this.persona;
  }

  /**
   * obtains the premium.
   *
   * @return premium.
   */
  public boolean isPremium() {
    return this.premium;
  }

  /**
   * checks if the skin is valid.
   *
   * @return {@code true} if the skin is valid.
   */
  public boolean isValid() {
    final var isSkinValid = this.skinId != null &&
      !this.skinId.trim().isEmpty() &&
      this.skinData != null &&
      this.skinData.getWidth() >= 64 &&
      this.skinData.getHeight() >= 32 &&
      this.skinData.getImage().length >= Skin.SINGLE_SKIN_SIZE;
    final var isSkinResourceValid = Skin.validateSkinResourcePatch(this.skinResourcePatch);
    return isSkinValid && isSkinResourceValid;
  }

  /**
   * a builder class that helps to create a new {@link Skin} instance.
   */
  private static final class Builder {

    /**
     * the animation data.
     */
    @NotNull
    private String animationData = "";

    /**
     * the animations.
     */
    @NotNull
    private List<AnimationData> animations = Collections.emptyList();

    /**
     * the arm size.
     */
    @NotNull
    private String armSize = "wide";

    /**
     * the cape data.
     */
    @NotNull
    private ImageData capeData = ImageData.empty();

    /**
     * the cape id.
     */
    @NotNull
    private String capeId = "";

    /**
     * the cape on classic.
     */
    private boolean capeOnClassic;

    /**
     * the full skin id.
     */
    @Nullable
    private String fullSkinId;

    /**
     * the geometry data.
     */
    @Nullable
    private String geometryData;

    /**
     * the geometry names..
     */
    @Nullable
    private String geometryName;

    /**
     * the persona.
     */
    private boolean persona;

    /**
     * the persona pieces.
     */
    @Nullable
    private List<PersonaPieceData> personaPieces;

    /**
     * the premium.
     */
    private boolean premium;

    /**
     * the skin color.
     */
    @NotNull
    private String skinColor = "#0";

    /**
     * the skin data.
     */
    @Nullable
    private ImageData skinData;

    /**
     * the skin id.
     */
    @Nullable
    private String skinId;

    /**
     * the skin resource patch.
     */
    @Nullable
    private String skinResourcePatch;

    /**
     * the tint colors.
     */
    @NotNull
    private List<PersonaPieceTintData> tintColors = Collections.emptyList();

    /**
     * sets the given animationData.
     *
     * @param animationData the animationData to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder animationData(@NotNull final String animationData) {
      this.animationData = animationData;
      return this;
    }

    /**
     * sets the given animations.
     *
     * @param animations the animations to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder animations(@NotNull final List<AnimationData> animations) {
      this.animations = Collections.unmodifiableList(animations);
      return this;
    }

    /**
     * sets the given armSize.
     *
     * @param armSize the armSize to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder armSize(@NotNull final String armSize) {
      this.armSize = armSize;
      return this;
    }

    /**
     * builds and return a new instance of {@link Skin}.
     *
     * @return a new instance of skin.
     */
    @NotNull
    public Skin build() {
      if (this.fullSkinId == null) {
        this.fullSkinId = this.skinId + this.capeId;
      }
      final var skinOrGeometry = Objects.requireNonNullElseGet(this.skinResourcePatch, () -> this.geometryName);
      return Skin.from(this.animationData, this.animations, this.capeData, this.capeId, this.capeOnClassic,
        this.fullSkinId, this.geometryData, this.persona, this.premium, this.skinData, this.skinId, skinOrGeometry);
    }

    /**
     * sets the given capeData.
     *
     * @param capeData the capeData to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder capeData(@NotNull final ImageData capeData) {
      this.capeData = capeData;
      return this;
    }

    /**
     * sets the given capeId.
     *
     * @param capeId the capeId to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder capeId(@NotNull final String capeId) {
      this.capeId = capeId;
      return this;
    }

    /**
     * sets the given capeOnClassic.
     *
     * @param capeOnClassic the capeOnClassic to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder capeOnClassic(final boolean capeOnClassic) {
      this.capeOnClassic = capeOnClassic;
      return this;
    }

    /**
     * sets the given fullSkinId.
     *
     * @param fullSkinId the fullSkinId to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder fullSkinId(@NotNull final String fullSkinId) {
      this.fullSkinId = fullSkinId;
      return this;
    }

    /**
     * sets the given geometryData.
     *
     * @param geometryData the geometryData to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder geometryData(@NotNull final String geometryData) {
      this.geometryData = geometryData;
      return this;
    }

    /**
     * sets the given geometryName.
     *
     * @param geometryName the geometryName to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder geometryName(@NotNull final String geometryName) {
      this.geometryName = geometryName;
      return this;
    }

    /**
     * sets the given persona.
     *
     * @param persona the persona to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder persona(final boolean persona) {
      this.persona = persona;
      return this;
    }

    /**
     * sets the given personaPieces.
     *
     * @param personaPieces the personaPieces to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder personaPieces(@NotNull final List<PersonaPieceData> personaPieces) {
      this.personaPieces = Collections.unmodifiableList(personaPieces);
      return this;
    }

    /**
     * sets the given premium.
     *
     * @param premium the premium to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder premium(final boolean premium) {
      this.premium = premium;
      return this;
    }

    /**
     * sets the given skinColor.
     *
     * @param skinColor the skinColor to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder skinColor(@NotNull final String skinColor) {
      this.skinColor = skinColor;
      return this;
    }

    /**
     * sets the given skinData.
     *
     * @param skinData the skinData to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder skinData(@NotNull final ImageData skinData) {
      this.skinData = skinData;
      return this;
    }

    /**
     * sets the given skinId.
     *
     * @param skinId the skinId to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder skinId(@NotNull final String skinId) {
      this.skinId = skinId;
      return this;
    }

    /**
     * sets the given skinResourcePatch.
     *
     * @param skinResourcePatch the skinResourcePatch to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder skinResourcePatch(@NotNull final String skinResourcePatch) {
      this.skinResourcePatch = skinResourcePatch;
      return this;
    }

    /**
     * sets the given tintColors.
     *
     * @param tintColors the tintColors to set.
     *
     * @return {@code this} for builder chain.
     */
    public Builder tintColors(@NotNull final List<PersonaPieceTintData> tintColors) {
      this.tintColors = Collections.unmodifiableList(tintColors);
      return this;
    }
  }
}
