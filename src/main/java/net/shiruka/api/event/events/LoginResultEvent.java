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

package net.shiruka.api.event.events;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine login result events.
 */
public interface LoginResultEvent {

  /**
   * obtains the login result.
   *
   * @return login result.
   */
  @NotNull
  LoginResult getLoginResult();

  /**
   * sets the login result.
   *
   * @param result the result to set.
   */
  void setLoginResult(@NotNull LoginResult result);

  /**
   * an enum class to determine login result.
   */
  enum LoginResult {

    /**
     * the player is allowed to log in.
     */
    ALLOWED,
    /**
     * the player is not allowed to log in, due to the server being full.
     */
    KICK_FULL,
    /**
     * the player is not allowed to log in, due to them being banned.
     */
    KICK_BANNED,
    /**
     * the player is not allowed to log in, due to them not being on the white list.
     */
    KICK_WHITELIST,
    /**
     * the player is not allowed to log in, for reasons undefined.
     */
    KICK_OTHER
  }
}
