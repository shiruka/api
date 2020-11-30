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

import io.github.shiruka.api.misc.Misc;
import io.github.shiruka.api.misc.Optionals;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

/**
 * this is a set of possible game rules that may be applied to a world.
 *
 * @param <T> the type of value that the game rule holds.
 *
 * @see WorldOptions
 * @see GameRuleMap
 */
public final class GameRule<T> {

  /**
   * whether player achievements are announced to players in this world.
   */
  @NotNull
  public static final GameRule<Boolean> ANNOUNCE_ADVANCEMENT;

  /**
   * whether or not actions performed by command blocks are displayed in the chat.
   */
  @NotNull
  public static final GameRule<Boolean> CMD_BLOCK_OUTPUT;

  /**
   * whether to do the Daylight Cycle or not.
   */
  @NotNull
  public static final GameRule<Boolean> DAYLIGHT_CYCLE;

  /**
   * whether non-living entities have drops.
   */
  @NotNull
  public static final GameRule<Boolean> DO_ENTITY_DROPS;

  /**
   * whether to spread or remove fire.
   */
  @NotNull
  public static final GameRule<Boolean> FIRE_TICK;

  /**
   * whether players keep their inventory after they die.
   */
  @NotNull
  public static final GameRule<Boolean> KEEP_INVENTORY;

  /**
   * whether crafting should be limited to only unlocked recipes.
   */
  @NotNull
  public static final GameRule<Boolean> LIMIT_CRAFTING;

  /**
   * whether to log admin commands to server log.
   */
  @NotNull
  public static final GameRule<Boolean> LOG_ADMIN_CMDS;

  /**
   * the maximum number of commands that may be executed by a function file.
   */
  @NotNull
  public static final GameRule<Integer> MAX_CMD_CHAIN_LEN;

  /**
   * the maximum number of entities a player can push before they begin to take 3 damage points (1.5 hearts).
   */
  @NotNull
  public static final GameRule<Integer> MAX_ENTITY_CRAM;

  /**
   * whether mobs can destroy blocks.
   */
  @NotNull
  public static final GameRule<Boolean> MOB_GRIEF;

  /**
   * whether mobs should drop loot when killed.
   */
  @NotNull
  public static final GameRule<Boolean> MOB_LOOT;

  /**
   * whether mobs should spawn naturally.
   */
  @NotNull
  public static final GameRule<Boolean> MOB_SPAWN;

  /**
   * Whether the server should disable checking if the player is moving too fast (cheating) while wearing Elytra.
   */
  @NotNull
  public static final GameRule<Boolean> MOVE_CHECK;

  /**
   * whether the player naturally regenerates health if their hunger is high enough.
   */
  @NotNull
  public static final GameRule<Boolean> NATURAL_REGEN;

  /**
   * how often a random tick occurs, such as plant growth, leaf decay, etc.
   */
  @NotNull
  public static final GameRule<Integer> RANDOM_TICK_SPEED;

  /**
   * whether players on this world will have reduced debug info available in the F3 menu.
   */
  @NotNull
  public static final GameRule<Boolean> REDUCE_DEBUG;

  /**
   * whether the feedback from commands executed by a player should show up in chat.
   */
  @NotNull
  public static final GameRule<Boolean> SEND_CMD_FEEDBACK;

  /**
   * whether a message appears in chat when a player dies.
   */
  @NotNull
  public static final GameRule<Boolean> SHOW_DEATH_MSG;

  /**
   * The distance from spawn that players will initially spawn or respawn without a bed.
   */
  @NotNull
  public static final GameRule<Integer> SPAWN_RADIUS;

  /**
   * whether or not spectator players are allowed to generate chunks (and therefore move freely throughout the world).
   */
  @NotNull
  public static final GameRule<Boolean> SPEC_GEN_CHUNKS;

  /**
   * the name of the function file to use when the server is ticked.
   */
  @NotNull
  public static final GameRule<String> TICK_FUNCTION;

  /**
   * whether breaking blocks should drop the block's item drop.
   */
  @NotNull
  public static final GameRule<Boolean> TILE_DROP;

  /**
   * if the weather does toggle between rain, thunder and clear.
   */
  @NotNull
  public static final GameRule<Boolean> WEATHER_CYCLE;

  /**
   * the game rule values.
   */
  @NotNull
  private static final Map<String, GameRule<?>> GAME_RULES;

  /**
   * the default value given to the game rule.
   */
  @NotNull
  private final T defValue;

  /**
   * the function used to parse the String value into a game rule value.
   */
  @NotNull
  private final Function<String, T> parseFunction;

  /**
   * the raw minecraft form of the game rule.
   */
  @NotNull
  private final String stringForm;

