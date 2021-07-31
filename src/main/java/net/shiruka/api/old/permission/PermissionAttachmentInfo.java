package net.shiruka.api.old.permission;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * holds information on a permission and which {@link PermissionAttachment} provides it.
 */
public final class PermissionAttachmentInfo {

  /**
   * the attachment.
   */
  @Nullable
  private final PermissionAttachment attachment;

  /**
   * the permissible.
   */
  @NotNull
  private final Permissible permissible;

  /**
   * the permission.
   */
  @NotNull
  private final String permission;

  /**
   * the value.
   */
  private final boolean value;

  /**
   * ctor.
   *
   * @param permissible the permissible.
   * @param permission the permission.
   * @param attachment the attachment.
   * @param value the value.
   */
  public PermissionAttachmentInfo(@NotNull final Permissible permissible, @NotNull final String permission,
                                  @Nullable final PermissionAttachment attachment, final boolean value) {
    this.permissible = permissible;
    this.permission = permission;
    this.attachment = attachment;
    this.value = value;
  }

  /**
   * gets the attachment providing this permission.
   *
   * @return attachment.
   */
  @Nullable
  public PermissionAttachment getAttachment() {
    return this.attachment;
  }

  /**
   * gets the permissible this is attached to.
   *
   * @return permissible this permission is for.
   */
  @NotNull
  public Permissible getPermissible() {
    return this.permissible;
  }

  /**
   * gets the permission being set.
   *
   * @return name of the permission.
   */
  @NotNull
  public String getPermission() {
    return this.permission;
  }

  /**
   * gets the value of this permission.
   *
   * @return value of the permission.
   */
  public boolean getValue() {
    return this.value;
  }
}
