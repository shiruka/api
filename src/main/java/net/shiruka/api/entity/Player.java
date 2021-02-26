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

package net.shiruka.api.entity;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Optional;
import net.shiruka.api.Shiruka;
import net.shiruka.api.base.BanEntry;
import net.shiruka.api.base.BanList;
import net.shiruka.api.base.ChainData;
import net.shiruka.api.base.GameProfile;
import net.shiruka.api.base.OfflinePlayer;
import net.shiruka.api.events.ChainDataEvent;
import net.shiruka.api.events.LoginResultEvent;
import net.shiruka.api.plugin.Plugin;
import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine players on the Minecraft.
 */
public interface Player extends HumanEntity, OfflinePlayer {

  @Override
  @NotNull
  default Optional<BanEntry> banPlayer(@Nullable final Text reason, @Nullable final Date expires,
                                       @Nullable final String source, final boolean kickIfOnline) {
    final var banEntry = Shiruka.getServer()
      .getBanList(BanList.Type.NAME)
      .addBan(this.getUniqueId().toString(), reason, expires, source);
    if (kickIfOnline && this.isOnline()) {
      this.getPlayer().ifPresent(player -> player.kick(LoginResultEvent.LoginResult.KICK_BANNED, reason));
    }
    return banEntry;
  }

  @Override
  default boolean isIpBanned() {
    return Shiruka.getServer()
      .getBanList(BanList.Type.IP)
      .isBanned(this.getAddress().getAddress().getHostAddress());
  }

  /**
   * permanently bans the profile and IP address currently used by the player.
   *
   * @param reason the reason to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerFull(@Nullable final Text reason) {
    return this.banPlayerFull(reason, null, null);
  }

  /**
   * permanently bans the profile and IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param source the source to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerFull(@Nullable final Text reason, @Nullable final String source) {
    return this.banPlayerFull(reason, null, source);
  }

  /**
   * permanently bans the profile and IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerFull(@Nullable final Text reason, @Nullable final Date expires) {
    return this.banPlayerFull(reason, expires, null);
  }

  /**
   * bans the profile and IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   * @param source the source to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerFull(@Nullable final Text reason, @Nullable final Date expires,
                                           @Nullable final String source) {
    this.banPlayer(reason, expires, source);
    return this.banPlayerIP(reason, expires, source, true);
  }

  /**
   * permanently bans the IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param kickPlayer the kick player to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerIP(@Nullable final Text reason, final boolean kickPlayer) {
    return this.banPlayerIP(reason, null, null, kickPlayer);
  }

  /**
   * permanently bans the IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param source the source to ban.
   * @param kickPlayer the kick player to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerIP(@Nullable final Text reason, @Nullable final String source,
                                         final boolean kickPlayer) {
    return this.banPlayerIP(reason, null, source, kickPlayer);
  }

  /**
   * bans the IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   * @param kickPlayer the kick player to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerIP(@Nullable final Text reason, @Nullable final Date expires,
                                         final boolean kickPlayer) {
    return this.banPlayerIP(reason, expires, null, kickPlayer);
  }

  /**
   * permanently bans the IP address currently used by the player.
   *
   * @param reason the reason to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerIP(@Nullable final Text reason) {
    return this.banPlayerIP(reason, null, null);
  }

  /**
   * permanently bans the IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param source the source to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerIP(@Nullable final Text reason, @Nullable final String source) {
    return this.banPlayerIP(reason, null, source);
  }

  /**
   * bans the IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerIP(@Nullable final Text reason, @Nullable final Date expires) {
    return this.banPlayerIP(reason, expires, null);
  }

  /**
   * bans the IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   * @param source the source to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerIP(@Nullable final Text reason, @Nullable final Date expires,
                                         @Nullable final String source) {
    return this.banPlayerIP(reason, expires, source, true);
  }

  /**
   * bans the IP address currently used by the player.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   * @param source the source to ban.
   * @param kickPlayer the kick player to ban.
   *
   * @return a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayerIP(@Nullable final Text reason, @Nullable final Date expires,
                                         @Nullable final String source, final boolean kickPlayer) {
    final var banEntry = Shiruka.getServer()
      .getBanList(BanList.Type.IP)
      .addBan(this.getAddress().getAddress().getHostAddress(), reason, expires, source);
    if (kickPlayer && this.isOnline()) {
      this.kick(LoginResultEvent.LoginResult.KICK_BANNED, reason);
    }
    return banEntry;
  }

  /**
   * checks to see if a player has been hidden from this player.
   *
   * @param player the player to check.
   *
   * @return {@code true} if the provided player is not being hidden from this player.
   */
  boolean canSee(@NotNull Player player);

  /**
   * obtains the address.
   *
   * @return address.
   */
  @NotNull
  InetSocketAddress getAddress();

  /**
   * obtains the chain data.
   *
   * @return chain data.
   */
  @NotNull
  ChainData getChainData();

  /**
   * obtains the ip ban entry if it's exist.
   *
   * @return a ip ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> getIpBanEntry() {
    return Shiruka.getServer()
      .getBanList(BanList.Type.IP)
      .getBanEntry(this.getAddress().getAddress().getHostAddress());
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

  /**
   * obtains the xbox unique id.
   *
   * @return xbox unique id or {@code null} if the player has not xbox auth.
   */
  @Nullable
  default String getXboxUniqueId() {
    return this.getProfile().getXboxUniqueId();
  }

  /**
   * hides a player from this player.
   *
   * @param plugin the plugin to hide.
   * @param player the player to hide.
   */
  void hidePlayer(@Nullable Plugin plugin, @NotNull Player player);

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
    return this.kick(LoginResultEvent.LoginResult.KICK_OTHER, reason, isAdmin);
  }

  /**
   * kicks the player.
   *
   * @param reason the reason to kick.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  default boolean kick(@Nullable final Text reason) {
    return this.kick(LoginResultEvent.LoginResult.KICK_OTHER, reason);
  }

  /**
   * kicks the player.
   *
   * @param reason the reason to kick.
   *
   * @return {@code true} if the player is kicked successfully.
   */
  default boolean kick(@NotNull final LoginResultEvent.LoginResult reason) {
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
  default boolean kick(@NotNull final LoginResultEvent.LoginResult reason, @Nullable final Text reasonString) {
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
  default boolean kick(@NotNull final LoginResultEvent.LoginResult reason, final boolean isAdmin) {
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
  boolean kick(@NotNull LoginResultEvent.LoginResult reason, @Nullable Text reasonString, boolean isAdmin);

  /**
   * Allows this player to see a player that was previously hidden.
   *
   * @param plugin the plugin to show.
   * @param player the player to show.
   */
  void showPlayer(@Nullable Plugin plugin, @NotNull Player player);
}
