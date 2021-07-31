package net.shiruka.api.old.geometry;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents persona piece data.
 */
@Value(staticConstructor = "of")
public class PersonaPieceData {

  /**
   * the id.
   */
  @NotNull
  String id;

  /**
   * the is default.
   */
  boolean isDefault;

  /**
   * the pack id.
   */
  @NotNull
  String packId;

  /**
   * the product id.
   */
  @NotNull
  String productId;

  /**
   * the type.
   */
  @NotNull
  String type;
}
