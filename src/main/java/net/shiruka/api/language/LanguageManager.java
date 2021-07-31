package net.shiruka.api.language;

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
  @NotNull
  Optional<Locale> getLanguage(@NotNull String code);

  /**
   * obtains the server language.
   *
   * @return server language.
   */
  @NotNull
  Locale getServerLanguage();

  /**
   * translates the given {@code key} using the {@link #getServerLanguage()}.
   *
   * @param key the key to translate.
   * @param params the params to translate.
   *
   * @return translated string.
   *
   * @throws IllegalArgumentException if the given {@code key} does not contain in the locale cache.
   */
  @NotNull
  default String translate(@NotNull final String key, @NotNull final Object... params) {
    return this.translate(this.getServerLanguage(), key, params);
  }

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
