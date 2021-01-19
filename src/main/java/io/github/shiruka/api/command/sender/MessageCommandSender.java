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

package io.github.shiruka.api.command.sender;

import io.github.shiruka.api.permission.Permission;
import io.github.shiruka.api.permission.PermissionAttachment;
import io.github.shiruka.api.permission.PermissionAttachmentInfo;
import io.github.shiruka.api.plugin.Plugin;
import io.github.shiruka.api.text.Text;
import java.util.Optional;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command sender which only can send message.
 */
public interface MessageCommandSender extends CommandSender {

  @NotNull
  @Override
  default PermissionAttachment addAttachment(@NotNull final Plugin plugin, @NotNull final String name,
                                             final boolean value) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @NotNull
  @Override
  default PermissionAttachment addAttachment(@NotNull final Plugin plugin) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @NotNull
  @Override
  default Optional<PermissionAttachment> addAttachment(@NotNull final Plugin plugin, @NotNull final String name,
                                                       final boolean value, final long ticks) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @NotNull
  @Override
  default Optional<PermissionAttachment> addAttachment(@NotNull final Plugin plugin, final long ticks) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @NotNull
  @Override
  default Set<PermissionAttachmentInfo> getEffectivePermissions() {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  default boolean hasPermission(@NotNull final String name) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  default boolean hasPermission(@NotNull final Permission perm) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  default boolean isPermissionSet(@NotNull final String name) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  default boolean isPermissionSet(@NotNull final Permission perm) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  default void recalculatePermissions() {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  default void removeAttachment(@NotNull final PermissionAttachment attachment) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @NotNull
  @Override
  default Text getName() {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  default boolean isOp() {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  default void setOp(final boolean value) {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
