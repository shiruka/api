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

package net.shiruka.api.config.config;

import java.io.File;
import net.shiruka.api.config.Paths;
import net.shiruka.api.config.path.simple.*;
import org.jetbrains.annotations.NotNull;

public final class TestYamlConfig extends YamlConfig {

  protected static final CpLong longTest = Paths.longPath("long-test", 1L);

  static final CpInteger intTest = Paths.integerPath("int-test", 1);

  private static final CpDouble doubleTest = Paths.doublePath("double-test", 1.0d);

  public final CpString textTest = Paths.stringPath("text-test", "text");

  final CpFloat floatTest = Paths.floatPath("float-test", 1.0f);

  private final CpBoolean boolTest = Paths.booleanPath("bool-test", true);

  public TestYamlConfig() {
    super(new File("target/testconfig", "test.yaml"));
  }

  @NotNull
  public static CpDouble getDoubleTest() {
    return TestYamlConfig.doubleTest;
  }

  @NotNull
  public static CpInteger getIntTest() {
    return TestYamlConfig.intTest;
  }

  @NotNull
  public static CpLong getLongTest() {
    return TestYamlConfig.longTest;
  }

  @NotNull
  public CpBoolean getBoolTest() {
    return this.boolTest;
  }

  @NotNull
  public CpFloat getFloatTest() {
    return this.floatTest;
  }

  @NotNull
  public CpString getTextTest() {
    return this.textTest;
  }
}
