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

package io.github.shiruka.api.resourcepack;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine resource pack managers
 */
public interface ResourcePackManager extends Closeable {

  /**
   * closes the registration of resource packs.
   */
  void closeRegistration();

  /**
   * get resource pack loader instance from the given path.
   *
   * @param path the path to get.
   *
   * @return resource pack loader.
   *
   * @throws IOException if an I/O error has occurred.
   */
  @NotNull
  Optional<ResourcePackLoader> getLoader(@NotNull Path path) throws IOException;

  /**
   * gets resource pack manifest instance from the given loader.
   *
   * @param loader the loader to get.
   *
   * @return resource pack manifest.
   */
  @NotNull
  ResourcePackManifest getManifest(@NotNull ResourcePackLoader loader);

  /**
   * loads resource pack from the given path.
   *
   * @param path the path to load.
   */
  void loadResourcePack(@NotNull Path path);

  /**
   * loads resource packs from the given directory.
   *
   * @param directory the directory to load.
   */
  void loadResourcePacks(@NotNull Path directory);

  /**
   * registers the given resource pack loader class.
   *
   * @param cls the class to register.
   * @param predicate the predicate to check if it's allowed for the given path.
   * @param function the function to create instance of the loader.
   *
   * @throws IllegalArgumentException if the given cls is already registered.
   */
  void registerLoader(@NotNull Class<? extends ResourcePackLoader> cls, @NotNull Predicate<Path> predicate,
                      @NotNull Function<Path, ResourcePackLoader> function);
}
