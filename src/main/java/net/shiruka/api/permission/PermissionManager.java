package net.shiruka.api.permission;

import java.util.Optional;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine permission managers.
 */
public interface PermissionManager {

  /**
   * adds a {@link Permission} to this plugin manager.
   *
   * @param perm the perm to add.
   *
   * @throws IllegalArgumentException when a permission with the same name already exists.
   */
  void addPermission(@NotNull Permission perm);

  /**
   * gets a set containing all subscribed {@link Permissible}s to the given default list, by op status.
   *
   * @param op the op to get.
   *
   * @return set containing all subscribed permissions.
   */
  @NotNull
  Set<Permissible> getDefaultPermSubscriptions(boolean op);

  /**
   * gets the default permissions for the given op status.
   *
   * @param op the op to get.
   *
   * @return the default permissions.
   */
  @NotNull
  Set<Permission> getDefaultPermissions(boolean op);

  /**
   * gets a {@link Permission} from its fully qualified name.
   *
   * @param name the name to get.
   *
   * @return permission.
   */
  @NotNull
  Optional<Permission> getPermission(@NotNull String name);

  /**
   * gets a set containing all subscribed {@link Permissible}s to the given permission, by name.
   *
   * @param permission the permission to get.
   *
   * @return set containing all subscribed permissions.
   */
  @NotNull
  Set<Permissible> getPermissionSubscriptions(@NotNull String permission);

  /**
   * gets a set of all registered permissions.
   *
   * @return set containing all current registered permissions.
   */
  @NotNull
  Set<Permission> getPermissions();

  /**
   * recalculates the defaults for the given {@link Permission}.
   *
   * @param perm the perm to recalculate.
   */
  void recalculatePermissionDefaults(@NotNull Permission perm);

  /**
   * removes a {@link Permission} registration from this plugin manager.
   *
   * @param perm the perm to remove.
   */
  void removePermission(@NotNull Permission perm);

  /**
   * removes a {@link Permission} registration from this plugin manager.
   *
   * @param name the name to remove.
   */
  void removePermission(@NotNull String name);

  /**
   * subscribes to the given default permissions by operator status.
   *
   * @param op the op to subscribe.
   * @param permissible the permissible to subscribe.
   */
  void subscribeToDefaultPerms(boolean op, @NotNull Permissible permissible);

  /**
   * subscribes the given permissible for information about the requested permission, by name.
   *
   * @param permission the permission to subscribe.
   * @param permissible the permissible to subscribe.
   */
  void subscribeToPermission(@NotNull String permission, @NotNull Permissible permissible);

  /**
   * unsubscribes from the given default permissions by operator status.
   *
   * @param op the op to unsubscribe.
   * @param permissible the permissible to unsubscribe.
   */
  void unsubscribeFromDefaultPerms(boolean op, @NotNull Permissible permissible);

  /**
   * unsubscribes the given permissible for information about the requested permission, by name.
   *
   * @param permission the permission to unsubscribe.
   * @param permissible the permissible to unsubscribe.
   */
  void unsubscribeFromPermission(@NotNull String permission, @NotNull Permissible permissible);
}
