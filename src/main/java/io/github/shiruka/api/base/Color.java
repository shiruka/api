package io.github.shiruka.api.base;

import com.google.common.collect.Maps;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface that represents colors.
 */
public interface Color {
  /**
   * the color char.
   */
  String COLOR_CHAR = "§";

  /**
   * the color char unique.
   */
  char COLOR_CHAR_UNIQUE = 'x';

  /**
   * the color name -> hex code.
   */
  Map<String, String> COLOR_TO_HEX = Maps.newConcurrentMap();

  /**
   * the de colorize pattern.
   */
  Pattern DE_COLORIZE_PATTERN = Pattern.compile(
    Color.COLOR_CHAR +
    Color.COLOR_CHAR_UNIQUE +
    "(" +
    Color.COLOR_CHAR +
    "[\\da-fA-F]){6}"
  );

  /**
   * the hex color char.
   */
  char HEX_COLOR_CHAR = '#';

  /**
   * the hex color end.
   */
  char HEX_COLOR_END = '}';

  /**
   * the hex color start.
   */
  char HEX_COLOR_START = '{';

  /**
   * the color pattern.
   */
  Pattern HEX_COLORIZE_PATTERN = Pattern.compile(
    "\\" +
    Color.HEX_COLOR_START +
    Color.HEX_COLOR_CHAR +
    "([\\da-fA-F]){6}" +
    Color.HEX_COLOR_END
  );

  /**
   * the hex code -> color name.
   */
  Map<String, String> HEX_TO_COLOR = Maps.newConcurrentMap();

  /**
   * the legacy color char.
   */
  char LEGACY_COLOR_CHAR = '&';

  /**
   * the legacy color pattern.
   */
  Pattern LEGACY_COLORIZE_PATTERN = Pattern.compile(
    Color.LEGACY_COLOR_CHAR + "[\\da-fk-orA-FK-OR]"
  );

  /**
   * the legacy de color pattern.
   */
  Pattern LEGACY_DE_COLORIZE_PATTERN = Pattern.compile(
    Color.COLOR_CHAR + "[\\da-fk-orA-FK-OR]"
  );

  /**
   * the color pattern.
   */
  Pattern NAMED_COLORIZE_PATTERN = Pattern.compile(
    "\\" +
    Color.HEX_COLOR_START +
    Color.HEX_COLOR_CHAR +
    "([a-zA-Z-]){3,}" +
    Color.HEX_COLOR_END
  );

  /**
   * adds the color name with the hex code.
   *
   * @param colorName the color name to add.
   * @param hexCode the hex code to add.
   */
  static void addColor(
    @NotNull final String colorName,
    @NotNull final String hexCode
  ) {
    Color.COLOR_TO_HEX.put(colorName, hexCode);
    Color.HEX_TO_COLOR.put(hexCode, colorName);
  }

  /**
   * colorizes the given text.
   *
   * @param text the text to colorize.
   *
   * @return colorized text.
   */
  @NotNull
  static String colorize(@NotNull final String text) {
    return Color.colorize(text, true);
  }

  /**
   * colorizes the given text.
   *
   * @param text the text to colorize.
   * @param named the named to colorize.
   *
   * @return colorized text.
   */
  @NotNull
  static String colorize(@NotNull final String text, final boolean named) {
    return Color.colorize(text, named, true);
  }

  /**
   * colorizes the given text.
   *
   * @param text the text to colorize.
   * @param named the named to colorize.
   * @param hex the hex to colorize.
   *
   * @return colorized text.
   */
  @NotNull
  static String colorize(
    @NotNull final String text,
    final boolean named,
    final boolean hex
  ) {
    var replaced = text;
    if (named) {
      replaced = Color.colorizeNamed(replaced);
    }
    if (hex) {
      replaced = Color.colorizeHex(replaced);
    }
    return Color.colorizeLegacy(replaced);
  }

