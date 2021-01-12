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

package io.github.shiruka.api.text;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.entity.Player;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents translated texts.
 */
public final class TranslatedText implements Text {

  /**
   * the cache.
   */
  private static final Map<String, TranslatedText> CACHE = new ConcurrentHashMap<>();

  /**
   * the non param cache.
   */
  private final Map<Locale, String> nonParamCache = new ConcurrentHashMap<>();

  /**
   * the text.
   */
  @NotNull
  private final String text;

  /**
   * ctor.
   *
   * @param text the text.
   */
  private TranslatedText(@NotNull final String text) {
    this.text = text;
  }

  /**
   * gets the translated text from the cache.
   *
   * @param key the key to get.
   *
   * @return a cached translated text instance from the language manager.
   */
  @NotNull
  public static TranslatedText get(@NotNull final String key) {
    Shiruka.getLanguageManager().check(key);
    return TranslatedText.CACHE.computeIfAbsent(key, TranslatedText::new);
  }

  @Override
  public String asString() {
    return this.text;
  }

  @Override
  public String toString() {
    return this.asString();
  }

  /**
   * gives the translated string.
   *
   * @param params the params to get.
   *
   * @return translated string.
   */
  @NotNull
  public String translate(@NotNull final Object... params) {
    return this.translate(Shiruka.getLanguageManager().getServerLanguage(), params);
  }

  /**
   * gives the translated string.
   *
   * @param player the player to get.
   * @param params the params to get.
   *
   * @return translated string.
   */
  @NotNull
  public Optional<String> translate(@NotNull final Player player, @NotNull final Object... params) {
    return Shiruka.getLanguageManager().getLanguage(player.getChainData().languageCode())
      .map(language -> this.translate(language, params));
  }

  /**
   * gives the translated string.
   *
   * @param locale the locale to get.
   * @param params the params to get.
   *
   * @return translated string.
   */
  @NotNull
  public String translate(@NotNull final Locale locale, @NotNull final Object... params) {
    if (params.length == 0) {
      return this.nonParamCache.computeIfAbsent(locale, l ->
        Shiruka.getLanguageManager().translate(l, this.text));
    }
    return Shiruka.getLanguageManager().translate(locale, this.text, params);
  }
}
