/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api;

import io.github.shiruka.api.base.BanList;
import io.github.shiruka.api.entity.Player;
import java.util.Collection;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class MockServer implements Server {

  private static final Logger LOGGER = LogManager.getLogger("MockServer");

  @NotNull
  @Override
  public BanList getBanList(final BanList.@NotNull Type type) {
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
    return MockServer.LOGGER;
  }

  @Override
  public int getMaxPlayerCount() {
    return 0;
  }

  @NotNull
  @Override
  public Collection<? extends Player> getOnlinePlayers() {
    return Collections.emptyList();
  }

  @Override
  public int getPlayerCount() {
    return 0;
  }

  @NotNull
  @Override
  public String getServerDescription() {
    return "null";
  }

  @Override
  public boolean isInShutdownState() {
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
  public <I> void registerInterface(@NotNull final Class<I> cls, @NotNull final I implementation) {
  }

  @Override
  public void startServer(final long startTime) {
  }

  @Override
  public void stopServer() {
  }

  @Override
  public <I> void unregisterInterface(@NotNull final Class<I> cls) {
  }
}