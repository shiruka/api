package net.shiruka.api;

import java.util.Collection;
import java.util.UUID;
import net.shiruka.api.base.BanList;
import net.shiruka.api.entity.Player;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine servers.
 */
public interface Server {

  /**
   * obtains the ban list instance.
   *
   * @param type the type to get.
   *
   * @return a ban list instance.
   */
  @NotNull
  BanList getBanList(@NotNull BanList.Type type);

  /**
   * obtains the given class's implementation.
   *
   * @param cls the class to get.
   * @param <I> type of the interface.
   *
   * @return given class's implementation.
   *
   * @throws IllegalArgumentException if the given class's implementation not found.
   */
  @NotNull <I> I getInterface(@NotNull Class<I> cls);

  /**
   * obtains the server logger instance.
   *
   * @return server logger.
   */
  @NotNull
  Logger getLogger();

  /**
   * obtains maximum player count of the server.
   *
   * @return maximum player count to join the server.
   */
  int getMaxPlayers();

  /**
   * sets the max players.
   *
   * @param maxPlayers the max players to set.
   */
  void setMaxPlayers(int maxPlayers);

  /**
   * obtains the online players.
   *
   * @return online players.
   */
  @NotNull
  Collection<? extends Player> getOnlinePlayers();

  /**
   * obtains server's shutdown statement.
   *
   * @return {@code true} if the server is in the shutdown state.
   */
  boolean isInShutdownState();

  /**
   * checks if the id is in the whitelist.
   *
   * @param uniqueId the unique id to check.
   *
   * @return {@code true} if the id is in the whitelist.
   */
  boolean isInWhitelist(@NotNull UUID uniqueId);

  /**
   * checks if the player is in the whitelist.
   *
   * @param player the player to check.
   *
   * @return {@code true} if the player is in the whitelist.
   */
  default boolean isInWhitelist(@NotNull final Player player) {
    return this.isInWhitelist(player.getUniqueId());
  }

  /**
   * checks the current thread against the expected primary thread for the server.
   *
   * @return {@code true} if the current thread matches the expected primary thread, otherwise {@code false}.
   */
  boolean isPrimaryThread();

  /**
   * obtains server's running statement.
   *
   * @return {@code true} if the server is running.
   */
  boolean isRunning();

  /**
   * checks if the server is in the shutdown statement.
   *
   * @return {@code true} if the server is in the shutdown statement.
   */
  boolean isStopping();

  /**
   * checks if the whitelist on.
   *
   * @return {@code true} if the whitelist is on.
   */
  boolean isWhitelistOn();

  /**
   * registers the given class and it's implementation.
   *
   * @param cls the class to register.
   * @param implementation the implementation to register.
   * @param <I> type of the interface.
   */
  <I> void registerInterface(@NotNull Class<I> cls, @NotNull I implementation);

  /**
   * initiates the server.
   */
  void startServer();

  /**
   * closes the server.
   */
  void stopServer();

  /**
   * unregisters the given class and it's implementation.
   *
   * @param cls the class to unregister.
   * @param <I> type of the interface.
   */
  <I> void unregisterInterface(@NotNull Class<I> cls);
}
