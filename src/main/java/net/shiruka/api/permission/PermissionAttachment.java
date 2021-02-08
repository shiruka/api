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

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.objects.Object2BooleanLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * holds information about a permission attachment on a {@link Permissible} object.
 */
public final class PermissionAttachment {

  /**
   * the permissible.
   */
  @NotNull
  private final Permissible permissible;

  /**
   * the permissions.
   */
  private final Map<String, Boolean> permissions = new Object2BooleanLinkedOpenHashMap<>();

  /**
   * the plugin.
   */
  @NotNull
  private final Plugin plugin;

  /**
   * the removed.
   */
  @Nullable
  private PermissionRemovedExecutor removed;

  /**
   * ctor.
   *
   * @param plugin the plugin.
   * @param permissible the permissible.
   */
  public PermissionAttachment(@NotNull final Plugin plugin, @NotNull final Permissible permissible) {
    Preconditions.checkArgument(plugin.isEnabled(), "Plugin %s is disabled", plugin.getDescription().getFullName());
    this.permissible = permissible;
    this.plugin = plugin;
  }

  /**
   * gets the permissible that this is attached to.
   *
   * @return permissible containing this attachment.
   */
  @NotNull
  public Permissible getPermissible() {
    return this.permissible;
  }

  /**
   * gets a copy of all set permissions and values contained within this attachment.
   *
   * @return copy of all permissions and values expressed by this attachment.
   */
  @NotNull
  public Map<String, Boolean> getPermissions() {
    return Collections.unmodifiableMap(this.permissions);
  }

  /**
   * gets the plugin responsible for this attachment.
   *
   * @return plugin responsible for this permission attachment.
   */
  @NotNull
  public Plugin getPlugin() {
    return this.plugin;
  }

  /**
   * gets the class that was previously set to be called when this attachment was removed from a {@link Permissible}.
   *
   * @return object to be called when this is removed.
   */
  @Nullable
  public PermissionRemovedExecutor getRemovalCallback() {
    return this.removed;
  }

  /**
   * sets an object to be called for when this attachment is removed from a {@link Permissible}.
   *
   * @param ex the executor to set.
   */
  public void setRemovalCallback(@Nullable final PermissionRemovedExecutor ex) {
    this.removed = ex;
  }

  /**
   * removes this attachment from its registered {@link Permissible}.
   *
   * @return {@code true} if the permissible was removed successfully, {@code false} if it did not exist.
   */
  public boolean remove() {
    try {
      this.permissible.removeAttachment(this);
      return true;
    } catch (final IllegalArgumentException ex) {
      return false;
    }
  }

  /**
   * sets a permission to the given value.
   *
   * @param perm the perm to set.
   * @param value the value to set.
   */
  public void setPermission(@NotNull final Permission perm, final boolean value) {
    this.setPermission(perm.getName(), value);
  }

  /**
   * sets a permission to the given value, by its fully qualified name.
   *
   * @param name the name to set.
   * @param value the value to set.
   */
  public void setPermission(@NotNull final String name, final boolean value) {
    this.permissions.put(name.toLowerCase(Locale.ROOT), value);
    this.permissible.recalculatePermissions();
  }

  /**
   * removes the specified permission from this attachment.
   *
   * @param perm the perm to remove.
   */
  public void unsetPermission(@NotNull final Permission perm) {
    this.unsetPermission(perm.getName());
  }

  /**
   * removes the specified permission from this attachment.
   *
   * @param name the name to remove.
   */
  public void unsetPermission(@NotNull final String name) {
    this.permissions.remove(name.toLowerCase(Locale.ROOT));
    this.permissible.recalculatePermissions();
  }
}