  /**
   * colorizes the text.
   *
   * @param text the text to colorize.
   *
   * @return colorized text.
   */
  @NotNull
  static String colorizeHex(@NotNull final String text) {
    var replaced = text;
    final var matcher = Color.HEX_COLORIZE_PATTERN.matcher(replaced);
    while (matcher.find()) {
      final var group = matcher.group();
      final var builder = new StringBuilder(
        Color.COLOR_CHAR + Color.COLOR_CHAR_UNIQUE
      );
      final var charArray = group
        .substring(2, group.length() - 1)
        .toCharArray();
      for (final var ch : charArray) {
        builder.append(Color.COLOR_CHAR).append(ch);
        if (group.substring(2, group.length() - 1).length() == 3) {
          builder.append(Color.COLOR_CHAR).append(ch);
        }
      }
      replaced = replaced.replace(group, builder.toString());
    }
    return replaced;
  }

  /**
   * colorizes the text.
   *
   * @param text the text to colorize.
   *
   * @return colorized text.
   */
  @NotNull
  static String colorizeLegacy(@NotNull final String text) {
    var replaced = text;
    final var matcher = Color.LEGACY_COLORIZE_PATTERN.matcher(replaced);
    while (matcher.find()) {
      final var group = matcher.group();
      replaced =
        replaced.replace(
          group,
          Color.COLOR_CHAR +
          group.replace(String.valueOf(Color.LEGACY_COLOR_CHAR), "")
        );
    }
    return replaced;
  }

  /**
   * colorizes the text.
   *
   * @param text the text to colorize.
   *
   * @return colorized text.
   */
  @NotNull
  static String colorizeNamed(@NotNull final String text) {
    final var replaced = new AtomicReference<>(text);
    final var matcher = Color.NAMED_COLORIZE_PATTERN.matcher(replaced.get());
    while (matcher.find()) {
      final var group = matcher.group();
      Color
        .getHexByColor(
          group.substring(2, group.length() - 1).toLowerCase(Locale.ROOT)
        )
        .ifPresent(s -> replaced.set(replaced.get().replace(group, s)));
    }
    return replaced.get();
  }

  /**
   * de colorizes the given text.
   *
   * @param text the text to de colorize.
   *
   * @return de colorized text.
   */
  @NotNull
  static String deColorize(@NotNull final String text) {
    return Color.deColorize(text, true);
  }

  /**
   * de colorizes the given text.
   *
   * @param text the text to de colorize.
   * @param named the named to de colorize.
   *
   * @return de colorized text.
   */
  @NotNull
  static String deColorize(@NotNull final String text, final boolean named) {
    return Color.deColorize(text, named, true);
  }

  /**
   * de colorizes the given text.
   *
   * @param text the text to de colorize.
   * @param named the named to de colorize.
   * @param hex the hex to de colorize.
   *
   * @return de colorized text.
   */
  @NotNull
  static String deColorize(
    @NotNull final String text,
    final boolean named,
    final boolean hex
  ) {
    var replaced = text;
    if (named) {
      replaced = Color.deColorizeNamed(replaced);
    }
    if (hex) {
      replaced = Color.deColorizeHex(replaced);
    }
    return Color.deColorizeLegacy(replaced);
  }

  /**
   * de colorizes the text.
   *
   * @param text the text to de colorize.
   *
   * @return de colorized text.
   */
  @NotNull
  static String deColorizeHex(@NotNull final String text) {
    var replaced = text;
    final var matcher = Color.DE_COLORIZE_PATTERN.matcher(replaced);
    while (matcher.find()) {
      final var group = matcher.group();
      final var hexColor =
        Color.HEX_COLOR_START +
        Color.HEX_COLOR_CHAR +
        group.substring(2).replace(Color.COLOR_CHAR, "") +
        Color.HEX_COLOR_END;
      replaced = replaced.replace(group, hexColor);
    }
    return replaced;
  }

  /**
   * de colorizes the text.
   *
   * @param text the text to de colorize.
   *
   * @return de colorized text.
   */
  @NotNull
  static String deColorizeLegacy(@NotNull final String text) {
    var replaced = text;
    final var matcher = Color.LEGACY_DE_COLORIZE_PATTERN.matcher(replaced);
    while (matcher.find()) {
      final var group = matcher.group();
      final var color =
        Color.LEGACY_COLOR_CHAR + group.replace(Color.COLOR_CHAR, "");
      replaced = replaced.replace(group, color);
    }
    return replaced;
  }

