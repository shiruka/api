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

package io.github.shiruka.api.conf.config;

import io.github.shiruka.api.conf.Config;
import io.github.shiruka.api.conf.provider.YamlProvider;
import io.github.shiruka.common.function.StickySupplier;
import java.io.File;
import org.jetbrains.annotations.NotNull;

/**
 * a class that creates YAML files.
 */
public class YamlConfig extends ConfigEnvelope {

  /**
   * ctor.
   *
   * @param config the config.
   */
  private YamlConfig(@NotNull final Config config) {
    super(new StickySupplier<>(config));
  }

  /**
   * ctor.
   *
   * @param fil the file to create.
   */
  public YamlConfig(@NotNull final File fil) {
    this(new ConfigBasic<>(fil, new YamlProvider()));
  }
}
