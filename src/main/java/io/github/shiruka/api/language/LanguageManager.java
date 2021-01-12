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

package io.github.shiruka.api.language;

import java.util.Locale;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine language managers.
 */
public interface LanguageManager {

  /**
   * checks the given {@code key}.
   *
   * @param key the key to check.
   *
   * @throws IllegalArgumentException if the given {@code key} does not contain in the language cache.
   */
  void check(@NotNull String key);

  /**
   * obtains the language instance from the given {@code code}.
   *
   * @param code the code to get.
   *
   * @return language instance.
   */
  Optional<Locale> getLanguage(@NotNull String code);

  /**
   * translates the given {@code key} in terms of the given {@code locale}.
   *
   * @param locale the locale to translate.
   * @param key the key to translate.
   * @param params the params to translate.
   *
   * @return translated string.
   *
   * @throws IllegalArgumentException if the given {@code key} does not contain in the locale cache.
   */
  @NotNull
  String translate(@NotNull Locale locale, @NotNull String key, @NotNull Object... params);
}