/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
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

package io.github.shiruka.api.base;

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
  Optional<String> getReason();

  /**
   * sets the reason for this ban. Reasons must not be null.
   *
   * @param reason the new reason, null values assume the implementation default
   *
   * @see #save() saving changes
   */
  void setReason(@Nullable String reason);

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
