package net.shiruka.api.permission;

import net.shiruka.api.entity.Player;

/**
 * an interface to determine an object that may become a server operator, such as a {@link Player}.
 */
public interface ServerOperator {

  /**
   * checks if this object is a server operator.
   *
   * @return {@code true} if this is an operator, otherwise {@code false}.
   */
  boolean isOp();

  /**
   * sets the operator status of this object.
   *
   * @param value the value to set.
   */
  void setOp(boolean value);
}
