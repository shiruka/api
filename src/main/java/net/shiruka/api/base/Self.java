package net.shiruka.api.base;

import org.jetbrains.annotations.NotNull;

/**
 * an interface that helps developers to create builder-pattern based classes.
 *
 * @param <T> type of the implementation class.
 */
public interface Self<T> {

  /**
   * obtains itself.
   *
   * @return itself.
   */
  @NotNull
  T getSelf();
}
