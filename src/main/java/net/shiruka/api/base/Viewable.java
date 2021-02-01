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

import java.util.Set;
import net.shiruka.api.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine something which can be displayed or hidden to players.
 */
public interface Viewable {

  /**
   * adds a viewer.
   *
   * @param player the player to add.
   *
   * @return {@code true} if the player has been added, {@code false} otherwise.
   */
  boolean addViewer(@NotNull Player player);

  /**
   * obtains the viewers.
   *
   * @return viewers.
   */
  @NotNull
  Set<Player> getViewers();

  /**
   * gets if a player is seeing this viewable object.
   *
   * @param player the player to check.
   *
   * @return {@code true} if {@code player} is a viewer, {@code false} otherwise.
   */
  default boolean isViewer(@NotNull final Player player) {
    return this.getViewers().contains(player);
  }

  /**
   * removes a viewer.
   *
   * @param player the viewer to remove.
   *
   * @return {@code true} if the player has been removed, {@code false} otherwise.
   */
  boolean removeViewer(@NotNull Player player);
}
