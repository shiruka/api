package net.shiruka.api;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Shiru ka servers.
 */
public interface Server {

  /**
   * obtains the provider.
   *
   * @return provider.
   */
  @NotNull
  Provider getProvider();
}
