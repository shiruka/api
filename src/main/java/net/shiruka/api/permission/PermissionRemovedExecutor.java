package net.shiruka.api.permission;

import java.util.function.Consumer;

/**
 * an interface to determine permission removed executors.
 */
@FunctionalInterface
public interface PermissionRemovedExecutor extends Consumer<PermissionAttachment> {

}
