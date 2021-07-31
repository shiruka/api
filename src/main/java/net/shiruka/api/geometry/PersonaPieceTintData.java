package net.shiruka.api.geometry;

import java.util.List;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents persona piece tint data.
 */
@Value(staticConstructor = "of")
public class PersonaPieceTintData {

  /**
   * the colors.
   */
  @NotNull
  List<String> colors;

  /**
   * the type.
   */
  @NotNull
  String type;
}
