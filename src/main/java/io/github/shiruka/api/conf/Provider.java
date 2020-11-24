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

import io.github.shiruka.api.conf.provider.HJsonProvider;
import io.github.shiruka.api.conf.provider.JsonProvider;
import io.github.shiruka.api.conf.provider.YamlProvider;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.file.FileConfiguration;

/**
 * config provider for {@link Config}.
 *
 * @param <F> file configuration class type.
 */
public interface Provider<F extends FileConfiguration> {

  /**
   * loads configuration from a input stream.
   *
   * @param inputStream the inputStream to load.
   *
   * @return the configuration.
   *
   * @throws Exception if there is an I/O error occurred.
   */
  @NotNull
  default F load(@NotNull final InputStream inputStream) throws Exception {
    final var file = File.createTempFile("tempProviderFile", null);
    file.deleteOnExit();
    Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    return this.load(file);
  }

  /**
   * saves the configuration to the file.
   *
   * @param configuration the configuration.
   * @param file the file.
   *
   * @throws IOException if there is an I/O error occurred.
   */
  default void save(@NotNull final F configuration, @NotNull final File file) throws IOException {
    configuration.save(file);
  }

  /**
   * loads configuration from a file.
   *
   * @param file the file.
   *
   * @return the configuration.
   *
   * @throws Exception if there is on I/O error occurred.
   */
  @NotNull
  F load(@NotNull File file) throws Exception;
}
