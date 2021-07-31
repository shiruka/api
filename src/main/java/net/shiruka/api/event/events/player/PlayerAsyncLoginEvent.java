package net.shiruka.api.event.events.player;

import java.util.function.Consumer;
import net.shiruka.api.entity.Player;
import net.shiruka.api.event.events.ChainDataEvent;
import net.shiruka.api.event.events.KickMessageEvent;
import net.shiruka.api.event.events.LoginEvent;
import net.shiruka.api.event.events.LoginResultEvent;
import net.shiruka.api.event.events.ObjectListEvent;

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
