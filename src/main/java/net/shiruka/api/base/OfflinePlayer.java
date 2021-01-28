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

package net.shiruka.api.base;

import java.util.Date;
import java.util.Optional;
import net.shiruka.api.entity.Player;
import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine offline players.
 */
public interface OfflinePlayer extends Named, UniqueId {

  /**
   * permanently bans this player from the server.
   *
   * @param reason the reason to ban.
   *
   * @return a new a ban entry instance. instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayer(@Nullable final Text reason) {
    return this.banPlayer(reason, null, null);
  }

  /**
   * permanently bans this player from the server.
   *
   * @param reason the reason to ban.
   * @param source source of the ban.
   *
   * @return a new a ban entry instance. instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayer(@Nullable final Text reason, @Nullable final String source) {
    return this.banPlayer(reason, null, source);
  }

  /**
   * bans this player from the server
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   *
   * @return a new a ban entry instance. instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayer(@Nullable final Text reason, @Nullable final Date expires) {
    return this.banPlayer(reason, expires, null);
  }

  /**
   * bans this player from the server.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   * @param source source of the ban.
   *
   * @return a new a ban entry instance. instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayer(@Nullable final Text reason, @Nullable final Date expires,
                                       @Nullable final String source) {
    return this.banPlayer(reason, expires, source, true);
  }

  /**
   * bans this player from the server.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   * @param source source of the ban.
   * @param kickIfOnline the kick if online to ban.
   *
   * @return a new a ban entry instance. instance.
   */
  @NotNull
  Optional<BanEntry> banPlayer(@Nullable final Text reason, @Nullable final Date expires, @Nullable final String source,
                               final boolean kickIfOnline);

  /**
   * gets the location where the player will spawn at their bed, null if they have not slept in one or their current bed
   * spawn is invalid.
   *
   * @return last bed spawn location if bed exists, otherwise null.
   */
  @Nullable
  Location getBedSpawnLocation();

  /**
   * gets the first date and time that this player was witnessed on this server.
   *
   * @return date of first log-in for this player, or 0.
   */
  long getFirstPlayed();

  /**
   * gets the last date and time that this player logged into the server.
   *
   * @return last login time.
   */
  long getLastLogin();

  /**
   * gets the last date and time that this player was seen on the server.
   *
   * @return last seen time.
   */
  long getLastSeen();

  /**
   * gets a {@link Player} object that this represents, if there is one.
   *
   * @return online player instance.
   */
  @NotNull
  Optional<Player> getPlayer();

  /**
   * checks if this player has played on this server before.
   *
   * @return {@code true} if the player has played before, otherwise {@code false}.
   */
  boolean hasPlayedBefore();

  /**
   * checks if this player is banned or not
   *
   * @return {@code true} if banned, otherwise {@code false}.
   */
  boolean isBanned();

  /**
   * checks if this player is currently online.
   *
   * @return {@code true} if the player is online.
   */
  boolean isOnline();

  /**
   * checks if this player is whitelisted or not.
   *
   * @return {@code true} if whitelisted.
   */
  boolean isWhitelisted();

  /**
   * sets if this player is whitelisted or not.
   *
   * @param value the value to set.
   */
  void setWhitelisted(boolean value);
}
