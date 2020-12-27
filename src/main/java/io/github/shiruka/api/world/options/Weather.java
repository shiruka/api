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

import io.github.shiruka.api.base.Tick;

/**
 * allows access to the world's current weather state such as rain, thunder, and sun time.
 */
public interface Weather extends Tick {

  /**
   * specifier passed to any method taking time to set the time to a random value.
   */
  int RANDOM_TIME = -1;

  /**
   * begins raining.
   */
  void beginRaining();

  /**
   * begins thundering, if it is currently raining.
   * otherwise has no effect.
   */
  void beginThunder();

  /**
   * causes the weather to cease raining and thundering.
   */
  void clear();

  /**
   * obtains the amount of ticks until clear weather is toggled to or from clear weather and this time will be reset.
   *
   * @return the amount of ticks until it is toggled.
   */
  int getClearTime();

  /**
   * sets the amount of ticks until clear weather is toggled in the world.
   *
   * @param ticks the amount of ticks.
   */
  void setClearTime(int ticks);

  /**
   * obtains the amount of ticks until the weather will be toggled to or from raining, and the time will be reset.
   *
   * @return the ticks until raining is toggled.
   */
  int getRainTime();

  /**
   * sets the amount of ticks until raining is toggled in the world.
   *
   * @param ticks the amount of ticks.
   */
  void setRainTime(int ticks);

  /**
   * obtains the amount of ticks until the weather will be toggled to or from thundering and this time will be reset.
   *
   * @return the amount of ticks until it is toggled.
   */
  int getThunderTime();

  /**
   * sets the amount of ticks until thundering is toggled in the world.
   *
   * @param ticks the amount of ticks.
   */
  void setThunderTime(int ticks);

  /**
   * obtains if the weather is currently clear.
   *
   * @return {@code true} if the weather is clear, {@code false} if it isn't.
   */
  boolean isClear();

  /**
   * whether or not the weather is currently raining in the world.
   *
   * @return {@code true} if it is raining, {@code false} if it is not.
   */
  boolean isRaining();

  /**
   * checks if the world is currently cloudy and thundering.
   *
   * @return {@code true} if it is thundering, {@code false} otherwise.
   */
  boolean isThundering();

  /**
   * ceases thundering.
   */
  void stopThunder();
}
