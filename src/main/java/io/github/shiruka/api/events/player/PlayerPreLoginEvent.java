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

package io.github.shiruka.api.events.player;

import io.github.shiruka.api.event.Cancellable;
import io.github.shiruka.api.events.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * called when the player logs in, before things have been set up.
 */
public interface PlayerPreLoginEvent extends PlayerEvent, Cancellable {

  /**
   * obtains the kick message.
   *
   * @return kick message.
   */
  @Nullable
  String kickMessage();

  /**
   * sets the kick message.
   *
   * @param message the message to set.
   */
  void kickMessage(@Nullable String message);

  /**
   * obtains the login data.
   *
   * @return login data.
   */
  @NotNull
  LoginData loginData();

  /**
   * an interface to determine chain data.
   */
  interface ChainData {

  }

  /**
   * an interface to determine login data.
   */
  interface LoginData {

    /**
     * obtains the chain data.
     *
     * @return chain data.
     */
    @NotNull
    ChainData chainData();
  }
}
