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

import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * represents the different colors that can be sent in chat.
 */
public enum ChatColor {
  /**
   * Black, &#167;0.
   */
  BLACK('0'),
  /**
   * Dark Blue, &#167;1.
   */
  DARK_BLUE('1'),
  /**
   * Dark Green, &#167;2.
   */
  DARK_GREEN('2'),
  /**
   * Dark Aqua, &#167;3.
   */
  DARK_AQUA('3'),
  /**
   * Dark Red, &#167;4.
   */
  DARK_RED('4'),
  /**
   * Dark Purple, &#167;5.
   */
  DARK_PURPLE('5'),
  /**
   * Gold, &#167;6.
   */
  GOLD('6'),
  /**
   * Gray, &#167;7.
   */
  GRAY('7'),
  /**
   * Gray, &#167;8.
   */
  DARK_GRAY('8'),
  /**
   * Blue, &#167;9.
   */
  BLUE('9'),
  /**
   * Green, &#167;a.
   */
  GREEN('a'),
  /**
   * Aqua, &#167;b.
   */
  AQUA('b'),
  /**
   * Red, &#167;c.
   */
  RED('c'),
  /**
   * Light Purple, &#167;d.
   */
  LIGHT_PURPLE('d'),
  /**
   * Yellow, &#167;e.
   */
  YELLOW('e'),
  /**
   * White, &#167;f.
   */
  WHITE('f'),
  /**
   * Obfuscated, &#167;k.
   */
  OBFUSCATED('k'),
  /**
   * Bold, &#167;l.
   */
  BOLD('l'),
  /**
   * Strikethrough, &#167;m.
   */
  STRIKETHROUGH('m'),
  /**
   * Underline, &#167;n.
   */
  UNDERLINE('n'),
  /**
   * Italic, &#167;o.
   */
  ITALIC('o'),
  /**
   * Reset, &#167;r.
   */
  RESET('r');

  /**
   * all color codes.
   */
  public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";

  /**
   * the escape character.
   */
  public static final char ESCAPE = '\u00A7';

  /**
   * the clean pattern.
   */
  private static final Pattern CLEAN_PATTERN = Pattern.compile("(?i)" + ChatColor.ESCAPE + "[0-9A-FK-ORX]");

  /**
   * an empty string that helps in {@link #clean(String, boolean)} method to clean strings.
   */
  private static final String EMPTY_STRING = "";

  /**
   * {@code this}'s values as cache.
   */
  private static final ChatColor[] VALUES = ChatColor.values();

  /**
   * the color character.
   */
  private final char colorChar;

  /**
   * the to string.
   */
  @NotNull
  private final String toString;

  /**
   * ctor.
   *
   * @param colorChar the color character.
   */
  ChatColor(final char colorChar) {
    this.colorChar = colorChar;
    this.toString = new String(new char[]{ChatColor.ESCAPE, colorChar});
  }

  /**
   * cleans the given message of all format codes.
   *
   * @param input the input to clean.
   *
   * @return a copy of the input string, without any formatting.
   */
  @NotNull
  public static String clean(@NotNull final String input) {
    return ChatColor.clean(input, false);
  }

  /**
   * cleans the given message of all format codes.
   *
   * @param input the input to clean.
   * @param recursive the recursive to clean.
   *
   * @return a copy of the input string, without any formatting.
   */
  @NotNull
  public static String clean(@NotNull final String input, final boolean recursive) {
    final var result = ChatColor.CLEAN_PATTERN.matcher(input).replaceAll(ChatColor.EMPTY_STRING);
    if (recursive && ChatColor.CLEAN_PATTERN.matcher(result).find()) {
      return ChatColor.clean(result, true);
    }
    return result;
  }

  /**
   * gets a chat color from a given character.
   *
   * @param colorChar The color's character.
   *
   * @return The color, or null if not found.
   */
  @NotNull
  public static ChatColor of(final char colorChar) {
    return Stream.of(ChatColor.VALUES)
      .filter(chatColor -> chatColor.colorChar == colorChar)
      .findFirst()
      .orElseThrow(() ->
        new IllegalArgumentException("no color with character " + colorChar));
  }

  /**
   * obtains the color's character.
   *
   * @return the character.
   */
  public char getColorChar() {
    return this.colorChar;
  }

  @Override
  public String toString() {
    return this.toString;
  }
}
