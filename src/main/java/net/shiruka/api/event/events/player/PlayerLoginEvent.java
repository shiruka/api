package net.shiruka.api.event.events.player;

import net.shiruka.api.event.events.KickMessageEvent;
import net.shiruka.api.event.events.LoginEvent;
import net.shiruka.api.event.events.LoginResultEvent;
import net.shiruka.api.event.events.PlayerEvent;

/**
 * a class that represents player login events.
 */
public interface PlayerLoginEvent extends LoginEvent, PlayerEvent, LoginResultEvent, KickMessageEvent {

}
