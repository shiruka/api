package net.shiruka.api.old.entity;

import net.shiruka.api.old.base.Location;
import net.shiruka.api.old.base.Named;
import net.shiruka.api.old.base.Tick;
import net.shiruka.api.old.command.sender.CommandSender;
import net.shiruka.api.old.metadata.Metadatable;
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
