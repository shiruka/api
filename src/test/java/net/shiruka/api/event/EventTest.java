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

package net.shiruka.api.event;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import net.shiruka.api.event.method.MethodAdapter;
import net.shiruka.api.event.method.SimpleMethodAdapter;
import net.shiruka.api.events.player.PlayerPreLoginEvent;
import net.shiruka.api.text.Text;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

final class EventTest {

  private static final MethodAdapter ADAPTER = new SimpleMethodAdapter();

  private static final AtomicInteger COUNTER = new AtomicInteger();

  @Test
  void runTest() {
    EventTest.ADAPTER.register(new ListenerTest());
    this.loop();
    MatcherAssert.assertThat(
      "Event system not working well!",
      EventTest.COUNTER.get(),
      new IsEqual<>(5));
  }

  private void loop() {
    final var sent = new SimpleEvent();
    EventTest.ADAPTER.call(sent);
    if (!sent.cancelled()) {
      this.loop();
    }
  }

  public static final class ListenerTest implements Listener {

    @EventHandler
    public void simpleEvent(final PlayerPreLoginEvent event) {
      if (EventTest.COUNTER.incrementAndGet() >= 5) {
        event.cancel();
      }
    }
  }

  public static final class SimpleEvent implements PlayerPreLoginEvent {

    private boolean cancelled = false;

    @Override
    public void cancel() {
      this.cancelled = true;
    }

    @Override
    public boolean cancelled() {
      return this.cancelled;
    }

    @Override
    public void dontCancel() {
      this.cancelled = false;
    }

    @NotNull
    @Override
    public Optional<Text> kickMessage() {
      return null;
    }

    @Override
    public void kickMessage(@Nullable final Text message) {
    }

    @NotNull
    @Override
    public LoginData loginData() {
      return null;
    }
  }
}
