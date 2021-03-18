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

import net.shiruka.api.event.events.PlayerEvent;
import net.shiruka.api.event.events.QuitMessageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * called when a player leaves a server.
 */
public interface PlayerQuitEvent extends PlayerEvent, QuitMessageEvent {

  /**
   * obtains the quit reason.
   *
   * @return quit reason.
   */
  @NotNull
  QuitReason getQuitReason();

  /**
   * an enum class that contains quit reasons.
   */
  enum QuitReason {
    /**
     * the player left on their own behalf.
     */
    DISCONNECTED,
    /**
     * the player was kicked from the server.
     */
    KICKED,
    /**
     * the player has timed out.
     */
    TIMED_OUT,
    /**
     * the player's connection has entered an erroneous state.
     */
    ERRONEOUS_STATE
  }
}
