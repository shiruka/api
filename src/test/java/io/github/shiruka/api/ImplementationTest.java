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

import io.github.shiruka.conf.Provider;
import io.github.shiruka.fragment.FragmentManager;
import io.github.shiruka.log.Logger;
import java.io.File;
import java.io.OutputStream;
import org.hamcrest.MatcherAssert;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Throws;

final class ImplementationTest {

  @Test
  void getServer() {
    MatcherAssert.assertThat(
      "Server set somewhere!",
      Implementation::getServer,
      new Throws<>(NullPointerException.class));
  }

  @Test
  void setServer() {
    Implementation.setServer(new Server() {
    });
    MatcherAssert.assertThat(
      "Server couldn't set!",
      () -> {
        Implementation.setServer(new Server() {
        });
        return null;
      },
      new Throws<>(UnsupportedOperationException.class));
  }

  @Test
  void getFragmentManager() {
    MatcherAssert.assertThat(
      "Fragment Manager set somewhere!",
      Implementation::getFragmentManager,
      new Throws<>(NullPointerException.class));
  }

  @Test
  void setFragmentManager() {
    Implementation.setFragmentManager(new EmptyFragmentManager());
    MatcherAssert.assertThat(
      "Fragment Manager couldn't set!",
      () -> {
        Implementation.setFragmentManager(new EmptyFragmentManager());
        return null;
      },
      new Throws<>(UnsupportedOperationException.class));
  }

  private static final class EmptyFragmentManager extends FragmentManager {

    public EmptyFragmentManager() {
      super(new File("fragments"), new Logger() {
        @NotNull
        @Override
        public String getName() {
          return null;
        }

        @Override
        public void log(@NotNull final String s) {
        }

        @Override
        public void success(@NotNull final String s) {
        }

        @Override
        public void warn(@NotNull final String s) {
        }

        @Override
        public void error(@NotNull final String s) {
        }

        @Override
        public void debug(@NotNull final String s) {
        }

        @NotNull
        @Override
        public OutputStream getOutputStream() {
          return null;
        }
      });
    }

    @NotNull
    @Override
    public String getFragmentConfigFileName() {
      return null;
    }

    @NotNull
    @Override
    protected Provider<?> getConfigProvider() {
      return null;
    }
  }
}