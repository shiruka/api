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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;

/**
 * a game rule map is needed because game rules behave
 * specially in that if they are not changed, they have a
 * default value which the map can return, which increases
 * both performance and memory efficiency.
 */
public final class GameRuleMap {

  /**
   * the internal map representing the changes made to the default value of the game rule.
   */
  private final Map<GameRule<?>, Object> changes = new ConcurrentHashMap<>();

  /**
   * copies the changes to the game rule values held in the given map to this map.
   *
   * @param map the map to copy.
   */
  public void copyTo(@NotNull final GameRuleMap map) {
    map.changes.putAll(this.changes);
  }

  /**
   * gets the value of the given game rule that is set in this map.
   *
   * @param key the game rule to get.
   * @param <T> the type of value.
   *
   * @return the value.
   */
  @NotNull
  public <T> T get(@NotNull final GameRule<T> key) {
    //noinspection unchecked
    final var t = (T) this.changes.get(key);
    if (t == null) {
      return key.getDefault();
    }
    return t;
  }

  /**
   * obtains whether the given game rule has been set in this game rule map or not.
   *
   * @param key the game rule to check.
   * @param <T> the type of value.
   *
   * @return {@code true} if the game rule has been set,{@code false} if it is still default.
   */
  public <T> boolean isSet(@NotNull final GameRule<T> key) {
    return this.changes.containsKey(key);
  }

  /**
   * resets the given game rule to its default.
   *
   * @param key the game rule to reset.
   * @param <T> the type of value held by the game rule.
   */
  public <T> void reset(@NotNull final GameRule<T> key) {
    this.changes.remove(key);
  }

  /**
   * resets all of the game rules that were changed to their respective defaults.
   */
  public void resetAll() {
    this.changes.clear();
  }

  /**
   * sets the value of the game rule to the given value.
   *
   * @param key the game rule to set.
   * @param value the value to set.
   * @param <T> the type of value.
   */
  public <T> void set(@NotNull final GameRule<T> key, @NotNull final T value) {
    if (!key.getDefault().equals(value)) {
      this.changes.put(key, value);
    }
  }
}
