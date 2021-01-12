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

package io.github.shiruka.api.entity;

import io.github.shiruka.api.Server;
import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.base.OfflinePlayer;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.events.player.PlayerKickEvent;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine players on the Minecraft.
 */
public interface Player extends Entity, CommandSender, OfflinePlayer {

  /**
   * obtains the server.
   *
   * @return the server.
   */
  @NotNull
  Server getServer();

  default boolean kick() {
    return this.kick("");
  }

  default boolean kick(@NotNull final String reason, final boolean isAdmin) {
    return this.kick(PlayerKickEvent.Reason.UNKNOWN, reason, isAdmin);
  }

  default boolean kick(@NotNull final String reason) {
    return this.kick(PlayerKickEvent.Reason.UNKNOWN, reason);
  }

  default boolean kick(@NotNull final PlayerKickEvent.Reason reason) {
    return this.kick(reason, true);
  }

  default boolean kick(@NotNull final PlayerKickEvent.Reason reason, @NotNull final String reasonString) {
    return this.kick(reason, reasonString, true);
  }

  default boolean kick(@NotNull final PlayerKickEvent.Reason reason, final boolean isAdmin) {
    return this.kick(reason, reason.toString(), isAdmin);
  }

  default boolean kick(@NotNull final PlayerKickEvent.Reason reason, @NotNull final String reasonString,
                       final boolean isAdmin) {
    final var event = Shiruka.getEventManager().playerKick(this, reason, this.getLeaveMessage());
    event.callEvent();
    if (event.cancelled()) {
      return false;
    }
    final String message;
    if (isAdmin) {
      if (!this.isBanned()) {
        message = "Kicked by admin." + (!reasonString.isEmpty() ? " Reason: " + reasonString : "");
      } else {
        message = reasonString;
      }
    } else {
      if (reasonString.isEmpty()) {
        message = "disconnectionScreen.noReason";
      } else {
        message = reasonString;
      }
    }
    this.close(event.getQuitMessage(), message);
    return true;
  }
}
