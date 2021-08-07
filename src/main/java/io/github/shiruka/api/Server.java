package io.github.shiruka.api;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Shiru ka servers.
 */
public interface Server {

  /**
   * obtains the logger.
   *
   * @return logger.
   */
  @NotNull
  Logger getLogger();

  /**
   * obtains the provider.
   *
   * @return provider.
   */
  @NotNull
  Provider getProvider();
}
