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

package io.github.shiruka.api.base;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
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
  Optional<BanEntry> addBan(@NotNull String target, @Nullable String reason, @Nullable Date expires,
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
