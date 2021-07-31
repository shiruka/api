package net.shiruka.api.old.base;

import java.util.Date;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine ban entries.
 */
public interface BanEntry {

  /**
   * gets the date this ban entry was created.
   *
   * @return the creation date.
   */
  @NotNull
  Date getCreated();

  /**
   * sets the date this ban entry was created.
   *
   * @param created the new created date.
   *
   * @see #save() saving changes.
   */
  void setCreated(@NotNull Date created);

  /**
   * gets the date this ban expires on, or null for no defined end date.
   *
   * @return the expiration date.
   */
  @NotNull
  Optional<Date> getExpiration();

  /**
   * sets the date this ban expires on.
   *
   * @param expiration the new expiration date, null for permanently.
   *
   * @see #save() saving changes.
   */
  void setExpiration(@Nullable Date expiration);

  /**
   * gets the reason for this ban.
   *
   * @return the ban reason.
   */
  @NotNull
  String getReason();

  /**
   * sets the reason for this ban. Reasons must not be null.
   *
   * @param reason the new reason, null values assume the implementation default
   *
   * @see #save() saving changes
   */
  void setReason(@NotNull String reason);

  /**
   * gets the source of this ban.
   *
   * @return the source of the ban.
   */
  @NotNull
  String getSource();

  /**
   * sets the source of this ban.
   *
   * @param source the new source.
   *
   * @see #save() saving changes.
   */
  void setSource(@NotNull String source);

  /**
   * gets the target involved.
   *
   * @return the target name or IP address.
   */
  @NotNull
  String getTarget();

  /**
   * saves the ban entry, overwriting any previous data in the ban list.
   */
  void save();
}
