package net.shiruka.api.event.events.player;

import net.shiruka.api.event.events.Cancellable;
import net.shiruka.api.event.events.ChainDataEvent;
import net.shiruka.api.event.events.KickMessageEvent;

/**
 * called when the player logs in, before things have been set up.
 */
public interface PlayerPreLoginEvent extends ChainDataEvent, KickMessageEvent, Cancellable {

}
