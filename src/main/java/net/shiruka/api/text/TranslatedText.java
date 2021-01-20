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

package net.shiruka.api.text;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import net.shiruka.api.Shiruka;
import net.shiruka.api.entity.Player;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.MessageSupplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents translated texts.
 */
public final class TranslatedText implements Text, MessageSupplier {

  /**
   * the non param cache.
   */
  private final Map<Locale, String> nonParamCache = new ConcurrentHashMap<>();

  /**
   * the params.
   */
  @NotNull
  private final Object[] params;

  /**
   * the text.
   */
  @NotNull
  private final String text;

  /**
   * the log message for {@link MessageSupplier}.
   */
  private final Message message = new Message() {
    @Override
    public String getFormattedMessage() {
      return TranslatedText.this.asString();
    }

    @Override
    public String getFormat() {
      return this.getFormattedMessage();
    }

    @Override
    public Object[] getParameters() {
      return null;
    }

    @Override
    public Throwable getThrowable() {
      return null;
    }
  };

  /**
   * ctor.
   *
   * @param text the text.
   * @param params the params.
   */
  public TranslatedText(@NotNull final String text, @NotNull final Object... params) {
    this.text = text;
    this.params = params.clone();
  }

  /**
   * gets the translated text from the cache.
   *
   * @param key the key to get.
   * @param params the params to get.
   *
   * @return a cached translated text instance from the language manager.
   */
  @NotNull
  public static TranslatedText get(@NotNull final String key, @NotNull final Object... params) {
    Shiruka.getLanguageManager().check(key);
    return new TranslatedText(key, params);
  }

  @Override
  public String asString() {
    return this.translate(Shiruka.getLanguageManager().getServerLanguage());
  }

  @Override
  public Message get() {
    return this.message;
  }

  @Override
  public String toString() {
    return this.asString();
  }

  /**
   * gives the translated string.
   *
   * @param player the player to get.
   *
   * @return translated string.
   */
  @NotNull
  public Optional<String> translate(@NotNull final Player player) {
    return Shiruka.getLanguageManager().getLanguage(player.getChainData().languageCode())
      .map(this::translate);
  }

  /**
   * gives the translated string.
   *
   * @param locale the locale to get.
   *
   * @return translated string.
   */
  @NotNull
  public String translate(@NotNull final Locale locale) {
    if (this.params.length == 0) {
      return this.nonParamCache.computeIfAbsent(locale, l ->
        Shiruka.getLanguageManager().translate(l, this.text));
    }
    return Shiruka.getLanguageManager().translate(locale, this.text, this.params);
  }
}
