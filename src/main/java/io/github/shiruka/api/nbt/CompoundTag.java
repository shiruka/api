package io.github.shiruka.api.nbt;

import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine compound tags which contain map of {@link Tag}.
 */
public interface CompoundTag extends Tag, ContainerTag<String, CompoundTag> {

  /**
   * checks if the given {@code type} is a number.
   *
   * @param type the type to check.
   *
   * @return {@code true} if the type is a number.
   */
  private static boolean isNumber(@NotNull final TagTypes type) {
    return type == TagTypes.BYTE ||
      type == TagTypes.SHORT ||
      type == TagTypes.INT ||
      type == TagTypes.LONG ||
      type == TagTypes.FLOAT ||
      type == TagTypes.DOUBLE;
  }

  /**
   * obtains compound tag's map as unmodifiable..
   *
   * @return compound tag's map.
   */
  @NotNull
  Map<String, Tag> all();

  @NotNull
  @Override
  default CompoundTag asCompound() {
    return this;
  }

  @NotNull
  @Override
  default TagTypes getType() {
    return TagTypes.COMPOUND;
  }

  @Override
  default boolean isCompound() {
    return true;
  }

  /**
   * checks if the given {@code key} contains and the id of the key's value equals the given {@code id}.
   *
   * @param key the key to check.
   * @param id the id to check.
   *
   * @return {@code true} if the id of the key's value equals the given {@code id}.
   */
  default boolean hasKeyOfType(@NotNull final String key, @NotNull final TagTypes id) {
    final var type = this.get(key).map(Tag::getType).orElse(TagTypes.END);
    return id == type || id.getId() == 99 && CompoundTag.isNumber(type);
  }

  @Override
  default boolean isEmpty() {
    return this.all().isEmpty();
  }
}
