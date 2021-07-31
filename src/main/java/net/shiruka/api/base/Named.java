package net.shiruka.api.base;

import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine named classes.
 */
public interface Named {

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  Text getName();
}
