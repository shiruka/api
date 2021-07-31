package net.shiruka.api;

import com.google.common.base.Preconditions;
import java.util.Objects;
import lombok.Synchronized;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents a back-end implementations for Shiru ka.
 */
final class Implementation {

  /**
   * the server instance.
   */
  @Nullable
  private static Server server;

  /**
   * obtains the server.
   *
   * @return server.
   */
  @NotNull
  static Server getServer() {
    return Objects.requireNonNull(Implementation.server,
      "The server not set yet!");
  }

  /**
   * sets the server.
   *
   * @param server the server to set.
   *
   * @throws IllegalStateException if the server is already set.
   */
  @Synchronized("server")
  static void setServer(@NotNull final Server server) {
    Preconditions.checkState(Implementation.server == null,
      "The server has been set already!", Implementation.server);
    Implementation.server = server;
  }
}
