package io.github.shiruka.api.nbt;

import java.util.List;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine list tags which contain list of {@link Tag}.
 */
public interface ListTag extends Tag, IntStoredTag, Iterable<Tag> {

  /**
   * adds the given tag.
   *
   * @param tag the tag to add.
   */
  void add(@NotNull Tag tag);

  /**
   * obtains list tag's list as unmodifiable..
   *
   * @return list tag's list.
   */
  @NotNull
  List<Tag> all();

  @NotNull
  @Override
  default ListTag asList() {
    return this;
  }

  @NotNull
  @Override
  default TagTypes getType() {
    return TagTypes.LIST;
  }

  @Override
  default boolean isList() {
    return true;
  }

  /**
   * obtains list's inside id of the tags.
   *
   * @return list's inside id of the tags
   */
  @NotNull
  TagTypes getListType();

  @Override
  default boolean isEmpty() {
    return this.all().isEmpty();
  }

  @Override
  default int size() {
    return this.all().size();
  }

  /**
   * creates a stream of the tags contained within this list.
   *
   * @return a new stream.
   */
  @NotNull
  Stream<Tag> stream();
}
