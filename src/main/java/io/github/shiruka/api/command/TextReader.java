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

package io.github.shiruka.api.command;

import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents immutable text reader.
 */
public final class TextReader {

  /**
   * the double quote syntax.
   */
  private static final char SYNTAX_DOUBLE_QUOTE = '"';

  /**
   * the escape syntax.
   */
  private static final char SYNTAX_ESCAPE = '\\';

  /**
   * the single quote syntax.
   */
  private static final char SYNTAX_SINGLE_QUOTE = '\'';

  /**
   * the text.
   */
  private final String text;

  /**
   * the cursor.
   */
  private int cursor;

  /**
   * ctor.
   *
   * @param other the other.
   */
  public TextReader(@NotNull final TextReader other) {
    this.text = other.text;
    this.cursor = other.cursor;
  }

  /**
   * ctor.
   *
   * @param text the text.
   */
  public TextReader(@NotNull final String text) {
    this.text = text;
  }

  /**
   * checks if it's allowed in unquoted text.
   *
   * @param ch the ch to check.
   *
   * @return {@code true} if it's allowed.
   */
  public static boolean isAllowedInUnquotedText(final char ch) {
    return TextReader.isAllowedInteger(ch) ||
      ch >= 'A' && ch <= 'Z' ||
      ch >= 'a' && ch <= 'z' ||
      ch == '_' || ch == '.';
  }

  /**
   * checks if it's allowed integer.
   *
   * @param ch the ch to check.
   *
   * @return {@code true} if it's allowed.}
   */
  private static boolean isAllowedInteger(final char ch) {
    return ch >= '0' && ch <= '9' || ch == '+' || ch == '-';
  }

  /**
   * checks if it's allowed number.
   *
   * @param ch the ch to check.
   *
   * @return {@code true} if it's allowed.}
   */
  private static boolean isAllowedNumber(final char ch) {
    return TextReader.isAllowedInteger(ch) || ch == '.' || ch == 'e' || ch == 'E';
  }

  /**
   * checks if it starts with quote.
   *
   * @param ch the ch to check.
   *
   * @return {@code true} if it starts with quote.
   */
  private static boolean isQuotedTextStart(final char ch) {
    return ch == TextReader.SYNTAX_DOUBLE_QUOTE || ch == TextReader.SYNTAX_SINGLE_QUOTE;
  }

  /**
   * checks if it's readable.
   *
   * @return {@code true} if it's readable.
   */
  public boolean canRead() {
    return this.canRead(1);
  }

  /**
   * checks if it's readable.
   *
   * @param length the length to check.
   *
   * @return {@code true} if it's readable.
   */
  public boolean canRead(final int length) {
    return this.cursor + length <= this.getTotalLength();
  }

  /**
   * obtains the cursor.
   *
   * @return cursor.
   */
  public int getCursor() {
    return this.cursor;
  }

  /**
   * sets the cursor.
   *
   * @param cursor the cursor to set.
   */
  public void setCursor(final int cursor) {
    this.cursor = cursor;
  }

  /**
   * obtains the remaining text.
   *
   * @return remaining text.
   */
  @NotNull
  public String getRemaining() {
    return this.text.substring(this.cursor);
  }

  /**
   * obtains the remaining length of the text.
   *
   * @return remaining length.
   */
  public int getRemainingLength() {
    return this.text.length() - this.cursor;
  }

  /**
   * obtains the text value.
   *
   * @return text value.
   */
  @NotNull
  public String getText() {
    return this.text;
  }

  /**
   * obtains the total length.
   *
   * @return total length.
   */
  public int getTotalLength() {
    return this.text.length();
  }

  /**
   * obtains the character of the {@link #cursor} position.
   *
   * @return peeked character.
   */
  public char peek() {
    return this.text.charAt(this.cursor);
  }

  /**
   * obtains the character of the {@link #cursor} position.
   *
   * @param offset the offset to peek.
   *
   * @return peeked character.
   */
  public char peek(final int offset) {
    return this.text.charAt(this.cursor + offset);
  }

  /**
   * polls the character.
   *
   * @return polled character.
   */
  public char read() {
    return this.text.charAt(this.cursor++);
  }

