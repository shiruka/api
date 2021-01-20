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

package net.shiruka.api.command.builder;

import static net.shiruka.api.command.Commands.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import net.shiruka.api.command.ArgumentType;
import net.shiruka.api.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
final class RequiredArgumentBuilderTest {

  private RequiredBuilder<Integer> builder;

  @Mock
  private
  Command command;

  @Mock
  private ArgumentType<Integer> type;

  @BeforeEach
  void setUp() {
    this.builder = arg("foo", this.type);
  }

  @Test
  void testBuild() {
    final var node = this.builder.build();
    assertThat(node.getName(), is("foo"));
    assertThat(node.getType(), is(this.type));
    assertThat(node.isDefaultNode(), is(false));
    assertThat(node.getDefaultValue(), is(nullValue()));
  }

  @Test
  void testBuildDefaultNode() {
    final var node = defaultArg("foo", this.type, 42).build();
    assertThat(node.getName(), is("foo"));
    assertThat(node.getType(), is(this.type));
    assertThat(node.isDefaultNode(), is(true));
    assertThat(node.getDefaultValue(), is(42));
  }

  @Test
  void testBuildWithChildren() {
    this.builder.then(arg("bar", integerArg()));
    this.builder.then(arg("baz", integerArg()));
    final var node = this.builder.build();
    assertThat(node.getChildren(), hasSize(2));
  }

  @Test
  void testBuildWithExecutor() {
    final var node = this.builder.executes(this.command).build();
    assertThat(node.getName(), is("foo"));
    assertThat(node.getType(), is(this.type));
    assertThat(node.getCommand(), is(this.command));
  }
}
