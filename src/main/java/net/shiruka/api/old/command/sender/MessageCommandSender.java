package net.shiruka.api.old.command.sender;

import java.util.Optional;
import java.util.Set;
import net.shiruka.api.old.permission.Permission;
import net.shiruka.api.old.permission.PermissionAttachment;
import net.shiruka.api.old.permission.PermissionAttachmentInfo;
import net.shiruka.api.old.plugin.Plugin;
import net.shiruka.api.old.text.Text;
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