  /**
   * reads boolean.
   *
   * @return boolean.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  public boolean readBoolean() throws CommandSyntaxException {
    final var start = this.cursor;
    final String value = this.readUnquotedText();
    if (value.isEmpty()) {
      throw CommandException.READER_EXPECTED_BOOL.createWithContext(this);
    }
    if (value.equals("true")) {
      return true;
    } else if (value.equals("false")) {
      return false;
    } else {
      this.cursor = start;
      throw CommandException.READER_INVALID_BOOL.createWithContext(this, value);
    }
  }

  /**
   * reads byte.
   *
   * @return byte.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  public byte readByte() throws CommandSyntaxException {
    final var start = this.cursor;
    while (this.canRead() && TextReader.isAllowedNumber(this.peek())) {
      this.skip();
    }
    final var number = this.text.substring(start, this.cursor);
    if (number.isEmpty()) {
      throw CommandException.READER_EXPECTED_BYTE.createWithContext(this);
    }
    try {
      return Byte.parseByte(number);
    } catch (final NumberFormatException ex) {
      this.cursor = start;
      throw CommandException.READER_INVALID_BYTE.createWithContext(this, number);
    }
  }

  /**
   * reads double.
   *
   * @return double.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  public double readDouble() throws CommandSyntaxException {
    final var start = this.cursor;
    while (this.canRead() && TextReader.isAllowedNumber(this.peek())) {
      this.skip();
    }
    final var number = this.text.substring(start, this.cursor);
    if (number.isEmpty()) {
      throw CommandException.READER_EXPECTED_DOUBLE.createWithContext(this);
    }
    try {
      return Double.parseDouble(number);
    } catch (final NumberFormatException ex) {
      this.cursor = start;
      throw CommandException.READER_INVALID_DOUBLE.createWithContext(this, number);
    }
  }

  /**
   * reads float.
   *
   * @return float.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  public float readFloat() throws CommandSyntaxException {
    final var start = this.cursor;
    while (this.canRead() && TextReader.isAllowedNumber(this.peek())) {
      this.skip();
    }
    final var number = this.text.substring(start, this.cursor);
    if (number.isEmpty()) {
      throw CommandException.READER_EXPECTED_FLOAT.createWithContext(this);
    }
    try {
      return Float.parseFloat(number);
    } catch (final NumberFormatException ex) {
      this.cursor = start;
      throw CommandException.READER_INVALID_FLOAT.createWithContext(this, number);
    }
  }

  /**
   * reads int.
   *
   * @return int.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  public int readInt() throws CommandSyntaxException {
    final var start = this.cursor;
    while (this.canRead() && TextReader.isAllowedInteger(this.peek())) {
      this.skip();
    }
    final var number = this.text.substring(start, this.cursor);
    if (number.isEmpty()) {
      throw CommandException.READER_EXPECTED_INT.createWithContext(this);
    }
    try {
      return Integer.parseInt(number);
    } catch (final NumberFormatException ex) {
      this.cursor = start;
      throw CommandException.READER_INVALID_INT.createWithContext(this, number);
    }
  }

  /**
   * reads long.
   *
   * @return long.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  public long readLong() throws CommandSyntaxException {
    final var start = this.cursor;
    while (this.canRead() && TextReader.isAllowedInteger(this.peek())) {
      this.skip();
    }
    final var number = this.text.substring(start, this.cursor);
    if (number.isEmpty()) {
      throw CommandException.READER_EXPECTED_LONG.createWithContext(this);
    }
    try {
      return Long.parseLong(number);
    } catch (final NumberFormatException ex) {
      this.cursor = start;
      throw CommandException.READER_INVALID_LONG.createWithContext(this, number);
    }
  }

  /**
   * reads quoted text.
   *
   * @return quoted text.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  public String readQuotedText() throws CommandSyntaxException {
    if (!this.canRead()) {
      return "";
    }
    final var next = this.peek();
    if (!TextReader.isQuotedTextStart(next)) {
      throw CommandException.READER_EXPECTED_START_OF_QUOTE.createWithContext(this);
    }
    this.skip();
    return this.readTextUntil(next);
  }

  /**
   * reads short.
   *
   * @return short.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  public short readShort() throws CommandSyntaxException {
    final var start = this.cursor;
    while (this.canRead() && TextReader.isAllowedNumber(this.peek())) {
      this.skip();
    }
    final var number = this.text.substring(start, this.cursor);
    if (number.isEmpty()) {
      throw CommandException.READER_EXPECTED_SHORT.createWithContext(this);
    }
    try {
      return Short.parseShort(number);
    } catch (final NumberFormatException ex) {
      this.cursor = start;
      throw CommandException.READER_INVALID_SHORT.createWithContext(this, number);
    }
  }

  /**
   * reads text.
   *
   * @return text.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  public String readText() throws CommandSyntaxException {
    if (!this.canRead()) {
      return "";
    }
    final var next = this.peek();
    if (TextReader.isQuotedTextStart(next)) {
      this.skip();
      return this.readTextUntil(next);
    }
    return this.readUnquotedText();
  }

  /**
   * reads unquoted text.
   *
   * @return unquoted text.
   */
  @NotNull
  public String readUnquotedText() {
    final var start = this.cursor;
    while (this.canRead() && TextReader.isAllowedInUnquotedText(this.peek())) {
      this.skip();
    }
    return this.text.substring(start, this.cursor);
  }

  /**
   * skips.
   */
  public void skip() {
    this.cursor++;
  }

  /**
   * skips whitespaces.
   */
  public void skipWhitespace() {
    while (this.canRead() && Character.isWhitespace(this.peek())) {
      this.skip();
    }
  }

  /**
   * reads until reached the {@code terminator}.
   *
   * @param terminator the terminator to read.
   *
   * @return text.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  private String readTextUntil(final char terminator) throws CommandSyntaxException {
    final var result = new StringBuilder();
    var escaped = false;
    while (this.canRead()) {
      final var c = this.read();
      if (escaped) {
        if (c == terminator || c == TextReader.SYNTAX_ESCAPE) {
          result.append(c);
          escaped = false;
        } else {
          this.setCursor(this.getCursor() - 1);
          throw CommandException.READER_INVALID_ESCAPE.createWithContext(this, String.valueOf(c));
        }
      } else if (c == TextReader.SYNTAX_ESCAPE) {
        escaped = true;
      } else if (c == terminator) {
        return result.toString();
      } else {
        result.append(c);
      }
    }
    throw CommandException.READER_EXPECTED_END_OF_QUOTE.createWithContext(this);
  }
}
