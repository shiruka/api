package net.shiruka.api.old.event.events.player;

import java.util.function.Consumer;
import net.shiruka.api.old.entity.Player;
import net.shiruka.api.old.event.events.ChainDataEvent;
import net.shiruka.api.old.event.events.KickMessageEvent;
import net.shiruka.api.old.event.events.LoginEvent;
import net.shiruka.api.old.event.events.LoginResultEvent;
import net.shiruka.api.old.event.events.ObjectListEvent;

/**
 * an interface to determine player's async login events.
 */
public interface PlayerAsyncLoginEvent extends LoginEvent, ChainDataEvent, KickMessageEvent, LoginResultEvent,
  ObjectListEvent<Consumer<Player>> {

  @Override
  default boolean isAsync() {
    return true;
  }
}
