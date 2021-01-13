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

package io.github.shiruka.api.command;

import io.github.shiruka.api.command.exceptions.CeDynamic;
import io.github.shiruka.api.command.exceptions.CeDynamic2;
import io.github.shiruka.api.command.exceptions.CeSimple;

/**
 * a marker interface class to determine command exception classes.
 * @todo #1:15m Fill Javadocs of the exception fields.
 */
public interface CommandException {

  CeDynamic2 BYTE_TOO_BIG = new CeDynamic2((found, max) -> "Byte must not be more than " + max + ", found " + found);

  CeDynamic2 BYTE_TOO_SMALL = new CeDynamic2((found, min) -> "Byte must not be less than " + min + ", found " + found);

  CeSimple DISPATCHER_EXPECTED_ARGUMENT_SEPARATOR = new CeSimple(
    "Expected whitespace to end one argument, but found trailing data");

  CeDynamic DISPATCHER_PARSE_EXCEPTION = new CeDynamic(message -> "Could not parse command: " + message);

  CeSimple DISPATCHER_UNKNOWN_ARGUMENT = new CeSimple("Incorrect argument for command");

  CeSimple DISPATCHER_UNKNOWN_COMMAND = new CeSimple("Unknown command");

  CeDynamic2 DOUBLE_TOO_BIG = new CeDynamic2((found, max) ->
    "Double must not be more than " + max + ", found " + found);

  CeDynamic2 DOUBLE_TOO_SMALL = new CeDynamic2((found, min) ->
    "Double must not be less than " + min + ", found " + found);

  CeDynamic2 FLOAT_TOO_BIG = new CeDynamic2((found, max) -> "Float must not be more than " + max + ", found " + found);

  CeDynamic2 FLOAT_TOO_SMALL = new CeDynamic2((found, min) ->
    "Float must not be less than " + min + ", found " + found);

  CeDynamic2 INTEGER_TOO_BIG = new CeDynamic2((found, max) ->
    "Integer must not be more than " + max + ", found " + found);

  CeDynamic2 INTEGER_TOO_SMALL = new CeDynamic2((found, min) ->
    "Integer must not be less than " + min + ", found " + found);

  CeDynamic LITERAL_INCORRECT = new CeDynamic(expected -> "Expected literal " + expected);

  CeDynamic2 LONG_TOO_BIG = new CeDynamic2((found, max) -> "Long must not be more than " + max + ", found " + found);

  CeDynamic2 LONG_TOO_SMALL = new CeDynamic2((found, min) -> "Long must not be less than " + min + ", found " + found);

  CeSimple READER_EXPECTED_BOOL = new CeSimple("Expected bool");

  CeSimple READER_EXPECTED_BYTE = new CeSimple("Expected byte");

  CeSimple READER_EXPECTED_DOUBLE = new CeSimple("Expected double");

  CeSimple READER_EXPECTED_END_OF_QUOTE = new CeSimple("Unclosed quoted string");

  CeSimple READER_EXPECTED_FLOAT = new CeSimple("Expected float");

  CeSimple READER_EXPECTED_INT = new CeSimple("Expected integer");

  CeSimple READER_EXPECTED_LONG = new CeSimple("Expected long");

  CeSimple READER_EXPECTED_SHORT = new CeSimple("Expected short");

  CeSimple READER_EXPECTED_START_OF_QUOTE = new CeSimple("Expected quote to start a string");

  CeDynamic READER_EXPECTED_SYMBOL = new CeDynamic(symbol -> "Expected '" + symbol + "'");

  CeDynamic READER_INVALID_BOOL = new CeDynamic(value ->
    "Invalid bool, expected true or false but found '" + value + "'");

  CeDynamic READER_INVALID_BYTE = new CeDynamic(value -> "Invalid byte '" + value + "'");

  CeDynamic READER_INVALID_DOUBLE = new CeDynamic(value -> "Invalid double '" + value + "'");

  CeDynamic READER_INVALID_ESCAPE = new CeDynamic(character ->
    "Invalid escape sequence '" + character + "' in quoted string");

  CeDynamic READER_INVALID_FLOAT = new CeDynamic(value -> "Invalid float '" + value + "'");

  CeDynamic READER_INVALID_INT = new CeDynamic(value -> "Invalid integer '" + value + "'");

  CeDynamic READER_INVALID_LONG = new CeDynamic(value -> "Invalid long '" + value + "'");

  CeDynamic READER_INVALID_SHORT = new CeDynamic(value -> "Invalid short '" + value + "'");

  CeDynamic2 SHORT_TOO_BIG = new CeDynamic2((found, max) -> "Short must not be more than " + max + ", found " + found);

  CeDynamic2 SHORT_TOO_SMALL = new CeDynamic2((found, min) -> "Short must not be less than " + min + ", found " + found);

  CeDynamic TERM_INVALID = new CeDynamic(term -> "Invalid term '" + term + "'");
}
