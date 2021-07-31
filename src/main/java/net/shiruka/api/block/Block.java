package net.shiruka.api.block;

import net.shiruka.api.base.Material;
import net.shiruka.api.metadata.Metadatable;
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
