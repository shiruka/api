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

package net.shiruka.api.base;

import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;
import net.shiruka.api.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that represents the various type of game modes that {@link HumanEntity}s may have.
 */
public enum GameMode {
  /**
   * the creative mode may fly, build instantly, become invulnerable and create free items.
   */
  CREATIVE(1, "Creative"),
  /**
   * the survival mode is the "normal" gameplay type, with no special features.
   */
  SURVIVAL(0, "Survival"),
  /**
   * the adventure mode cannot break blocks without the correct tools.
   */
  ADVENTURE(2, "Adventure"),
  /**
   * spectator mode cannot interact with the world in anyway and is invisible to normal players.
   * This grants the player the ability to no-clip through the world.
   */
  SPECTATOR(3, "Spectator");

  /**
   * the id.
   */
  private final int id;

  /**
   * the type.
   */
  @NotNull
  private final String type;

  /**
   * ctor.
   *
   * @param id the id.
   * @param type the type.
   */
  GameMode(final int id, @NotNull final String type) {
    this.id = id;
    this.type = type;
  }

  /**
   * gets a game mode instance from the given {@code id}.
   *
   * @param id the id to get.
   *
   * @return a game mode instance.
   */
  @NotNull
  public static Optional<GameMode> fromId(final int id) {
    return Stream.of(GameMode.values())
      .filter(gameMode -> gameMode.id == id)
      .findFirst();
  }

  /**
   * gets a game mode instance from the given {@code type}.
   *
   * @param type the type to get.
   *
   * @return a game mode instance.
   */
  @NotNull
  public static Optional<GameMode> fromType(@NotNull final String type) {
    return Stream.of(GameMode.values())
      .filter(gameMode -> gameMode.type.equalsIgnoreCase(type.toLowerCase(Locale.ROOT)))
      .findFirst();
  }

  /**
   * obtains the id.
   *
   * @return id.
   */
  public int getId() {
    return this.id;
  }

  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  public String getType() {
    return this.type;
  }
}
