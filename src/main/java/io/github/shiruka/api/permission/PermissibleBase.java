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

package io.github.shiruka.api.permission;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.plugin.Plugin;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * base {@link Permissible} for use in any Permissible object via proxy or extension.
 */
public final class PermissibleBase implements Permissible {

  /**
   * the attachments.
   */
  private final List<PermissionAttachment> attachments = new LinkedList<>();

  /**
   * the opable.
   */
  @Nullable
  private final ServerOperator opable;

  /**
   * the permissions.
   */
  private final Map<String, PermissionAttachmentInfo> permissions = new HashMap<>();

  /**
   * the parent.
   */
  private Permissible parent = this;

  /**
   * ctor.
   *
   * @param opable the opable.
   */
  public PermissibleBase(@Nullable final ServerOperator opable) {
    this.opable = opable;
    if (opable instanceof Permissible) {
      this.parent = (Permissible) opable;
    }
    this.recalculatePermissions();
  }

  /**
   * checks if the given {@code plugin} is enabled.
   *
   * @param plugin the plugin to check.
   *
   * @throws IllegalArgumentException if the plugin is not enabled.
   */
  private static void checkPlugin(@NotNull final Plugin plugin) {
    Preconditions.checkArgument(plugin.isEnabled(), "Plugin %s is disabled", plugin.getDescription().getFullName());
  }

  @NotNull
  @Override
  public synchronized PermissionAttachment addAttachment(@NotNull final Plugin plugin, @NotNull final String name,
                                                         final boolean value) {
    PermissibleBase.checkPlugin(plugin);
    final var result = this.addAttachment(plugin);
    result.setPermission(name, value);
    this.recalculatePermissions();
    return result;
  }

  @NotNull
  @Override
  public synchronized PermissionAttachment addAttachment(@NotNull final Plugin plugin) {
    PermissibleBase.checkPlugin(plugin);
    final PermissionAttachment result = new PermissionAttachment(plugin, this.parent);
    this.attachments.add(result);
    this.recalculatePermissions();
    return result;
  }

  @NotNull
  @Override
  public synchronized Optional<PermissionAttachment> addAttachment(@NotNull final Plugin plugin,
                                                                   @NotNull final String name, final boolean value,
                                                                   final long ticks) {
    PermissibleBase.checkPlugin(plugin);
    final var result = this.addAttachment(plugin, ticks);
    result.ifPresent(permissionAttachment ->
      permissionAttachment.setPermission(name, value));
    return result;
  }

  @NotNull
  @Override
  public synchronized Optional<PermissionAttachment> addAttachment(@NotNull final Plugin plugin, final long ticks) {
    PermissibleBase.checkPlugin(plugin);
    final var result = this.addAttachment(plugin);
    Shiruka.getScheduler().schedule(result::remove, ticks * 50L, TimeUnit.MILLISECONDS);
    return Optional.of(result);
  }

  @Override
  @NotNull
  public synchronized Set<PermissionAttachmentInfo> getEffectivePermissions() {
    return new HashSet<>(this.permissions.values());
  }

  @Override
  public boolean hasPermission(@NotNull final String name) {
    final var inName = name.toLowerCase(Locale.ROOT);
    final var info = this.permissions.get(inName);
    if (info != null) {
      return info.getValue();
    }
    return Shiruka.getPermissionManager().getPermission(inName)
      .map(permission -> permission.getDefault().getValue(this.isOp()))
      .orElseGet(() -> Permission.DEFAULT_PERMISSION.getValue(this.isOp()));
  }

  @Override
  public boolean hasPermission(@NotNull final Permission perm) {
    final var name = perm.getName().toLowerCase(Locale.ROOT);
    final var info = this.permissions.get(name);
    if (info != null) {
      return info.getValue();
    }
    return perm.getDefault().getValue(this.isOp());
  }

  @Override
  public boolean isPermissionSet(@NotNull final String name) {
    return this.permissions.containsKey(name.toLowerCase(Locale.ROOT));
  }

  @Override
  public boolean isPermissionSet(@NotNull final Permission perm) {
    return this.isPermissionSet(perm.getName());
  }

  @Override
  public synchronized void recalculatePermissions() {
    this.clearPermissions();
    final var manager = Shiruka.getPermissionManager();
    final var defaults = manager.getDefaultPermissions(this.isOp());
    manager.subscribeToDefaultPerms(this.isOp(), this.parent);
    defaults.forEach(perm -> {
      final var name = perm.getName().toLowerCase(Locale.ROOT);
      this.permissions.put(name, new PermissionAttachmentInfo(this.parent, name, null, true));
      manager.subscribeToPermission(name, this.parent);
      this.calculateChildPermissions(perm.getChildren(), false, null);
    });
    this.attachments.forEach(attachment ->
      this.calculateChildPermissions(attachment.getPermissions(), false, attachment));
  }

  @Override
  public synchronized void removeAttachment(@NotNull final PermissionAttachment attachment) {
    Preconditions.checkArgument(this.attachments.contains(attachment),
      "Given attachment is not part of Permissible object %s", this.parent);
    this.attachments.remove(attachment);
    final var ex = attachment.getRemovalCallback();
    if (ex != null) {
      ex.accept(attachment);
    }
    this.recalculatePermissions();
  }

  public synchronized void clearPermissions() {
    final var perms = this.permissions.keySet();
    final var manager = Shiruka.getPermissionManager();
    perms.forEach(name -> manager.unsubscribeFromPermission(name, this.parent));
    manager.unsubscribeFromDefaultPerms(false, this.parent);
    manager.unsubscribeFromDefaultPerms(true, this.parent);
    this.permissions.clear();
  }

  @Override
  public boolean isOp() {
    if (this.opable == null) {
      return false;
    }
    return this.opable.isOp();
  }

  @Override
  public void setOp(final boolean value) {
    if (this.opable == null) {
      throw new UnsupportedOperationException("Cannot change op value as no ServerOperator is set");
    }
    this.opable.setOp(value);
  }

  private void calculateChildPermissions(@NotNull final Map<String, Boolean> children, final boolean invert,
                                         @Nullable final PermissionAttachment attachment) {
    children.forEach((name, value1) -> {
      final var manager = Shiruka.getPermissionManager();
      final var perm = manager.getPermission(name);
      final var value = value1 ^ invert;
      final var lname = name.toLowerCase(Locale.ROOT);
      this.permissions.put(lname, new PermissionAttachmentInfo(this.parent, lname, attachment, value));
      manager.subscribeToPermission(name, this.parent);
      perm.ifPresent(permission ->
        this.calculateChildPermissions(permission.getChildren(), !value, attachment));
    });
  }
}
