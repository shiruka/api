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

import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine login events.
 */
public interface LoginEvent extends Event {

  /**
   * allows the player to join.
   */
  default void allow() {
    if (this instanceof LoginResultEvent) {
      ((LoginResultEvent) this).setLoginResult(LoginResultEvent.LoginResult.ALLOWED);
    }
  }

  /**
   * kicks the player with the given kick message.
   *
   * @param text the text to disallow.
   * @param result the result to disallow.
   */
  default void disallow(@NotNull final LoginResultEvent.LoginResult result, @NotNull final Text text) {
    if (this instanceof LoginResultEvent) {
      ((LoginResultEvent) this).setLoginResult(result);
    }
    if (this instanceof KickMessageEvent) {
      ((KickMessageEvent) this).setKickMessage(text);
    }
  }
}