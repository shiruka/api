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

package io.github.shiruka.api.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this is a required annotation to be placed above the
 * main class in order to describe the properties of
 * the plugin when it is loaded by the server.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {

  /**
   * a unique ID that identifies a particular plugin.
   *
   * <p>The ID cannot be "minecraft" or "shiruka" as they
   * are reserved names.</p>
   *
   * @return the id.
   */
  String id();

  /**
   * the name of the plugin. This need not be unique, but
   * is recommended to be short and iconic. Avoid using
   * newlines.
   *
   * @return the name.
   */
  String name();

  /**
   * the plugin version.
   *
   * @return the version.
   */
  String version() default "1.0.0";

  /**
   * the plugin author.
   *
   * @return the author.
   */
  String author() default "";

  /**
   * plugin dependencies.
   * <p>
   * dependencies must have a special format.
   * they should be formatted {@code <id>:<version>} such that
   * the correct plugin and version should be loaded prior
   * to loading the plugin.
   *
   * @return the plugin dependencies.
   */
  String[] depends() default {};
}
