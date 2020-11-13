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

package io.github.shiruka.api.conf.path.advanced;

import io.github.portlek.replaceable.Replaceable;
import io.github.portlek.replaceable.rp.RpEnvelope;
import io.github.portlek.replaceable.rp.RpString;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a simple implementation for {@link ApListString} as {@link RpString}.
 */
public final class ApReplaceableString extends ApString<RpString> {

  /**
   * ctor.
   *
   * @param path the path.
   * @param def the default value.
   */
  public ApReplaceableString(@NotNull final String path, @Nullable final RpString def) {
    super(path, def,
      s -> Optional.ofNullable(def)
        .map(rpString -> rpString.value(s))
        .orElse(Replaceable.from(s)),
      RpEnvelope::getValue);
  }
}
