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

package net.shiruka.api.config;

import java.util.Arrays;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * the {@link ConfigPath} loader to run {@link ConfigPath#setConfig(Config)} method.
 */
public final class PathLoader {

  /**
   * ctor.
   */
  private PathLoader() {
  }

  /**
   * loads the config paths.
   *
   * @param config the config class to load.
   */
  public static void load(@NotNull final Config config) {
    Arrays.stream(config.getClass().getDeclaredFields())
      .filter(field -> ConfigPath.class.isAssignableFrom(field.getType()))
      .map(field -> {
        final var accessible = field.canAccess(config);
        try {
          field.setAccessible(true);
          return Optional.of(field.get(config));
        } catch (final IllegalArgumentException | IllegalAccessException ignored) {
        } finally {
          field.setAccessible(accessible);
        }
        return Optional.empty();
      })
      .filter(Optional::isPresent)
      .map(Optional::get)
      .map(o -> (ConfigPath<?>) o)
      .forEach(configPath -> configPath.setConfig(config));
  }
}
