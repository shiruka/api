package net.shiruka.api.old.base;

import java.util.Date;
import java.util.Optional;
import net.shiruka.api.old.Shiruka;
import net.shiruka.api.old.entity.Player;
import net.shiruka.api.old.text.Text;
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
   * @return a new a ban entry instance.
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
   * @return a new a ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> banPlayer(@Nullable final Text reason, @Nullable final String source) {
    return this.banPlayer(reason, null, source);
  }

  /**
   * bans this player from the server.
   *
   * @param reason the reason to ban.
   * @param expires the expires to ban.
   *
   * @return a new a ban entry instance.
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
   * @return a new a ban entry instance.
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
   * @return a new a ban entry instance.
   */
  @NotNull
  Optional<BanEntry> banPlayer(@Nullable Text reason, @Nullable Date expires, @Nullable String source,
                               boolean kickIfOnline);

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
   * obtains the name ban entry if it's exist.
   *
   * @return a name ban entry instance.
   */
  @NotNull
  default Optional<BanEntry> getNameBanEntry() {
    return Shiruka.getServer()
      .getBanList(BanList.Type.NAME)
      .getBanEntry(this.getName().asString());
  }

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
   * checks if this player is fully banned or not.
   *
   * @return {@code true} if fully banned, otherwise {@code false}.
   */
  default boolean isBanned() {
    return this.isIpBanned() || this.isNameBanned();
  }

  /**
   * checks if this player is ip banned or not.
   *
   * @return {@code true} if ip banned, otherwise {@code false}.
   */
  boolean isIpBanned();

  /**
   * checks if this player is name banned or not.
   *
   * @return {@code true} if name banned, otherwise {@code false}.
   */
  default boolean isNameBanned() {
    return Shiruka.getServer()
      .getBanList(BanList.Type.NAME)
      .isBanned(this.getName().asString());
  }

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
