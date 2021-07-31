package net.shiruka.api.event;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import net.shiruka.api.base.ChainData;
import net.shiruka.api.event.method.MethodAdapter;
import net.shiruka.api.event.method.SimpleMethodAdapter;
import net.shiruka.api.event.events.player.PlayerPreLoginEvent;
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
    if (!sent.isCancelled()) {
      this.loop();
    }
  }

  public static final class ListenerTest implements Listener {

    @EventHandler
    public void simpleEvent(final PlayerPreLoginEvent event) {
      if (EventTest.COUNTER.incrementAndGet() >= 5) {
        event.setCancelled(true);
      }
    }
  }

  public static final class SimpleEvent implements PlayerPreLoginEvent {

    private boolean cancelled = false;

    @NotNull
    @Override
    public ChainData getChainData() {
      return null;
    }

    @NotNull
    @Override
    public Text getKickMessage() {
      return Text.empty();
    }

    @Override
    public void setKickMessage(@Nullable final Text message) {
    }

    @Override
    public boolean isCancelled() {
      return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancelled) {
      this.cancelled = cancelled;
    }
  }
}
