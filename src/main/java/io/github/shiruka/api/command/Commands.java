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

import io.github.shiruka.api.command.arguments.*;
import io.github.shiruka.api.command.builder.LiteralBuilder;
import io.github.shiruka.api.command.builder.RequiredBuilder;
import io.github.shiruka.api.command.context.CommandContext;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * an interface that contains only static methods which help developers to create command easily.
 */
public interface Commands {

  /**
   * creates a new required argument node builder instance.
   *
   * @param name the name to create.
   *
   * @return a new argument builder instance.
   */
  @NotNull
  static <V> RequiredBuilder<V> arg(@NotNull final String name, @NotNull final ArgumentType<V> type) {
    return new RequiredBuilder<>(name, type);
  }

  /**
   * creates a simple boolean argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Boolean> booleanArg() {
    return new BooleanArgumentType();
  }

  /**
   * creates a simple double argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Byte> byteArg() {
    return Commands.byteArg(Byte.MIN_VALUE);
  }

  /**
   * creates a simple double argument type.
   *
   * @param minimum the minimum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Byte> byteArg(final byte minimum) {
    return Commands.byteArg(minimum, Byte.MAX_VALUE);
  }

  /**
   * creates a simple double argument type.
   *
   * @param minimum the minimum value to create.
   * @param maximum the maximum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Byte> byteArg(final byte minimum, final byte maximum) {
    return new ByteArgumentType(minimum, maximum);
  }

  /**
   * creates a simple double argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Double> doubleArg() {
    return Commands.doubleArg(-Double.MAX_VALUE);
  }

  /**
   * creates a simple double argument type.
   *
   * @param minimum the minimum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Double> doubleArg(final double minimum) {
    return Commands.doubleArg(minimum, Double.MAX_VALUE);
  }

  /**
   * creates a simple double argument type.
   *
   * @param minimum the minimum value to create.
   * @param maximum the maximum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Double> doubleArg(final double minimum, final double maximum) {
    return new DoubleArgumentType(minimum, maximum);
  }

  /**
   * creates a simple boolean argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Float> floatArg() {
    return Commands.floatArg(-Float.MAX_VALUE);
  }

  /**
   * creates a simple boolean argument type.
   *
   * @param minimum the minimum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Float> floatArg(final float minimum) {
    return Commands.floatArg(minimum, Float.MAX_VALUE);
  }

  /**
   * creates a simple boolean argument type.
   *
   * @param minimum the minimum value to create.
   * @param maximum the maximum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Float> floatArg(final float minimum, final float maximum) {
    return new FloatArgumentType(minimum, maximum);
  }

  /**
   * obtains the boolean value.
   *
   * @param context the context to obtain.
   * @param name the name to obtain.
   *
   * @return a boolean value.
   */
  static boolean getBoolean(@NotNull final CommandContext context, @NotNull final String name) {
    return context.getArgument(name, Boolean.class);
  }

  /**
   * obtains the byte value.
   *
   * @param context the context to obtain.
   * @param name the name to obtain.
   *
   * @return a byte value.
   */
  static byte getByte(@NotNull final CommandContext context, @NotNull final String name) {
    return context.getArgument(name, byte.class);
  }

  /**
   * obtains the double value.
   *
   * @param context the context to obtain.
   * @param name the name to obtain.
   *
   * @return a double value.
   */
  static double getDouble(@NotNull final CommandContext context, @NotNull final String name) {
    return context.getArgument(name, Double.class);
  }

  /**
   * obtains the float value.
   *
   * @param context the context to obtain.
   * @param name the name to obtain.
   *
   * @return a float value.
   */
  static float getFloat(@NotNull final CommandContext context, @NotNull final String name) {
    return context.getArgument(name, Float.class);
  }

  /**
   * obtains the int value.
   *
   * @param context the context to obtain.
   * @param name the name to obtain.
   *
   * @return a int value.
   */
  static int getInteger(@NotNull final CommandContext context, @NotNull final String name) {
    return context.getArgument(name, int.class);
  }

  /**
   * obtains the long value.
   *
   * @param context the context to obtain.
   * @param name the name to obtain.
   *
   * @return a long value.
   */
  static long getLong(@NotNull final CommandContext context, @NotNull final String name) {
    return context.getArgument(name, long.class);
  }

  /**
   * obtains the string value.
   *
   * @param context the context to obtain.
   * @param name the name to obtain.
   *
   * @return a string value.
   */
  @NotNull
  static String getString(@NotNull final CommandContext context, @NotNull final String name) {
    return context.getArgument(name, String.class);
  }

  /**
   * creates a simple greedy argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static StringArgumentType greedyArg() {
    return new StringArgumentType(StringArgumentType.StringType.GREEDY_PHRASE);
  }

  /**
   * creates a simple double argument type.
   *
   * @param minimum the minimum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Integer> integerArg(final int minimum) {
    return Commands.integerArg(minimum, Integer.MAX_VALUE);
  }

  /**
   * creates a simple double argument type.
   *
   * @param minimum the minimum value to create.
   * @param maximum the maximum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Integer> integerArg(final int minimum, final int maximum) {
    return new IntegerArgumentType(minimum, maximum);
  }

  /**
   * creates a simple double argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<Integer> integerArg() {
    return Commands.integerArg(Integer.MIN_VALUE);
  }

  /**
   * creates a new instance of {@link LiteralBuilder}.
   *
   * @param name the name to create.
   *
   * @return a new command builder instance.
   */
  @NotNull
  static LiteralBuilder literal(@NotNull final String name) {
    return new LiteralBuilder(name);
  }

  /**
   * creates a simple double argument type.
   *
   * @param minimum the minimum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static LongArgumentType longArg(final long minimum) {
    return Commands.longArg(minimum, Long.MAX_VALUE);
  }

  /**
   * creates a simple double argument type.
   *
   * @param minimum the minimum value to create.
   * @param maximum the maximum value to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static LongArgumentType longArg(final long minimum, final long maximum) {
    return new LongArgumentType(minimum, maximum);
  }

  /**
   * creates a simple double argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static LongArgumentType longArg() {
    return Commands.longArg(Long.MIN_VALUE);
  }

  /**
   * creates a simple string argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static StringArgumentType stringArg() {
    return new StringArgumentType(StringArgumentType.StringType.QUOTABLE_PHRASE);
  }

  /**
   * creates a simple unique id argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static ArgumentType<UUID> uniqueIdArg() {
    return new UniqueIdArgumentType();
  }

  /**
   * creates a simple word argument type.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  static StringArgumentType wordArg() {
    return new StringArgumentType(StringArgumentType.StringType.SINGLE_WORD);
  }
}
