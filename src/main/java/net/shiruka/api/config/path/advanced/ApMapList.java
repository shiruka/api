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

package net.shiruka.api.config.path.advanced;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import net.shiruka.api.config.AdvancedPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that helps to create {@link AdvancedPath} which has raw value as map list..
 *
 * @param <T> the final value's type.
 */
public abstract class ApMapList<T> extends ApEnvelope<List<Map<?, ?>>, List<T>> {

  /**
   * ctor.
   *
   * @param path the path.
   * @param def the default value.
   * @param toTList the to object list function.
   * @param toMap the to map function.
   */
  public ApMapList(@NotNull final String path, @Nullable final List<T> def,
                   @NotNull final Function<List<Map<?, ?>>, Optional<List<T>>> toTList,
                   @NotNull final Function<List<T>, Optional<List<Map<?, ?>>>> toMap) {
    super(new ApBasic<>(toTList, toMap, def, path,
      config -> Optional.of(config.getConfiguration().getMapList(path))));
  }
}
