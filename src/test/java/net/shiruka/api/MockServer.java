package net.shiruka.api;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import net.shiruka.api.base.BanList;
import net.shiruka.api.entity.Player;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class MockServer implements Server {

  @NotNull
  @Override
  public BanList getBanList(@NotNull final BanList.Type type) {
    throw new UnsupportedOperationException();
  }

  @NotNull
  @Override
  public <I> I getInterface(@NotNull final Class<I> cls) {
    throw new UnsupportedOperationException();
  }

  @NotNull
  @Override
  public Logger getLogger() {
    return null;
  }

  @Override
  public int getMaxPlayers() {
    return 0;
  }

  @Override
  public void setMaxPlayers(final int maxPlayers) {
  }

  @NotNull
  @Override
  public Collection<? extends Player> getOnlinePlayers() {
    return Collections.emptyList();
  }

  @Override
  public boolean isInShutdownState() {
    return false;
  }

  @Override
  public boolean isInWhitelist(@NotNull final UUID uniqueId) {
    return false;
  }

  @Override
  public boolean isPrimaryThread() {
    return false;
  }

  @Override
  public boolean isRunning() {
    return false;
  }

  @Override
  public boolean isStopping() {
    return false;
  }

  @Override
  public boolean isWhitelistOn() {
    return false;
  }

  @Override
  public <I> void registerInterface(@NotNull final Class<I> cls, @NotNull final I implementation) {
  }

  @Override
  public void startServer() {
  }

  @Override
  public void stopServer() {
  }

  @Override
  public <I> void unregisterInterface(@NotNull final Class<I> cls) {
  }
}
