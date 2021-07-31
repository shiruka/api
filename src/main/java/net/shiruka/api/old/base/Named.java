package net.shiruka.api.old.base;

import net.shiruka.api.old.text.Text;
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
