package net.shiruka.api.old.event.events;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine object list events.
 *
 * @param <O> type of the object.
 */
public interface ObjectListEvent<O> {

  /**
   * adds the given object.
   *
   * @param object the object to add.
   */
  void addObject(@NotNull O object);

  /**
   * obtains the object list.
   *
   * @return list of object.
   */
  @NotNull
  List<O> getObjects();

  /**
   * removes the given object.
   *
   * @param object the object to remove.
   */
  void removeObject(@NotNull O object);
}
