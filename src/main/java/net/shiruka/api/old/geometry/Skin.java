package net.shiruka.api.old.geometry;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents skins.
 */
@Getter
@EqualsAndHashCode
@ToString(exclude = {"geometryData"})
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
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
  @Getter
  private final String animationData;

  /**
   * the animations.
   */
  @Nullable
  @Getter
  private final List<AnimationData> animations;

  /**
   * the arm size.
   */
  @NotNull
  @Getter
  private final String armSize;

  /**
   * the cape data.
   */
  @NotNull
  @Getter
  private final ImageData capeData;

  /**
   * the cape id.
   */
  @NotNull
  @Getter
  private final String capeId;

  /**
   * the cape on classic.
   */
  @Getter
  private final boolean capeOnClassic;

  /**
   * the full skin id.
   */
  @NotNull
  @Getter
  private final String fullSkinId;

  /**
   * the geometry data.
   */
  @Nullable
  @Getter
  private final String geometryData;

  /**
   * the geometry name.
   */
  @NotNull
  @Getter
  private final String geometryName;

  /**
   * the persona.
   */
  @Getter
  private final boolean persona;

  /**
   * the persona pieces.
   */
  @NotNull
  @Getter
  private final List<PersonaPieceData> personaPieces;

  /**
   * the play fab id.
   */
  private final String playFabId;

  /**
   * the premium.
   */
  @Getter
  private final boolean premium;

  /**
   * the skin color.
   */
  @NotNull
  @Getter
  private final String skinColor;

  /**
   * the skin data.
   */
  @Nullable
  @Getter
  private final ImageData skinData;

  /**
   * the skin id.
   */
  @Nullable
  @Getter
  private final String skinId;

  /**
   * the skin resource patch.
   */
  @NotNull
  @Getter
  private final String skinResourcePatch;

  /**
   * the tint colors.
   */
  @NotNull
  @Getter
  private final List<PersonaPieceTintData> tintColors;

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
   * @param capeData the cape data.
   * @param geometryData the geometry data.
   * @param geometryName the geometry name.
   * @param playFabId the play fab id.
   * @param premium the premium.
   * @param skinData the skin data.
   * @param skinId the skin id.
   *
   * @return a newly created skin instance.
   */
  @NotNull
  public static Skin of(@NotNull final String skinId, @NotNull final String playFabId,
                        @NotNull final ImageData skinData, @NotNull final ImageData capeData,
                        @NotNull final String geometryName, @NotNull final String geometryData,
                        final boolean premium) {
    skinData.checkLegacySkinSize();
    capeData.checkLegacyCapeSize();
    final String skinResourcePatch = Skin.convertLegacyGeometryName(geometryName);
    return new Skin("", Collections.emptyList(), "wide", capeData, "", false,
      "", geometryData, geometryName, false, Collections.emptyList(), playFabId, premium,
      "#0", skinData, skinId, skinResourcePatch, Collections.emptyList());
  }

  /**
   * ctor.
   *
   * @param animationData the animation data.
   * @param animations the animations.
   * @param capeData the cape data.
   * @param capeId the cape id.
   * @param capeOnClassic the cape on classic.
   * @param fullSkinId the full skin id.
   * @param geometryData the geometry data.
   * @param persona the persona.
   * @param playFabId the play fab id.
   * @param premium the premium.
   * @param skinData the skin data.
   * @param skinId the skin id.
   * @param skinResourcePatch the skin resource patch.
   *
   * @return a newly created skin instance.
   */
  @NotNull
  public static Skin of(@Nullable final String skinId, @NotNull final String playFabId,
                        @NotNull final String skinResourcePatch, @Nullable final ImageData skinData,
                        @NotNull final List<AnimationData> animations, @NotNull final ImageData capeData,
                        @Nullable final String geometryData, @NotNull final String animationData, final boolean premium,
                        final boolean persona, final boolean capeOnClassic, @NotNull final String capeId,
                        @NotNull final String fullSkinId) {
    return Skin.of(skinId, playFabId, skinResourcePatch, skinData,
      Collections.unmodifiableList(new ObjectArrayList<>(animations)), capeData, geometryData, animationData, premium,
      persona, capeOnClassic, capeId, fullSkinId, "wide", "#0", Collections.emptyList(),
      Collections.emptyList());
  }

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
   * @param persona the persona.
   * @param personaPieces the persona pieces.
   * @param playFabId the play fab id.
   * @param premium the premium.
   * @param skinColor the skin color.
   * @param skinData the skin data.
   * @param skinId the skin id.
   * @param skinResourcePatch the skin resource patch.
   * @param tintColors the tint colors.
   *
   * @return a newly created skin instance.
   */
  @NotNull
  public static Skin of(@Nullable final String skinId, @NotNull final String playFabId,
                        @NotNull final String skinResourcePatch, @Nullable final ImageData skinData,
                        @NotNull final List<AnimationData> animations, @NotNull final ImageData capeData,
                        @Nullable final String geometryData, @NotNull final String animationData, final boolean premium,
                        final boolean persona, final boolean capeOnClassic, @NotNull final String capeId,
                        @NotNull final String fullSkinId, @NotNull final String armSize,
                        @NotNull final String skinColor, @NotNull final List<PersonaPieceData> personaPieces,
                        @NotNull final List<PersonaPieceTintData> tintColors) {
    final var geometryName = Skin.convertSkinPatchToLegacy(skinResourcePatch);
    return new Skin(animationData, Collections.unmodifiableList(new ObjectArrayList<>(animations)), armSize, capeData,
      capeId, capeOnClassic, fullSkinId, geometryData, geometryName, persona, personaPieces, playFabId, premium,
      skinColor, skinData, skinId, skinResourcePatch, tintColors);
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
      return geometry.containsKey("default") &&
        geometry.get("default") instanceof String;
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
    return this.skinId != null &&
      !this.skinId.trim().isEmpty() &&
      this.skinData != null &&
      this.skinData.getWidth() >= 64 &&
      this.skinData.getHeight() >= 32 &&
      this.skinData.getImage().length >= Skin.SINGLE_SKIN_SIZE &&
      Skin.validateSkinResourcePatch(this.skinResourcePatch);
  }

  /**
   * a builder class that helps to create a new {@link Skin} instance.
   */
  public static final class Builder {

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
    @NotNull
    private List<PersonaPieceData> personaPieces = Collections.emptyList();

    /**
     * the play fad id.
     */
    @NotNull
    private String playFabId = "";

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
      return Skin.of(this.skinId, this.playFabId, skinOrGeometry, this.skinData, this.animations, this.capeData,
        this.geometryData, this.animationData, this.premium, this.persona, this.capeOnClassic, this.capeId,
        this.fullSkinId);
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
    public Builder fullSkinId(@Nullable final String fullSkinId) {
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
    public Builder geometryData(@Nullable final String geometryData) {
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
    public Builder geometryName(@Nullable final String geometryName) {
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
     * sets the given play fab id.
     *
     * @param playFabId the play fab id to set.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder setPlayFabId(final String playFabId) {
      this.playFabId = playFabId;
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
    public Builder skinData(@Nullable final ImageData skinData) {
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
    public Builder skinId(@Nullable final String skinId) {
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
    public Builder skinResourcePatch(@Nullable final String skinResourcePatch) {
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
