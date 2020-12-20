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
    public void runCommand(@NotNull final String command) {
    }

    @Override
    public void startServer() {
    }

    @Override
    public void stopServer() {
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
