package net.shiruka.api.old.event.events.player;

import net.shiruka.api.old.event.events.KickMessageEvent;
import net.shiruka.api.old.event.events.LoginEvent;
import net.shiruka.api.old.event.events.LoginResultEvent;
import net.shiruka.api.old.event.events.PlayerEvent;

/**
 * a class that represents player login events.
 */
public interface PlayerLoginEvent extends LoginEvent, PlayerEvent, LoginResultEvent, KickMessageEvent {

}