  static {
    GAME_RULES = new HashMap<>();
    ANNOUNCE_ADVANCEMENT = GameRule.newRule("announceAdvancements", true);
    CMD_BLOCK_OUTPUT = GameRule.newRule("commandBlockOutput", true);
    MOVE_CHECK = GameRule.newRule("disableElytraMovementCheck", false);
    DAYLIGHT_CYCLE = GameRule.newRule("doDaylightCycle", true);
    DO_ENTITY_DROPS = GameRule.newRule("doEntityDrops", true);
    FIRE_TICK = GameRule.newRule("doFireTick", true);
    LIMIT_CRAFTING = GameRule.newRule("doLimitedCrafting", false);
    MOB_LOOT = GameRule.newRule("doMobLoot", true);
    MOB_SPAWN = GameRule.newRule("doMobSpawning", true);
    TILE_DROP = GameRule.newRule("doTileDrops", true);
    WEATHER_CYCLE = GameRule.newRule("doWeatherCycle", true);
    TICK_FUNCTION = GameRule.newRule("gameLoopFunction", "");
    KEEP_INVENTORY = GameRule.newRule("keepInventory", false);
    LOG_ADMIN_CMDS = GameRule.newRule("logAdminCommands", true);
    MAX_CMD_CHAIN_LEN = GameRule.newRule("maxCommandChainLength", 65536);
    MAX_ENTITY_CRAM = GameRule.newRule("maxEntityCramming", 24);
    MOB_GRIEF = GameRule.newRule("mobGriefing", true);
    NATURAL_REGEN = GameRule.newRule("naturalRegeneration", true);
    RANDOM_TICK_SPEED = GameRule.newRule("randomTickSpeed", 3);
    REDUCE_DEBUG = GameRule.newRule("reducedDebugInfo", false);
    SEND_CMD_FEEDBACK = GameRule.newRule("sendCommandFeedback", true);
    SHOW_DEATH_MSG = GameRule.newRule("showDeathMessages", true);
    SPAWN_RADIUS = GameRule.newRule("spawnRadius", 10);
    SPEC_GEN_CHUNKS = GameRule.newRule("spectatorsGenerateChunks", true);
  }

  /**
   * ctor.
   *
   * @param stringForm the raw string form of the rule.
   * @param defValue the default value.
   */
  private GameRule(@NotNull final String stringForm, @NotNull final T defValue,
                   @NotNull final Function<String, T> parseFunction) {
    this.stringForm = stringForm;
    this.defValue = defValue;
    this.parseFunction = parseFunction;
  }

  /**
   * obtains the game rule using the raw string form in the NBT format.
   *
   * @param <T> the type of value that the game rule holds.
   * @param s the NBT form of the game rule to find.
   *
   * @return the game rule, if found.
   *
   * @throws IllegalArgumentException if the game rule is not found.
   */
  @NotNull
  public static <T> GameRule<T> from(@NotNull final String s) {
    final var rule = GameRule.GAME_RULES.get(s);
    if (rule == null) {
      throw new IllegalArgumentException(
        String.format(Misc.NBT_BOUND_FAIL, "io.github.shiruka.api.world.options.GameRule (" + s + ')'));
    }
    //noinspection unchecked
    return (GameRule<T>) rule;
  }

  /**
   * obtains all the possible game rule keys.
   *
   * @return the set of valid game rule keys, not modifiable.
   */
  @NotNull
  public static Collection<String> getKeyStrings() {
    return Collections.unmodifiableCollection(GameRule.GAME_RULES.keySet());
  }

  /**
   * factory method shortcut for creating new game rule constant values.
   *
   * @param name NBT name of the game rule.
   * @param defValue the default value.
   *
   * @return the new game rule.
   */
  @NotNull
  private static GameRule<Boolean> newRule(@NotNull final String name, final boolean defValue) {
    return Optionals.useAndGet(new GameRule<>(name, defValue, s -> s.equals("true")), rule ->
      GameRule.GAME_RULES.put(name, rule));
  }

  /**
   * factory method shortcut for creating new game rule constant values.
   *
   * @param name NBT name of the game rule.
   * @param defValue the default value.
   *
   * @return the new game rule.
   */
  @NotNull
  private static GameRule<Integer> newRule(@NotNull final String name, final int defValue) {
    return Optionals.useAndGet(new GameRule<>(name, defValue, Integer::parseInt), rule ->
      GameRule.GAME_RULES.put(name, rule));
  }

  /**
   * factory method shortcut for creating new game rule constant values.
   *
   * @param name NBT name of the game rule.
   * @param defValue the default value.
   *
   * @return the new game rule.
   */
  @NotNull
  private static GameRule<String> newRule(@NotNull final String name, @NotNull final String defValue) {
    return Optionals.useAndGet(new GameRule<>(name, defValue, s -> s), rule ->
      GameRule.GAME_RULES.put(name, rule));
  }

  /**
   * obtains the default value for the given game rule.
   *
   * @return the vanilla default value.
   */
  @NotNull
  public T getDefault() {
    return this.defValue;
  }

  /**
   * parses the game rule value string from a compound into a game rule value.
   *
   * @param value the value to parse.
   *
   * @return the game rule value.
   */
  @NotNull
  public T parseValue(@NotNull final String value) {
    return this.parseFunction.apply(value);
  }

  @Override
  public String toString() {
    return this.stringForm;
  }
}
