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

package net.shiruka.api.event.events.player;

import java.util.function.Consumer;
import net.shiruka.api.entity.Player;
import net.shiruka.api.event.events.ChainDataEvent;
import net.shiruka.api.event.events.KickEvent;
import net.shiruka.api.event.events.LoginResultEvent;
import net.shiruka.api.event.events.ObjectListEvent;
import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine player's async login events.
 */
public interface PlayerAsyncLoginEvent extends ChainDataEvent, KickEvent, LoginResultEvent,
  ObjectListEvent<Consumer<Player>> {

  /**
   * allows the player to join.
   */
  default void allow() {
    this.setLoginResult(LoginResult.ALLOWED);
  }

  /**
   * kicks the player with the given kick message.
   *
   * @param text the text to disallow.
   * @param result the result to disallow.
   */
  default void disallow(@NotNull final LoginResult result, @Nullable final Text text) {
    this.setLoginResult(result);
    this.setKickMessage(text);
  }

  @Override
  default boolean isAsync() {
    return true;
  }
}
