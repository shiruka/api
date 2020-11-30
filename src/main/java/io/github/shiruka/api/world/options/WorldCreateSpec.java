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

package io.github.shiruka.api.world.options;

import io.github.shiruka.api.util.Vector;
import io.github.shiruka.api.world.generators.GeneratorProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * the options for creating a new world.
 */
public final class WorldCreateSpec {

  /**
   * the default instance of the world creator specification.
   */
  private static final WorldCreateSpec DEFAULT = new WorldCreateSpec(true);

  /**
   * whether this spec uses the default world options
   */
  private final boolean def;

  /**
   * whether world features such as villages, strongholds, and mine-shafts should be generated.
   */
  private boolean allowFeatures = true;

  /**
   * the difficulty of the world.
   */
  @NotNull
  private Difficulty difficulty = Difficulty.NORMAL;

  /**
   * if the difficulty is locked.
   */
  private boolean difficultyLocked;

  /**
   * the dimension.
   */
  @NotNull
  private Dimension dimension = Dimension.OVER_WORLD;

  /**
   * the game mode of the world.
   */
  @NotNull
  private GameMode gameMode = GameMode.SURVIVAL;

  /**
   * the game rule map.
   */
  @NotNull
  private GameRuleMap gameRules = new GameRuleMap();

  /**
   * the level type of the world.
   */
  @NotNull
  private LevelType levelType = LevelType.DEFAULT;

  /**
   * the option string of the world.
   */
  @NotNull
  private String optionString = "";

  /**
   * the generator provider.
   */
  @Nullable
  private GeneratorProvider provider;

  /**
   * the seed of the world.
   */
  private long seed;

  /**
   * the spawn.
   */
  @Nullable
  private Vector spawn;

  /**
   * ctor.
   *
   * @param def the default spec.
   */
  private WorldCreateSpec(final boolean def) {
    this.def = def;
  }

  /**
   * create a new custom world options specification that can be passed to the server world loader to
   * create a custom world.
   *
   * @return a new custom world specification.
   */
  @NotNull
  public static WorldCreateSpec custom() {
    return new WorldCreateSpec(false);
  }

  /**
   * uses the default world options to build the world.
   *
   * @return the default world specification.
   */
  @NotNull
  public static WorldCreateSpec getDefaultOptions() {
    return WorldCreateSpec.DEFAULT;
  }

  /**
   * obtains difficulty of the world.
   *
   * @return difficulty of the world.
   */
  @NotNull
  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  /**
   * sets the difficulty of the world.
   *
   * @param difficulty the difficulty to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setDifficulty(@NotNull final Difficulty difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   * obtains dimension of the world.
   *
   * @return dimension of the world.
   */
  @NotNull
  public Dimension getDimension() {
    return this.dimension;
  }

  /**
   * sets the world's dimension.
   *
   * @param dimension the dimension to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setDimension(@NotNull final Dimension dimension) {
    this.dimension = dimension;
    return this;
  }

  /**
   * obtains gameMode of the world.
   *
   * @return gameMode of the world.
   */
  @NotNull
  public GameMode getGameMode() {
    return this.gameMode;
  }

  /**
   * sets the game mode of the world.
   *
   * @param gameMode the game mode to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setGameMode(@NotNull final GameMode gameMode) {
    this.gameMode = gameMode;
    return this;
  }

  /**
   * obtains the game rule map of the world.
   *
   * @return the game rule map of the world.
   */
  @NotNull
  public GameRuleMap getGameRules() {
    return this.gameRules;
  }

  /**
   * sets the game rules of the world.
   *
   * @param gameRules the game rules to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setGameRules(@NotNull final GameRuleMap gameRules) {
    this.gameRules = gameRules;
    return this;
  }

  /**
   * obtains level type of the world.
   *
   * @return level type of the world.
   */
  @NotNull
  public LevelType getLevelType() {
    return this.levelType;
  }

  /**
   * sets the level type of the world.
   *
   * @param levelType the level type to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setLevelType(@NotNull final LevelType levelType) {
    this.levelType = levelType;
    return this;
  }

  /**
   * obtains option string of the world.
   *
   * @return option string of the world.
   */
  @NotNull
  public String getOptionString() {
    return this.optionString;
  }

  /**
   * sets the option string of the world.
   *
   * @param optionString the option string to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setOptionString(@NotNull final String optionString) {
    this.optionString = optionString;
    return this;
  }

  /**
   * obtains generator provider of the world.
   *
   * @return generator provider of the world.
   */
  @Nullable
  public GeneratorProvider getProvider() {
    return this.provider;
  }

  /**
   * sets the generator provider of the world.
   *
   * @param provider the provider to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setProvider(@NotNull final GeneratorProvider provider) {
    this.provider = provider;
    return this;
  }

  /**
   * obtains seed of the world.
   *
   * @return seed of the world.
   */
  public long getSeed() {
    return this.seed;
  }

  /**
   * sets the seed of the world.
   *
   * @param seed the seed to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setSeed(final long seed) {
    this.seed = seed;
    return this;
  }

  /**
   * obtains spawn of the world.
   *
   * @return spawn of the world.
   */
  @Nullable
  public Vector getSpawn() {
    return this.spawn;
  }

  /**
   * sets the world's spawn.
   *
   * @param spawn the spawn to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setSpawn(@Nullable final Vector spawn) {
    this.spawn = spawn;
    return this;
  }

  /**
   * whether world features such as villages, strongholds, and mine-shafts should be generated.
   *
   * @return a bool.
   */
  public boolean isAllowFeatures() {
    return this.allowFeatures;
  }

  /**
   * sets the allows features to the given value.
   *
   * @param allowFeatures the allow features to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setAllowFeatures(final boolean allowFeatures) {
    this.allowFeatures = allowFeatures;
    return this;
  }

  /**
   * determines whether this option specification is uses all default values or not.
   *
   * @return {@code true} for default values.
   */
  public boolean isDefault() {
    return this.def;
  }

  /**
   * obtains difficulty locked of the world.
   *
   * @return difficulty locked of the world.
   */
  public boolean isDifficultyLocked() {
    return this.difficultyLocked;
  }

  /**
   * sets the difficulty locked of the world.
   *
   * @param difficultyLocked the difficulty locked to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public WorldCreateSpec setDifficultyLocked(final boolean difficultyLocked) {
    this.difficultyLocked = difficultyLocked;
    return this;
  }
}
