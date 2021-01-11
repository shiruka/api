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
import java.io.IOException;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.file.FileConfiguration;

/**
 * a simple {@link Config} implementation.
 *
 * @param <F> the file type.
 */
public final class ConfigBasic<F extends FileConfiguration> implements Config {

  /**
   * the file.
   */
  @NotNull
  private final File file;

  /**
   * the provider.
   */
  @NotNull
  private final Provider<F> provider;

  /**
   * the file configuration.
   */
  @NotNull
  private F configuration;

  /**
   * ctor.
   *
   * @param file the file to create.
   * @param provider the provider to help create configurations.
   */
  public ConfigBasic(@NotNull final File file, @NotNull final Provider<F> provider) {
    this.file = file;
    this.provider = provider;
    if (!file.exists()) {
      Optional.ofNullable(file.getParentFile())
        .filter(parent -> !parent.exists())
        .ifPresent(File::mkdirs);
      try {
        file.createNewFile();
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
    try {
      this.configuration = provider.load(file);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  @NotNull
  @Override
  public FileConfiguration getConfiguration() {
    return this.configuration;
  }

  @NotNull
  @Override
  public File getFile() {
    return this.file;
  }

  @Override
  public void reload() {
    try {
      this.configuration = this.provider.load(this.file);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void save() {
    try {
      this.provider.save(this.configuration, this.file);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
