package net.shiruka.api.entity;

import net.shiruka.api.base.Location;
import net.shiruka.api.base.Named;
import net.shiruka.api.base.Tick;
import net.shiruka.api.command.sender.CommandSender;
import net.shiruka.api.metadata.Metadatable;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine entities on the Minecraft.
 */
public interface Entity extends CommandSender, Metadatable, Named, Tick {

  /**
   * obtains the entity id.
   *
   * @return entity id.
   */
  long getEntityId();

  /**
   * obtains the location.
   *
   * @return location.
   */
  @NotNull
  Location getLocation();

  /**
   * removes the entity from the server.
   */
  void remove();
}