  /**
   * de colorizes the text.
   *
   * @param text the text to de colorize.
   *
   * @return de colorized text.
   */
  @NotNull
  static String deColorizeNamed(@NotNull final String text) {
    return Color.stripColorNamed(
      text,
      hexCode -> Color.getColorByHex(hexCode).orElse(null)
    );
  }

  /**
   * gets the color name by hex code.
   *
   * @param hexCode the hex code to get.
   *
   * @return color name by hex code
   */
  @NotNull
  static Optional<String> getColorByHex(@NotNull final String hexCode) {
    return Optional.ofNullable(Color.HEX_TO_COLOR.get(hexCode));
  }

  /**
   * gets the hex code of the color name.
   *
   * @param colorName the color name to get.
   *
   * @return hex code of the color.
   */
  @NotNull
  static Optional<String> getHexByColor(@NotNull final String colorName) {
    return Optional.ofNullable(Color.COLOR_TO_HEX.get(colorName));
  }

  /**
   * removes the color name.
   *
   * @param colorName the color name to add.
   */
  static void removeColor(@NotNull final String colorName) {
    final var hex = Color.COLOR_TO_HEX.remove(colorName);
    if (hex != null) {
      Color.HEX_TO_COLOR.remove(hex);
    }
  }

  /**
   * strips the given text.
   *
   * @param text the text to de strip.
   *
   * @return striped text.
   */
  @NotNull
  static String stripColor(@NotNull final String text) {
    return Color.stripColor(text, true);
  }

  /**
   * strips the given text.
   *
   * @param text the text to de strip.
   * @param named the named to de strip.
   *
   * @return striped text.
   */
  @NotNull
  static String stripColor(@NotNull final String text, final boolean named) {
    return Color.stripColor(text, named, true);
  }

  /**
   * strips the given text.
   *
   * @param text the text to de strip.
   * @param named the named to de strip.
   * @param hex the hex to de strip.
   *
   * @return striped text.
   */
  @NotNull
  static String stripColor(
    @NotNull final String text,
    final boolean named,
    final boolean hex
  ) {
    var replaced = text;
    if (named) {
      replaced = Color.stripColorNamed(replaced);
    }
    if (hex) {
      replaced = Color.stripColorHex(replaced);
    }
    return Color.stripColorLegacy(replaced);
  }

  /**
   * strips the text.
   *
   * @param text the text to colorize.
   *
   * @return striped text.
   */
  @NotNull
  static String stripColorHex(@NotNull final String text) {
    return Color.DE_COLORIZE_PATTERN.matcher(text).replaceAll("");
  }

  /**
   * strips the text.
   *
   * @param text the text to strip.
   *
   * @return striped text.
   */
  @NotNull
  static String stripColorLegacy(@NotNull final String text) {
    return Color.LEGACY_DE_COLORIZE_PATTERN.matcher(text).replaceAll("");
  }

  /**
   * strips the text.
   *
   * @param text the text to strip.
   *
   * @return striped text.
   */
  @NotNull
  static String stripColorNamed(@NotNull final String text) {
    return Color.stripColorNamed(text, hexCode -> "");
  }

  /**
   * strips the text.
   *
   * @param text the text to strip.
   * @param replace the replace to strip.
   *
   * @return striped text.
   */
  @NotNull
  static String stripColorNamed(
    @NotNull final String text,
    @NotNull final Function<@NotNull String, @Nullable String> replace
  ) {
    final var replaced = new AtomicReference<>(text);
    final var matcher = Color.DE_COLORIZE_PATTERN.matcher(replaced.get());
    while (matcher.find()) {
      final var group = matcher.group();
      final var hexCode = group
        .replace(String.valueOf(Color.COLOR_CHAR_UNIQUE), "")
        .replace(Color.COLOR_CHAR, "")
        .toLowerCase(Locale.ROOT);
      if (Color.HEX_TO_COLOR.containsKey(hexCode)) {
        final var replacement = replace.apply(hexCode);
        if (replacement != null) {
          replaced.set(replaced.get().replace(group, replacement));
        }
      }
    }
    return replaced.get();
  }
}
