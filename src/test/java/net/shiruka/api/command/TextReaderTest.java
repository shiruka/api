package net.shiruka.api.command;

import static net.shiruka.api.command.CommandException.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;

final class TextReaderTest {

  @Test
  void canRead() {
    final var reader = new TextReader("abc");
    assertThat(reader.canRead(), is(true));
    reader.skip(); // 'a'
    assertThat(reader.canRead(), is(true));
    reader.skip(); // 'b'
    assertThat(reader.canRead(), is(true));
    reader.skip(); // 'c'
    assertThat(reader.canRead(), is(false));
  }

  @Test
  void canRead_length() {
    final var reader = new TextReader("abc");
    assertThat(reader.canRead(1), is(true));
    assertThat(reader.canRead(2), is(true));
    assertThat(reader.canRead(3), is(true));
    assertThat(reader.canRead(4), is(false));
    assertThat(reader.canRead(5), is(false));
  }

  @Test
  void expect_correct() throws Exception {
    final var reader = new TextReader("abc");
    reader.expect('a');
    assertThat(reader.getCursor(), is(1));
  }

  @Test
  void expect_incorrect() {
    final var reader = new TextReader("bcd");
    try {
      reader.expect('a');
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_SYMBOL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void expect_none() {
    final var reader = new TextReader("");
    try {
      reader.expect('a');
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_SYMBOL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void getRead() {
    final var reader = new TextReader("Hello!");
    assertThat(reader.getRead(), equalTo(""));
    reader.setCursor(3);
    assertThat(reader.getRead(), equalTo("Hel"));
    reader.setCursor(6);
    assertThat(reader.getRead(), equalTo("Hello!"));
  }

  @Test
  void getRemaining() {
    final var reader = new TextReader("Hello!");
    assertThat(reader.getRemaining(), equalTo("Hello!"));
    reader.setCursor(3);
    assertThat(reader.getRemaining(), equalTo("lo!"));
    reader.setCursor(6);
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void getRemainingLength() {
    final var reader = new TextReader("abc");
    assertThat(reader.getRemainingLength(), is(3));
    reader.setCursor(1);
    assertThat(reader.getRemainingLength(), is(2));
    reader.setCursor(2);
    assertThat(reader.getRemainingLength(), is(1));
    reader.setCursor(3);
    assertThat(reader.getRemainingLength(), is(0));
  }

  @Test
  void isAllowedInUnquotedStringInput2OutputTrue() {
    assertTrue(TextReader.isAllowedInUnquotedText('2'));
  }

  @Test
  void isAllowedInUnquotedStringInputNotNullOutputFalse() {
    assertFalse(TextReader.isAllowedInUnquotedText('\u0000'));
  }

  @Test
  void isAllowedInUnquotedStringInputNotNullOutputFalse2() {
    assertFalse(TextReader.isAllowedInUnquotedText(':'));
  }

  @Test
  void isAllowedInUnquotedStringInputOutputTrue() {
    assertTrue(TextReader.isAllowedInUnquotedText('r'));
  }

  @Test
  void peek() {
    final var reader = new TextReader("abc");
    assertThat(reader.peek(), is('a'));
    assertThat(reader.getCursor(), is(0));
    reader.setCursor(2);
    assertThat(reader.peek(), is('c'));
    assertThat(reader.getCursor(), is(2));
  }

  @Test
  void peek_length() {
    final var reader = new TextReader("abc");
    assertThat(reader.peek(0), is('a'));
    assertThat(reader.peek(2), is('c'));
    assertThat(reader.getCursor(), is(0));
    reader.setCursor(1);
    assertThat(reader.peek(1), is('c'));
    assertThat(reader.getCursor(), is(1));
  }

  @Test
  void read() {
    final var reader = new TextReader("abc");
    assertThat(reader.read(), is('a'));
    assertThat(reader.read(), is('b'));
    assertThat(reader.read(), is('c'));
    assertThat(reader.getCursor(), is(3));
  }

  @Test
  void readBoolean_correct() throws Exception {
    final var reader = new TextReader("true");
    assertThat(reader.readBoolean(), is(true));
    assertThat(reader.getRead(), equalTo("true"));
  }

  @Test
  void readBoolean_incorrect() {
    final var reader = new TextReader("tuesday");
    try {
      reader.readBoolean();
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_INVALID_BOOL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readBoolean_none() {
    final var reader = new TextReader("");
    try {
      reader.readBoolean();
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_BOOL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readBoolean_quoted() {
    final var reader = new TextReader("\"true\"");
    try {
      reader.readBoolean();
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_BOOL));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readDouble() throws Exception {
    final var reader = new TextReader("123");
    assertThat(reader.readDouble(), is(123.0));
    assertThat(reader.getRead(), equalTo("123"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readDouble_explicitPositive() throws Exception {
    final var reader = new TextReader("+123");
    assertThat(reader.readDouble(), is(+123.0));
    assertThat(reader.getRead(), equalTo("+123"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readDouble_explicitPositiveExponent() throws Exception {
    final var reader = new TextReader("123e+4");
    assertThat(reader.readDouble(), is(123e+4));
    assertThat(reader.getRead(), equalTo("123e+4"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readDouble_exponent() throws Exception {
    final var reader = new TextReader("123e4");
    assertThat(reader.readDouble(), is(123e4));
    assertThat(reader.getRead(), equalTo("123e4"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readDouble_invalid() {
    try {
      new TextReader("12.34.56").readDouble();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_INVALID_DOUBLE));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readDouble_negative() throws Exception {
    final var reader = new TextReader("-123");
    assertThat(reader.readDouble(), is(-123.0));
    assertThat(reader.getRead(), equalTo("-123"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readDouble_negativeExponent() throws Exception {
    final var reader = new TextReader("123e-4");
    assertThat(reader.readDouble(), is(123e-4));
    assertThat(reader.getRead(), equalTo("123e-4"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readDouble_none() {
    try {
      new TextReader("").readDouble();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_DOUBLE));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readDouble_withDecimal() throws Exception {
    final var reader = new TextReader("12.34");
    assertThat(reader.readDouble(), is(12.34));
    assertThat(reader.getRead(), equalTo("12.34"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readDouble_withRemaining() throws Exception {
    final var reader = new TextReader("12.34 foo bar");
    assertThat(reader.readDouble(), is(12.34));
    assertThat(reader.getRead(), equalTo("12.34"));
    assertThat(reader.getRemaining(), equalTo(" foo bar"));
  }

  @Test
  void readDouble_withRemainingImmediate() throws Exception {
    final var reader = new TextReader("12.34foo bar");
    assertThat(reader.readDouble(), is(12.34));
    assertThat(reader.getRead(), equalTo("12.34"));
    assertThat(reader.getRemaining(), equalTo("foo bar"));
  }

  @Test
  void readFloat() throws Exception {
    final var reader = new TextReader("123");
    assertThat(reader.readFloat(), is(123.0f));
    assertThat(reader.getRead(), equalTo("123"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readFloat_explicitPositive() throws Exception {
    final var reader = new TextReader("+123");
    assertThat(reader.readFloat(), is(+123.0f));
    assertThat(reader.getRead(), equalTo("+123"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readFloat_explicitPositiveExponent() throws Exception {
    final var reader = new TextReader("123e+4");
    assertThat(reader.readFloat(), is(123e+4f));
    assertThat(reader.getRead(), equalTo("123e+4"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readFloat_exponent() throws Exception {
    final var reader = new TextReader("123e4");
    assertThat(reader.readFloat(), is(123e4f));
    assertThat(reader.getRead(), equalTo("123e4"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readFloat_invalid() {
    try {
      new TextReader("12.34.56").readFloat();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_INVALID_FLOAT));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readFloat_negative() throws Exception {
    final var reader = new TextReader("-123");
    assertThat(reader.readFloat(), is(-123.0f));
    assertThat(reader.getRead(), equalTo("-123"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readFloat_negativeExponent() throws Exception {
    final var reader = new TextReader("123e-4");
    assertThat(reader.readFloat(), is(123e-4f));
    assertThat(reader.getRead(), equalTo("123e-4"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readFloat_none() {
    try {
      new TextReader("").readFloat();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_FLOAT));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readFloat_withDecimal() throws Exception {
    final var reader = new TextReader("12.34");
    assertThat(reader.readFloat(), is(12.34f));
    assertThat(reader.getRead(), equalTo("12.34"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readFloat_withRemaining() throws Exception {
    final var reader = new TextReader("12.34 foo bar");
    assertThat(reader.readFloat(), is(12.34f));
    assertThat(reader.getRead(), equalTo("12.34"));
    assertThat(reader.getRemaining(), equalTo(" foo bar"));
  }

  @Test
  void readFloat_withRemainingImmediate() throws Exception {
    final var reader = new TextReader("12.34foo bar");
    assertThat(reader.readFloat(), is(12.34f));
    assertThat(reader.getRead(), equalTo("12.34"));
    assertThat(reader.getRemaining(), equalTo("foo bar"));
  }

  @Test
  void readInt() throws Exception {
    final var reader = new TextReader("1234567890");
    assertThat(reader.readInt(), is(1234567890));
    assertThat(reader.getRead(), equalTo("1234567890"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readInt_explicitPositive() throws CommandSyntaxException {
    final var reader = new TextReader("+1234567890");
    assertThat(reader.readInt(), is(+1234567890));
    assertThat(reader.getRead(), equalTo("+1234567890"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readInt_invalid() {
    try {
      new TextReader("12.34").readInt();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_INVALID_INT));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readInt_negative() throws Exception {
    final var reader = new TextReader("-1234567890");
    assertThat(reader.readInt(), is(-1234567890));
    assertThat(reader.getRead(), equalTo("-1234567890"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readInt_none() {
    try {
      new TextReader("").readInt();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_INT));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readInt_withRemaining() throws Exception {
    final var reader = new TextReader("1234567890 foo bar");
    assertThat(reader.readInt(), is(1234567890));
    assertThat(reader.getRead(), equalTo("1234567890"));
    assertThat(reader.getRemaining(), equalTo(" foo bar"));
  }

  @Test
  void readInt_withRemainingImmediate() throws Exception {
    final var reader = new TextReader("1234567890foo bar");
    assertThat(reader.readInt(), is(1234567890));
    assertThat(reader.getRead(), equalTo("1234567890"));
    assertThat(reader.getRemaining(), equalTo("foo bar"));
  }

  @Test
  void readLong() throws Exception {
    final var reader = new TextReader("1234567890");
    assertThat(reader.readLong(), is(1234567890L));
    assertThat(reader.getRead(), equalTo("1234567890"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readLong_explicitPositive() throws Exception {
    final var reader = new TextReader("+1234567890");
    assertThat(reader.readLong(), is(+1234567890L));
    assertThat(reader.getRead(), equalTo("+1234567890"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readLong_invalid() {
    try {
      new TextReader("12.34").readLong();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_INVALID_LONG));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readLong_negative() throws Exception {
    final var reader = new TextReader("-1234567890");
    assertThat(reader.readLong(), is(-1234567890L));
    assertThat(reader.getRead(), equalTo("-1234567890"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readLong_none() {
    try {
      new TextReader("").readLong();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_LONG));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readLong_withRemaining() throws Exception {
    final var reader = new TextReader("1234567890 foo bar");
    assertThat(reader.readLong(), is(1234567890L));
    assertThat(reader.getRead(), equalTo("1234567890"));
    assertThat(reader.getRemaining(), equalTo(" foo bar"));
  }

  @Test
  void readLong_withRemainingImmediate() throws Exception {
    final var reader = new TextReader("1234567890foo bar");
    assertThat(reader.readLong(), is(1234567890L));
    assertThat(reader.getRead(), equalTo("1234567890"));
    assertThat(reader.getRemaining(), equalTo("foo bar"));
  }

  @Test
  void readMixedQuotedString_doubleInsideSingle() throws CommandSyntaxException {
    final var reader = new TextReader("'hello \"world\"'");
    assertThat(reader.readQuotedText(), equalTo("hello \"world\""));
    assertThat(reader.getRead(), equalTo("'hello \"world\"'"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readMixedQuotedString_singleInsideDouble() throws Exception {
    final var reader = new TextReader("\"hello 'world'\"");
    assertThat(reader.readQuotedText(), equalTo("hello 'world'"));
    assertThat(reader.getRead(), equalTo("\"hello 'world'\""));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readQuotedString() throws Exception {
    final var reader = new TextReader("\"hello world\"");
    assertThat(reader.readQuotedText(), equalTo("hello world"));
    assertThat(reader.getRead(), equalTo("\"hello world\""));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readQuotedString_empty() throws Exception {
    final var reader = new TextReader("");
    assertThat(reader.readQuotedText(), equalTo(""));
    assertThat(reader.getRead(), equalTo(""));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readQuotedString_emptyQuoted() throws Exception {
    final var reader = new TextReader("\"\"");
    assertThat(reader.readQuotedText(), equalTo(""));
    assertThat(reader.getRead(), equalTo("\"\""));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readQuotedString_emptyQuoted_withRemaining() throws Exception {
    final var reader = new TextReader("\"\" hello world");
    assertThat(reader.readQuotedText(), equalTo(""));
    assertThat(reader.getRead(), equalTo("\"\""));
    assertThat(reader.getRemaining(), equalTo(" hello world"));
  }

  @Test
  void readQuotedString_invalidEscape() {
    try {
      new TextReader("\"hello\nworld\"").readQuotedText();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_INVALID_ESCAPE));
      assertThat(ex.getCursor(), is(7));
    }
  }

  @Test
  void readQuotedString_invalidQuoteEscape() {
    try {
      new TextReader("'hello\\\"'world").readQuotedText();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_INVALID_ESCAPE));
      assertThat(ex.getCursor(), is(7));
    }
  }

  @Test
  void readQuotedString_noClose() {
    try {
      new TextReader("\"hello world").readQuotedText();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_END_OF_QUOTE));
      assertThat(ex.getCursor(), is(12));
    }
  }

  @Test
  void readQuotedString_noOpen() {
    try {
      new TextReader("hello world\"").readQuotedText();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_START_OF_QUOTE));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void readQuotedString_withEscapedEscapes() throws Exception {
    final var reader = new TextReader("\"\\\\o/\"");
    assertThat(reader.readQuotedText(), equalTo("\\o/"));
    assertThat(reader.getRead(), equalTo("\"\\\\o/\""));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readQuotedString_withEscapedQuote() throws Exception {
    final var reader = new TextReader("\"hello \\\"world\\\"\"");
    assertThat(reader.readQuotedText(), equalTo("hello \"world\""));
    assertThat(reader.getRead(), equalTo("\"hello \\\"world\\\"\""));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readQuotedString_withImmediateRemaining() throws Exception {
    final var reader = new TextReader("\"hello world\"foo bar");
    assertThat(reader.readQuotedText(), equalTo("hello world"));
    assertThat(reader.getRead(), equalTo("\"hello world\""));
    assertThat(reader.getRemaining(), equalTo("foo bar"));
  }

  @Test
  void readQuotedString_withRemaining() throws Exception {
    final var reader = new TextReader("\"hello world\" foo bar");
    assertThat(reader.readQuotedText(), equalTo("hello world"));
    assertThat(reader.getRead(), equalTo("\"hello world\""));
    assertThat(reader.getRemaining(), equalTo(" foo bar"));
  }

  @Test
  void readSingleQuotedString() throws Exception {
    final var reader = new TextReader("'hello world'");
    assertThat(reader.readQuotedText(), equalTo("hello world"));
    assertThat(reader.getRead(), equalTo("'hello world'"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readString_doubleQuotes() throws Exception {
    final var reader = new TextReader("\"hello world\"");
    assertThat(reader.readText(), equalTo("hello world"));
    assertThat(reader.getRead(), equalTo("\"hello world\""));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readString_noQuotes() throws Exception {
    final var reader = new TextReader("hello world");
    assertThat(reader.readText(), equalTo("hello"));
    assertThat(reader.getRead(), equalTo("hello"));
    assertThat(reader.getRemaining(), equalTo(" world"));
  }

  @Test
  void readString_singleQuotes() throws Exception {
    final var reader = new TextReader("'hello world'");
    assertThat(reader.readText(), equalTo("hello world"));
    assertThat(reader.getRead(), equalTo("'hello world'"));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readUnquotedString() {
    final var reader = new TextReader("hello world");
    assertThat(reader.readUnquotedText(), equalTo("hello"));
    assertThat(reader.getRead(), equalTo("hello"));
    assertThat(reader.getRemaining(), equalTo(" world"));
  }

  @Test
  void readUnquotedString_empty() {
    final var reader = new TextReader("");
    assertThat(reader.readUnquotedText(), equalTo(""));
    assertThat(reader.getRead(), equalTo(""));
    assertThat(reader.getRemaining(), equalTo(""));
  }

  @Test
  void readUnquotedString_empty_withRemaining() {
    final var reader = new TextReader(" hello world");
    assertThat(reader.readUnquotedText(), equalTo(""));
    assertThat(reader.getRead(), equalTo(""));
    assertThat(reader.getRemaining(), equalTo(" hello world"));
  }

  @Test
  void skip() {
    final var reader = new TextReader("abc");
    reader.skip();
    assertThat(reader.getCursor(), is(1));
  }

  @Test
  void skipWhitespace_empty() {
    final var reader = new TextReader("");
    reader.skipWhitespace();
    assertThat(reader.getCursor(), is(0));
  }

  @Test
  void skipWhitespace_mixed() {
    final var reader = new TextReader(" \t \t\nHello!");
    reader.skipWhitespace();
    assertThat(reader.getCursor(), is(5));
  }

  @Test
  void skipWhitespace_none() {
    final var reader = new TextReader("Hello!");
    reader.skipWhitespace();
    assertThat(reader.getCursor(), is(0));
  }
}
