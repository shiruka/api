package net.shiruka.api.base;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine objects which have a {@link Namespaced}.
 */
public interface Keyed {

  /**
   * obtains the key.
   *
   * @return key.
   */
  @NotNull
  Namespaced getKey();
}
