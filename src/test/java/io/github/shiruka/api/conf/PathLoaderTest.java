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

package io.github.shiruka.api.conf;

import io.github.shiruka.common.conf.path.simple.CpBoolean;
import io.github.shiruka.common.conf.path.simple.CpFloat;
import io.github.shiruka.common.conf.path.simple.CpString;
import java.io.File;
import org.hamcrest.MatcherAssert;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.IsTrue;
import org.simpleyaml.configuration.file.FileConfiguration;

final class PathLoaderTest {

  @Test
  void load() {
    final var config = new TestConfig();
    PathLoader.load(config);
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.textTest.getConfig().isPresent(),
      new IsTrue());
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.floatTest.getConfig().isPresent(),
      new IsTrue());
    MatcherAssert.assertThat(
      "Couldn't set the config!",
      config.boolTest.getConfig().isPresent(),
      new IsTrue());
  }

  private static final class TestConfig implements Config {

    public final CpString textTest = Paths.stringPath("test", "text");

    final CpFloat floatTest = Paths.floatPath("test", 1.0f);

    private final CpBoolean boolTest = Paths.booleanPath("test", true);

    @NotNull
    @Override
    public File getFile() {
      return null;
    }

    @NotNull
    @Override
    public FileConfiguration getConfiguration() {
      return null;
    }

    @Override
    public void reload() {
    }

    @Override
    public void save() {
    }
  }
}