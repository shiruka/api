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

import io.github.shiruka.api.base.GameProfile;
import io.github.shiruka.api.base.OfflinePlayer;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.events.KickEvent;
import io.github.shiruka.api.events.LoginDataEvent;
import io.github.shiruka.api.text.Text;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine players on the Minecraft.
 */
public interface Player extends Entity, CommandSender, OfflinePlayer {

  /**
   * obtains the chain data.
   *
   * @return chain data.
   */
  @NotNull
  LoginDataEvent.ChainData getChainData();

  @NotNull
  @Override
  default Text getName() {
    return this.getProfile().getName();
  }

  /**
   * obtains the ping.
   *
   * @return ping.
   */
  long getPing();

  /**
   * obtains the profile.
   *
   * @return profile.
   */
  @NotNull
  GameProfile getProfile();

  @NotNull
  @Override
  default UUID getUniqueId() {
    return this.getProfile().getUniqueId();
  }

  /**
   * kicks the player.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  default boolean kick() {
    return this.kick(() -> "");
  }

  /**
   * kicks the player.
   *
   * @param reason the reason to kick.
   * @param isAdmin the is admin to kick.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  default boolean kick(@Nullable final Text reason, final boolean isAdmin) {
    return this.kick(KickEvent.Reason.UNKNOWN, reason, isAdmin);
  }

  /**
   * kicks the player.
   *
   * @param reason the reason to kick.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  default boolean kick(@Nullable final Text reason) {
    return this.kick(KickEvent.Reason.UNKNOWN, reason);
  }

  /**
   * kicks the player.
   *
   * @param reason the reason to kick.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  default boolean kick(@NotNull final KickEvent.Reason reason) {
    return this.kick(reason, true);
  }

  /**
   * kicks the player.
   *
   * @param reason the reason to kick.
   * @param reasonString the reason string.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  default boolean kick(@NotNull final KickEvent.Reason reason, @Nullable final Text reasonString) {
    return this.kick(reason, reasonString, true);
  }

  /**
   * kicks the player.
   *
   * @param reason the reason to kick.
   * @param isAdmin the is admin to kick.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  default boolean kick(@NotNull final KickEvent.Reason reason, final boolean isAdmin) {
    return this.kick(reason, reason::toString, isAdmin);
  }

  /**
   * kicks the player.
   *
   * @param reason the reason to kick.
   * @param reasonString the reason string.
   * @param isAdmin the is admin to kick.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  boolean kick(@NotNull KickEvent.Reason reason, @Nullable Text reasonString, boolean isAdmin);

  @Override
  default void remove() {
    // ignored.
  }
}
