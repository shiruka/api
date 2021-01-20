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

import static net.shiruka.api.command.Commands.defaultLiteral;
import static net.shiruka.api.command.Commands.literal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.util.stream.Collectors;
import net.shiruka.api.command.sender.CommandSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
final class CommandDispatcherUsagesTest {

  @Mock
  private Command command;

  @Mock
  private CommandSender source;

  private CommandDispatcher subject;

  @BeforeEach
  void setUp() {
    this.subject = new CommandDispatcher();
    this.subject.register(literal("a")
      .then(literal("1")
        .then(literal("i")
          .executes(this.command))
        .then(literal("ii")
          .executes(this.command)))
      .then(literal("2")
        .then(literal("i")
          .executes(this.command))
        .then(literal("ii")
          .executes(this.command))));
    this.subject.register(literal("b")
      .then(literal("1")
        .executes(this.command)));
    this.subject.register(literal("c")
      .executes(this.command));
    this.subject.register(literal("d")
      .requires(s -> false)
      .executes(this.command));
    this.subject.register(literal("e")
      .executes(this.command)
      .then(literal("1")
        .executes(this.command)
        .then(literal("i")
          .executes(this.command))
        .then(literal("ii")
          .executes(this.command))));
    this.subject.register(literal("f")
      .then(literal("1")
        .then(literal("i")
          .executes(this.command))
        .then(literal("ii")
          .executes(this.command)
          .requires(s -> false)))
      .then(literal("2")
        .then(literal("i")
          .executes(this.command)
          .requires(s -> false))
        .then(literal("ii")
          .executes(this.command))));
    this.subject.register(literal("g")
      .executes(this.command)
      .then(literal("1")
        .then(literal("i")
          .executes(this.command))));
    this.subject.register(literal("h")
      .executes(this.command)
      .then(literal("1")
        .then(literal("i")
          .executes(this.command)))
      .then(literal("2")
        .then(literal("i")
          .then(literal("ii")
            .executes(this.command))))
      .then(literal("3")
        .executes(this.command)));
    this.subject.register(literal("i")
      .executes(this.command)
      .then(literal("1")
        .executes(this.command))
      .then(literal("2")
        .executes(this.command)));
    this.subject.register(literal("j")
      .redirect(this.subject.getRoot()));
    this.subject.register(literal("k")
      .redirect(this.get("h")));
    this.subject.register(
      literal("l")
        .then(defaultLiteral("1")
          .executes(this.command))
        .then(literal("2")
          .then(defaultLiteral("i")
            .executes(this.command))
          .then(literal("ii")
            .executes(this.command)))
        .then(literal("3")
          .then(defaultLiteral("i")
            .then(defaultLiteral("ii")
              .executes(this.command))))
        .then(literal("4")
          .then(defaultLiteral("i")
            .then(literal("ii")
              .executes(this.command))))
    );
  }

  @Test
  void testAllUsage_noCommands() {
    this.subject = new CommandDispatcher();
    final var results = this.subject.getAllUsage(this.subject.getRoot(), this.source, true);
    assertThat(results, is(emptyArray()));
  }

  @Test
  void testAllUsage_root() {
    final String[] results = this.subject.getAllUsage(this.subject.getRoot(), this.source, true);
    assertThat(results, equalTo(new String[]{
      "a 1 i",
      "a 1 ii",
      "a 2 i",
      "a 2 ii",
      "b 1",
      "c",
      "e",
      "e 1",
      "e 1 i",
      "e 1 ii",
      "f 1 i",
      "f 2 ii",
      "g",
      "g 1 i",
      "h",
      "h 1 i",
      "h 2 i ii",
      "h 3",
      "i",
      "i 1",
      "i 2",
      "j ...",
      "k -> h",
      "l",
      "l 1",
      "l 2",
      "l 2 i",
      "l 2 ii",
      "l 3",
      "l 3 i",
      "l 3 i ii",
      "l 4 i ii",
    }));
  }

  @Test
  void testSmartUsage_h() {
    final var results = this.subject.getSmartUsage(this.get("h"), this.source);
    assertThat(results, equalTo(ImmutableMap.builder()
      .put(this.get("h 1"), "[1] i")
      .put(this.get("h 2"), "[2] i ii")
      .put(this.get("h 3"), "[3]")
      .build()
    ));
  }

  @Test
  void testSmartUsage_l() {
    final var results = this.subject.getSmartUsage(this.get("l"), this.source);
    assertThat(results, equalTo(ImmutableMap.builder()
      .put(this.get("l 1"), "1")
      .put(this.get("l 2"), "2 [i|ii]")
      .put(this.get("l 3"), "3 [i]")
      .put(this.get("l 4"), "4 i ii")
      .build()
    ));
  }

  @Test
  void testSmartUsage_noCommands() {
    this.subject = new CommandDispatcher();
    final var results = this.subject.getSmartUsage(this.subject.getRoot(), this.source);
    assertThat(results.entrySet(), is(empty()));
  }

  @Test
  void testSmartUsage_offsetH() {
    final var offsetH = new TextReader("/|/|/h");
    offsetH.setCursor(5);
    final var results = this.subject.getSmartUsage(this.get(offsetH), this.source);
    assertThat(results, equalTo(ImmutableMap.builder()
      .put(this.get("h 1"), "[1] i")
      .put(this.get("h 2"), "[2] i ii")
      .put(this.get("h 3"), "[3]")
      .build()
    ));
  }

  @Test
  void testSmartUsage_root() {
    final var results = this.subject.getSmartUsage(this.subject.getRoot(), this.source);
    assertThat(results, equalTo(ImmutableMap.builder()
      .put(this.get("a"), "a (1|2)")
      .put(this.get("b"), "b 1")
      .put(this.get("c"), "c")
      .put(this.get("e"), "e [1]")
      .put(this.get("f"), "f (1|2)")
      .put(this.get("g"), "g [1]")
      .put(this.get("h"), "h [1|2|3]")
      .put(this.get("i"), "i [1|2]")
      .put(this.get("j"), "j ...")
      .put(this.get("k"), "k -> h")
      .put(this.get("l"), "l [1|2|3|4]")
      .build()
    ));
  }

  private CommandNode get(final String command) {
    return Iterables.getLast(this.subject.parse(command, this.source)
      .getBuilder()
      .getNodes()
      .stream()
      .filter(n -> !n.getRange().isEmpty())
      .collect(Collectors.toList()))
      .getNode();
  }

  private CommandNode get(final TextReader command) {
    return Iterables.getLast(this.subject.parse(command, this.source)
      .getBuilder()
      .getNodes()
      .stream()
      .filter(n -> !n.getRange().isEmpty())
      .collect(Collectors.toList()))
      .getNode();
  }
}
