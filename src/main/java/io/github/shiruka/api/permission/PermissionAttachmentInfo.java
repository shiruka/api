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

package io.github.shiruka.api.permission;

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
   * gets the attachment providing this permission
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
