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
import java.io.File;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.file.FileConfiguration;

/**
 * an envelope class for {@link Config}.
 */
public abstract class ConfigEnvelope implements Config {

  /**
   * the original {@link Config}.
   */
  @NotNull
  private final Config origin;

  /**
   * ctor.
   *
   * @param origin the config.
   */
  protected ConfigEnvelope(@NotNull final Config origin) {
    this.origin = origin;
  }

  @NotNull
  @Override
  public final File getFile() {
    return this.origin.getFile();
  }

  @NotNull
  @Override
  public final FileConfiguration getConfiguration() {
    return this.origin.getConfiguration();
  }

  @Override
  public final void reload() {
    this.origin.reload();
  }

  @Override
  public final void save() {
    this.origin.save();
  }
}
