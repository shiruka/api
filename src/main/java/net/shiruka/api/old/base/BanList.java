package net.shiruka.api.old.base;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import net.shiruka.api.old.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine ban list.
 */
public interface BanList {

  /**
   * adds a ban to the this list.
   *
   * @param target the target to ban.
   * @param reason the reason to ban.
   * @param expires the date to ban.
   * @param source the source to ban.
   *
   * @return the entry for the newly created ban, or the entry for the (updated) previous ban.
   */
  @NotNull
  Optional<BanEntry> addBan(@NotNull String target, @Nullable Text reason, @Nullable Date expires,
                            @Nullable String source);

  /**
   * gets a set containing every {@link BanEntry} in this list.
   *
   * @return an immutable set containing every entry tracked by this list.
   */
  @NotNull
  Set<BanEntry> getBanEntries();

  /**
   * gets a {@link BanEntry} by target.
   *
   * @param target the target to get.
   *
   * @return the corresponding entry, or null if none found.
   */
  @NotNull
  Optional<BanEntry> getBanEntry(@NotNull String target);

  /**
   * gets if a {@link BanEntry} exists for the target, indicating an active ban status.
   *
   * @param target the target to check.
   *
   * @return {@code true} if a {@link BanEntry} exists for the name, indicating an active ban status,
   *   {@code false} otherwise.
   */
  boolean isBanned(@NotNull String target);

  /**
   * removes the specified target from this list, therefore indicating a "not banned" status.
   *
   * @param target the target to remove.
   */
  void pardon(@NotNull String target);

  /**
   * an enum class that represents a ban-type that a {@link BanList} may track.
   */
  enum Type {
    /**
     * banned player names.
     */
    NAME,
    /**
     * banned player ip addresses.
     */
    IP
  }
}
