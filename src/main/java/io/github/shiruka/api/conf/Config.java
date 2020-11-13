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

import java.io.File;
import java.util.Optional;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.configuration.file.FileConfiguration;

/**
 * the interface of Config classes.
 */
public interface Config {

  /**
   * runs your consumer after that saves the config file.
   *
   * @param consumer the consumer to run.
   */
  default void saveAfterDo(@NotNull final Consumer<FileConfiguration> consumer) {
    consumer.accept(this.getConfiguration());
    this.save();
  }

  /**
   * gets the value from the config.
   *
   * @param path the path to get.
   * @param def the default value if it's not found.
   *
   * @return the value.
   */
  @NotNull
  default Optional<Object> get(@NotNull final String path, @Nullable final Object def) {
    return Optional.ofNullable(this.getConfiguration().get(path, def));
  }

  /**
   * gets the value from the config.
   *
   * @param path the path to get.
   *
   * @return the value.
   */
  @NotNull
  default Optional<Object> get(@NotNull final String path) {
    return this.get(path, null);
  }

  /**
   * sets the value to the config.
   *
   * @param path the path to set.
   * @param obj the object to set.
   */
  default void set(@NotNull final String path, @Nullable final Object obj) {
    this.getConfiguration().set(path, obj);
  }

  /**
   * adds the default value to the config.
   *
   * @param path the path to add.
   * @param obj the object to add.
   */
  default void addDefault(@NotNull final String path, @Nullable final Object obj) {
    this.getConfiguration().addDefault(path, obj);
  }

  /**
   * obtains the file.
   *
   * @return the file.
   */
  @NotNull
  File getFile();

  /**
   * gets the instance of the config file.
   *
   * @return the config.
   */
  @NotNull
  FileConfiguration getConfiguration();

  /**
   * reloads the config.
   */
  void reload();

  /**
   * saves the config.
   */
  void save();
}
