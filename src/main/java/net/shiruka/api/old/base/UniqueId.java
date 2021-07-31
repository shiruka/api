package net.shiruka.api.old.base;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine classes which have unique id in it.
 */
public interface UniqueId {

  /**
   * obtains the unique id.
   *
   * @return unique id.
   */
  @NotNull
  UUID getUniqueId();
}
