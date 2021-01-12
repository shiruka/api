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

package io.github.shiruka.api.config.config;

import io.github.shiruka.api.config.Config;
import io.github.shiruka.api.config.Provider;
import java.io.File;
import org.jetbrains.annotations.NotNull;

/**
 * a class that creates JSON files.
 */
public final class HJsonConfig extends ConfigEnvelope {

  /**
   * ctor.
   *
   * @param origin the config.
   */
  private HJsonConfig(@NotNull final Config origin) {
    super(origin);
  }

  /**
   * ctor.
   *
   * @param file the file to create.
   */
  public HJsonConfig(@NotNull final File file) {
    this(new ConfigBasic<>(file, Provider.H_JSON_PROVIDER));
  }

  /**
   * ctor.
   *
   * @param path the path to create dir.
   * @param file the file to create.
   */
  public HJsonConfig(@NotNull final String path, @NotNull final String file) {
    this(new File(path, file));
  }
}
