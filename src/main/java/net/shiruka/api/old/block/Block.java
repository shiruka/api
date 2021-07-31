package net.shiruka.api.old.block;

import net.shiruka.api.old.base.Material;
import net.shiruka.api.old.metadata.Metadatable;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine block in Minecraft game.
 */
public interface Block extends Metadatable {

  /**
   * obtains the block type.
   *
   * @return block type.
   */
  @NotNull
  Material getType();
}
