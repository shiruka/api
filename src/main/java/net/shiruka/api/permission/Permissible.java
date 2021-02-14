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

package net.shiruka.api.permission;

import java.util.Optional;
import java.util.Set;
import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object that may be assigned permissions.
 */
public interface Permissible extends ServerOperator {

  /**
   * adds a new {@link PermissionAttachment} with a single permission by name and value.
   *
   * @param plugin the plugin to add.
   * @param name the name to add.
   * @param value the value to add.
   *
   * @return permission attachment that was just created.
   */
  @NotNull
  PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value);

  /**
   * adds a new empty {@link PermissionAttachment} to this object.
   *
   * @param plugin the plugin to add.
   *
   * @return permission attachment that was just created.
   */
  @NotNull
  PermissionAttachment addAttachment(@NotNull Plugin plugin);

  /**
   * temporarily adds a new {@link PermissionAttachment} with a single permission by name and value.
   *
   * @param plugin the plugin to add.
   * @param name the name to add.
   * @param value the value to add.
   * @param ticks the ticks to add.
   *
   * @return permission attachment that was just created.
   */
  @NotNull
  Optional<PermissionAttachment> addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, long ticks);

  /**
   * temporarily adds a new empty {@link PermissionAttachment} to this object.
   *
   * @param plugin the plugin to add.
   * @param ticks the ticks to add.
   *
   * @return permission attachment that was just created.
   */
  @NotNull
  Optional<PermissionAttachment> addAttachment(@NotNull Plugin plugin, long ticks);

  /**
   * gets a set containing all of the permissions currently in effect by this object.
   *
   * @return set of currently effective permissions.
   */
  @NotNull
  Set<PermissionAttachmentInfo> getEffectivePermissions();

  /**
   * gets the value of the specified permission, if set.
   *
   * @param name the name to get.
   *
   * @return value of the permission.
   */
  boolean hasPermission(@NotNull String name);

  /**
   * gets the value of the specified permission, if set.
   *
   * @param perm the perm to get.
   *
   * @return value of the permission.
   */
  boolean hasPermission(@NotNull Permission perm);

  /**
   * checks if this object contains an override for the specified permission, by fully qualified name.
   *
   * @param name the name to check.
   *
   * @return {@code true} if the permission is set, otherwise {@code false}.
   */
  boolean isPermissionSet(@NotNull String name);

  /**
   * checks if this object contains an override for the specified {@link Permission}.
   *
   * @param perm the perm to check.
   *
   * @return {@code true} if the permission is set, otherwise {@code false}.
   */
  boolean isPermissionSet(@NotNull Permission perm);

  /**
   * recalculates the permissions for this object, if the attachments have changed values.
   */
  void recalculatePermissions();

  /**
   * removes the given {@link PermissionAttachment} from this object.
   *
   * @param attachment the attachment to remove.
   *
   * @throws IllegalArgumentException when the specified attachment isn't part of this object.
   */
  void removeAttachment(@NotNull PermissionAttachment attachment);
}
