package net.shiruka.api.old.permission;

import java.util.function.Consumer;

/**
 * an interface to determine permission removed executors.
 */
@FunctionalInterface
public interface PermissionRemovedExecutor extends Consumer<PermissionAttachment> {

}
