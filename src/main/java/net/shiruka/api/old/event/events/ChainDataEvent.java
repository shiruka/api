package net.shiruka.api.old.event.events;

import net.shiruka.api.old.base.ChainData;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine events that have chain data value.
 */
public interface ChainDataEvent extends Event {

  /**
   * obtains the chain data.
   *
   * @return chain data.
   */
  @NotNull
  ChainData getChainData();
}
