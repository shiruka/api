package net.shiruka.api.old.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import net.shiruka.api.old.Shiruka;
import net.shiruka.api.old.entity.Player;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.MessageSupplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents translated texts.
 */
public final class TranslatedText implements GameMessage, MessageSupplier {

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
   * the siblings.
   */
  private final List<Text> siblings = new ObjectArrayList<>();

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
  public void addSiblings(final @NotNull Text... siblings) {
    Collections.addAll(this.siblings, siblings);
  }

  @NotNull
  @Override
  public List<Text> getSiblings() {
    return Collections.unmodifiableList(this.siblings);
  }

  @NotNull
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
    return Shiruka.getLanguageManager().getLanguage(player.getChainData().getLanguageCode())
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
      return this.nonParamCache.computeIfAbsent(locale, this::translate0);
    }
    return this.translate0(locale);
  }

  /**
   * gives the translated string.
   *
   * @param locale the locale to get.
   *
   * @return translated string.
   */
  @NotNull
  private String translate0(@NotNull final Locale locale) {
    final var builder = new StringBuilder(Shiruka.getLanguageManager().translate(locale, this.text, this.params));
    this.siblings.forEach(sibling ->
      builder.append('\n').append(sibling));
    return builder.toString();
  }
}
