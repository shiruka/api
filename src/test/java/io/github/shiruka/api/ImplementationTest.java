/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
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
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.llorllale.cactoos.matchers.Throws;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
final class ImplementationTest {

  private static final Server SERVER = new Server() {
    @NotNull
    @Override
    public BanList getBanList(final BanList.@NotNull Type type) {
      return null;
    }

    @NotNull
    @Override
    public <I> I getInterface(@NotNull final Class<I> cls) {
      return null;
    }

    @NotNull
    @Override
    public Logger getLogger() {
      return null;
    }

    @Override
    public int getMaxPlayerCount() {
      return 0;
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
    public boolean isRunning() {
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
  };

  @Test
  @Order(1)
  void getServer() {
    MatcherAssert.assertThat(
      "Server set somewhere!",
      Implementation::getServer,
      new Throws<>(NullPointerException.class));
  }

  @Test
  @Order(2)
  void setServer() {
    Implementation.setServer(ImplementationTest.SERVER);
    MatcherAssert.assertThat(
      "Server couldn't set!",
      () -> {
        Implementation.setServer(ImplementationTest.SERVER);
        return "null";
      },
      new Throws<>(IllegalArgumentException.class));
  }
}
