package net.shiruka.api.old;

import com.google.common.base.Preconditions;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that contains Shiru ka's implementations.
 */
final class Implementation {

  /**
   * the lock used for writing the impl field.
   */
  private static final Object LOCK = new Object();

  /**
   * the server implementation.
   */
  @Nullable
  private static Server server;

  /**
   * ctor.
   */
  private Implementation() {
  }

  /**
   * obtains the current {@link Server} singleton.
   *
   * @return the server instance being ran.
   */
  @NotNull
  static Server getServer() {
    return Objects.requireNonNull(Implementation.server, "Cannot get the Server before it initialized!");
  }

  /**
   * sets the {@link Server} singleton to the given server instance.
   *
   * @param server the server to set.
   */
  static void setServer(@NotNull final Server server) {
    Preconditions.checkArgument(Implementation.server == null,
      "Cannot set the server after it initialized!");
    synchronized (Implementation.LOCK) {
      if (Implementation.server == null) {
        Implementation.server = server;
      }
    }
  }
}
