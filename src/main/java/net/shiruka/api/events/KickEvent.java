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

package net.shiruka.api.events;

import java.util.Optional;
import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine kick events.
 */
public interface KickEvent extends Event {

  /**
   * obtains the kick message.
   *
   * @return kick message.
   */
  @NotNull
  Optional<Text> getKickMessage();

  /**
   * sets the kick message.
   *
   * @param message the message to set.
   */
  void setKickMessage(@Nullable Text message);

  /**
   * sets the kick message.
   *
   * @param message the message to set.
   */
  default void setKickMessage(@Nullable final String message) {
    if (message == null) {
      this.setKickMessage((Text) null);
    } else {
      this.setKickMessage(() -> message);
    }
  }

  /**
   * an enum class that represents the kick's reason.
   */
  enum Reason {
    /**
     * the new connection.
     */
    NEW_CONNECTION,
    /**
     * the already logged in.
     */
    ALREADY_LOGGED_IN,
    /**
     * the kicked by admin.
     */
    KICKED_BY_ADMIN,
    /**
     * the not whitelisted.
     */
    NOT_WHITELISTED,
    /**
     * the ip banned.
     */
    IP_BANNED,
    /**
     * the name banned.
     */
    NAME_BANNED,
    /**
     * the invalid pve.
     */
    INVALID_PVE,
    /**
     * the login timeout.
     */
    LOGIN_TIMEOUT,
    /**
     * the server full.
     */
    SERVER_FULL,
    /**
     * the flying disabled.
     */
    FLYING_DISABLED,
    /**
     * the unknown.
     */
    UNKNOWN
  }
}
