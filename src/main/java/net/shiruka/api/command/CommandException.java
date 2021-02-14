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

package net.shiruka.api.command;

import net.shiruka.api.command.exceptions.CeDynamic;
import net.shiruka.api.command.exceptions.CeDynamic2;
import net.shiruka.api.command.exceptions.CeSimple;

/**
 * a marker interface class to determine command exception classes.
 */
public interface CommandException {

  /**
   * the byte too big.
   */
  CeDynamic2 BYTE_TOO_BIG = new CeDynamic2((found, max) -> "Byte must not be more than " + max + ", found " + found);

  /**
   * the byte too small.
   */
  CeDynamic2 BYTE_TOO_SMALL = new CeDynamic2((found, min) -> "Byte must not be less than " + min + ", found " + found);

  /**
   * the dispatcher expected argument separator.
   */
  CeSimple DISPATCHER_EXPECTED_ARGUMENT_SEPARATOR = new CeSimple(
    "Expected whitespace to end one argument, but found trailing data");

  /**
   * the dispatcher parse exception.
   */
  CeDynamic DISPATCHER_PARSE_EXCEPTION = new CeDynamic(message -> "Could not parse command: " + message);

  /**
   * the dispatcher unknown argument.
   */
  CeSimple DISPATCHER_UNKNOWN_ARGUMENT = new CeSimple("Incorrect argument for command");

  /**
   * the dispatcher unknown command.
   */
  CeSimple DISPATCHER_UNKNOWN_COMMAND = new CeSimple("Unknown command");

  /**
   * the double too big.
   */
  CeDynamic2 DOUBLE_TOO_BIG = new CeDynamic2((found, max) ->
    "Double must not be more than " + max + ", found " + found);

  /**
   * the double too small.
   */
  CeDynamic2 DOUBLE_TOO_SMALL = new CeDynamic2((found, min) ->
    "Double must not be less than " + min + ", found " + found);

  /**
   * the float too big.
   */
  CeDynamic2 FLOAT_TOO_BIG = new CeDynamic2((found, max) -> "Float must not be more than " + max + ", found " + found);

  /**
   * the float too small.
   */
  CeDynamic2 FLOAT_TOO_SMALL = new CeDynamic2((found, min) ->
    "Float must not be less than " + min + ", found " + found);

  /**
   * the integer too big.
   */
  CeDynamic2 INTEGER_TOO_BIG = new CeDynamic2((found, max) ->
    "Integer must not be more than " + max + ", found " + found);

  /**
   * the integer too small.
   */
  CeDynamic2 INTEGER_TOO_SMALL = new CeDynamic2((found, min) ->
    "Integer must not be less than " + min + ", found " + found);

  /**
   * the literal incorrect.
   */
  CeDynamic LITERAL_INCORRECT = new CeDynamic(expected -> "Expected literal " + expected);

  /**
   * the long too big.
   */
  CeDynamic2 LONG_TOO_BIG = new CeDynamic2((found, max) -> "Long must not be more than " + max + ", found " + found);

  /**
   * the long too small.
   */
  CeDynamic2 LONG_TOO_SMALL = new CeDynamic2((found, min) -> "Long must not be less than " + min + ", found " + found);

  /**
   * the reader expected booolean.
   */
  CeSimple READER_EXPECTED_BOOL = new CeSimple("Expected bool");

  /**
   * the reader expected byte.
   */
  CeSimple READER_EXPECTED_BYTE = new CeSimple("Expected byte");

  /**
   * the reader expected double.
   */
  CeSimple READER_EXPECTED_DOUBLE = new CeSimple("Expected double");

  /**
   * the reader expected end of quote.
   */
  CeSimple READER_EXPECTED_END_OF_QUOTE = new CeSimple("Unclosed quoted string");

  /**
   * the reader expected float.
   */
  CeSimple READER_EXPECTED_FLOAT = new CeSimple("Expected float");

  /**
   * the reader expected int.
   */
  CeSimple READER_EXPECTED_INT = new CeSimple("Expected integer");

  /**
   * the reader expected long.
   */
  CeSimple READER_EXPECTED_LONG = new CeSimple("Expected long");

  /**
   * the reader expected short.
   */
  CeSimple READER_EXPECTED_SHORT = new CeSimple("Expected short");

  /**
   * the reader expected start of quote.
   */
  CeSimple READER_EXPECTED_START_OF_QUOTE = new CeSimple("Expected quote to start a string");

  /**
   * the reader expected symbol.
   */
  CeDynamic READER_EXPECTED_SYMBOL = new CeDynamic(symbol -> "Expected '" + symbol + "'");

  /**
   * the reader invalid boolean.
   */
  CeDynamic READER_INVALID_BOOL = new CeDynamic(value ->
    "Invalid bool, expected true or false but found '" + value + "'");

  /**
   * the reader invalid byte.
   */
  CeDynamic READER_INVALID_BYTE = new CeDynamic(value -> "Invalid byte '" + value + "'");

  /**
   * the reader invalid double.
   */
  CeDynamic READER_INVALID_DOUBLE = new CeDynamic(value -> "Invalid double '" + value + "'");

  /**
   * the reader invalid escape.
   */
  CeDynamic READER_INVALID_ESCAPE = new CeDynamic(character ->
    "Invalid escape sequence '" + character + "' in quoted string");

  /**
   * the reader invalid float.
   */
  CeDynamic READER_INVALID_FLOAT = new CeDynamic(value -> "Invalid float '" + value + "'");

  /**
   * the reader invalid int.
   */
  CeDynamic READER_INVALID_INT = new CeDynamic(value -> "Invalid integer '" + value + "'");

  /**
   * the reader invalid long.
   */
  CeDynamic READER_INVALID_LONG = new CeDynamic(value -> "Invalid long '" + value + "'");

  /**
   * the reader invalid short.
   */
  CeDynamic READER_INVALID_SHORT = new CeDynamic(value -> "Invalid short '" + value + "'");

  /**
   * the short too big.
   */
  CeDynamic2 SHORT_TOO_BIG = new CeDynamic2((found, max) -> "Short must not be more than " + max + ", found " + found);

  /**
   * the short too small.
   */
  CeDynamic2 SHORT_TOO_SMALL = new CeDynamic2((found, min) -> "Short must not be less than " + min + ", found " + found);

  /**
   * the term invalid.
   */
  CeDynamic TERM_INVALID = new CeDynamic(term -> "Invalid term '" + term + "'");
}
