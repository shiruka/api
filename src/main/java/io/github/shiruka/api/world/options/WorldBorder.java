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

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents the world border.
 */
public interface WorldBorder {

  /**
   * the default center of a world border.
   */
  DoubleXZ DEFAULT_CENTER = new DoubleXZ(0, 0);

  /**
   * the default damage per second dealt to a player outside the safe zone.
   */
  double DEFAULT_DAMAGE = 0.2;

  /**
   * the default distance for the safe zone and warning zone.
   */
  int DEFAULT_SAFE_AND_WARN_DIST = 5;

  /**
   * the default width between two sides of a world border.
   */
  double DEFAULT_SIZE = 60_000_000;

  /**
   * the default seconds when a border will reach a player before they are warned.
   */
  int DEFAULT_WARN_TIME = 15;

  /**
   * obtains the center of the world border.
   *
   * @return the center of the border.
   */
  @NotNull
  DoubleXZ getCenter();

  /**
   * sets the center of the world border.
   *
   * @param center the new border center.
   */
  void setCenter(@NotNull DoubleXZ center);

  /**
   * obtains the amount of damage being outside the world border will be dealt to a player each second.
   *
   * @return the damage per second, where 0.5 is half a heart.
   */
  double getDamage();

  /**
   * sets the damage dealt per second to players outside the safe zone behind the world border.
   *
   * @param damage the damage, where 0.5 is half a heart.
   *
   * @see #getDamage()
   */
  void setDamage(double damage);

  /**
   * obtains the N amount of blocks away from the world border where players will not be dealt damage until
   * they reach N+1 blocks away from the safe zone.
   *
   * @return the amount of blocks players are safe before being dealt damage.
   */
  double getSafeZoneDistance();

  /**
   * sets the amount of blocks on the other side of the world border which the player must be in order to be
   * dealt damage.
   *
   * @param size the amount of blocks away from the border.
   */
  void setSafeZoneDistance(int size);

  /**
   * obtains the <em>current</em> size of the world border.
   *
   * @return the border size.
   */
  double getSize();

  /**
   * obtains the size that is expected to be of the world border once {@link #getTargetTime()} seconds have
   * elapsed unless another call to either {@link #setSize(double, long)} or
   * {@link #grow(double, long)} occurs during the time elapsed, in which case this method will return target
   * size set by the newest invocation.
   *
   * @return the size that this border is set to grow.
   */
  double getTargetSize();

  /**
   * obtains the remaining time that it will take for {@link #getSize()} to reach {@link #getTargetSize()}
   * in seconds, if the border size is not already at the target size.
   *
   * @return the time the border will take to shrink or grow in MILLISECONDS.
   */
  long getTargetTime();

  /**
   * obtains the distance away from the other side of the world border where players will be warned by the
   * screen being tinted red.
   *
   * @return the blocks away from the border that players will be warned.
   */
  int getWarnDistance();

  /**
   * sets the distance away from the border which players will be warned.
   *
   * @param dist the blocks away from the border which to warn players.
   */
  void setWarnDistance(int dist);

  /**
   * obtains the time in which the player will be warned before a shrinking world border will reach them.
   *
   * @return the time in seconds before the border will reach the player until they are warned.
   */
  int getWarnTime();

  /**
   * sets the number of seconds before a shrinking world border will reach a player in which they will be warned.
   *
   * @param seconds the number of seconds until the border will reach the player before warning the player.
   */
  void setWarnTime(int seconds);

  /**
   * grows the <em>TARGET</em> size by the given amount, shrinking it for negative numbers.
   *
   * @param delta the size to grow or shrink, if it is negative.
   * @param time the time in MILLIS that the border to grow or shrink to the new size, or
   * {@code 0} to take immediate effect.
   */
  void grow(double delta, long time);

  /**
   * adds the given distance (negative numbers valid) to the distance away from the border which the player
   * must be in order to be dealt damage.
   *
   * @param dist the distance to grow or shrink.
   */
  void growWarnDistance(int dist);

  /**
   * initializes the world border, bringing it into view for players on this world.
   */
  void init();

  /**
   * sets the size of the world border.
   *
   * @param size the new size to set the world border to.
   * @param time the time in MILLIS that the border to grow or shrink to the new size, or
   * {@code 0} to take immediate effect.
   */
  void setSize(double size, long time);

  /**
   * represents a pair of doubles that signify the X and Z coordinates of a world border's center.
   */
  class DoubleXZ {

    /**
     * the x.
     */
    private double x;

    /**
     * the z.
     */
    private double z;

    /**
     * ctor.
     *
     * @param x the x.
     * @param z the z.
     */
    public DoubleXZ(final double x, final double z) {
      this.x = x;
      this.z = z;
    }

    /**
     * obtains the x.
     *
     * @return the x.
     */
    public double getX() {
      return this.x;
    }

    /**
     * sets the x.
     *
     * @param x the to set.
     */
    public void setX(final double x) {
      this.x = x;
    }

    /**
     * obtains the z.
     *
     * @return the z.
     */
    public double getZ() {
      return this.z;
    }

    /**
     * sets the z.
     *
     * @param z to set.
     */
    public void setZ(final double z) {
      this.z = z;
    }
  }
}
