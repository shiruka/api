package io.github.shiruka.api.nbt;

import io.github.shiruka.api.nbt.array.ByteArrayTag;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine primitive int container tags.
 *
 * @param <S> type of the implementation.
 */
public interface IntContainerTag<S extends IntContainerTag<S>> extends Tag {

  /**
   * checks if the container tag has the given tag.
   *
   * @param tag the tag to check.
   *
   * @return {@code true} if the container tag has the given tag.
   */
  boolean contains(@NotNull Tag tag);

  /**
   * checks if the container tag has the given key.
   *
   * @param key the key to check.
   *
   * @return {@code true} if the container tag has the given key.
   */
  default boolean containsKey(final int key) {
    return this.contains(Tag.createInt(key));
  }

  /**
   * gets the tag at the given key.
   *
   * @param key the key to get.
   *
   * @return the tag.
   */
  @NotNull
  Optional<Tag> get(int key);

  /**
   * gets the byte from the tag container.
   *
   * @param key the key to get.
   *
   * @return a byte instance from the tag container.
   */
  @NotNull
  default Optional<Byte> getByte(final int key) {
    return this.get(key)
      .filter(Tag::isByte)
      .map(Tag::asByte)
      .map(NumberTag::byteValue);
  }

  /**
   * gets the byte array from the tag container.
   *
   * @param key the key to get.
   *
   * @return a byte array instance from the tag container.
   */
  @NotNull
  default Optional<Byte[]> getByteArray(final int key) {
    return this.get(key)
      .filter(Tag::isByteArray)
      .map(Tag::asByteArray)
      .map(ArrayTag::value);
  }

  /**
   * gets the compound tag from the tag container.
   *
   * @param key the key to get.
   *
   * @return a compound tag instance from the tag container.
   */
  @NotNull
  default Optional<CompoundTag> getCompoundTag(final int key) {
    return this.get(key)
      .filter(Tag::isCompound)
      .map(Tag::asCompound);
  }

  /**
   * gets the double from the tag container.
   *
   * @param key the key to get.
   *
   * @return a double instance from the tag container.
   */
  @NotNull
  default Optional<Double> getDouble(final int key) {
    return this.get(key)
      .filter(Tag::isDouble)
      .map(Tag::asDouble)
      .map(NumberTag::doubleValue);
  }

  /**
   * gets the float from the tag container.
   *
   * @param key the key to get.
   *
   * @return a float instance from the tag container.
   */
  @NotNull
  default Optional<Float> getFloat(final int key) {
    return this.get(key)
      .filter(Tag::isFloat)
      .map(Tag::asFloat)
      .map(NumberTag::floatValue);
  }

  /**
   * gets the int array from the tag container.
   *
   * @param key the key to get.
   *
   * @return a int array instance from the tag container.
   */
  @NotNull
  default Optional<Integer[]> getIntArray(final int key) {
    return this.get(key)
      .filter(Tag::isIntArray)
      .map(Tag::asIntArray)
      .map(ArrayTag::value);
  }

  /**
   * gets the integer from the tag container.
   *
   * @param key the key to get.
   *
   * @return a integer instance from the tag container.
   */
  @NotNull
  default Optional<Integer> getInteger(final int key) {
    return this.get(key)
      .filter(Tag::isInt)
      .map(Tag::asInt)
      .map(NumberTag::intValue);
  }

  /**
   * gets the list from the tag container.
   *
   * @param key the key to get.
   *
   * @return a list instance from the tag container.
   */
  @NotNull
  default Optional<List<Tag>> getList(final int key) {
    return this.getListTag(key)
      .map(ListTag::all);
  }

  /**
   * gets the list from the tag container.
   *
   * @param key the key to get.
   * @param listType the list type to get.
   *
   * @return a list instance from the tag container.
   */
  @NotNull
  default Optional<List<Tag>> getList(final int key, @NotNull final TagTypes listType) {
    return this.getListTag(key, listType)
      .map(ListTag::all);
  }

  /**
   * gets the list tag from the tag container.
   *
   * @param key the key to get.
   *
   * @return a list tag instance from the tag container.
   */
  @NotNull
  default Optional<ListTag> getListTag(final int key) {
    return this.get(key)
      .filter(Tag::isList)
      .map(Tag::asList);
  }

  /**
   * gets the list tag from the tag container.
   *
   * @param key the key to get.
   * @param listType the list type to get.
   *
   * @return a list tag instance from the tag container.
   */
  @NotNull
  default Optional<ListTag> getListTag(final int key, @NotNull final TagTypes listType) {
    return this.get(key)
      .filter(Tag::isList)
      .map(Tag::asList)
      .filter(tags -> tags.getListType() == listType);
  }

  /**
   * gets the long from the tag container.
   *
   * @param key the key to get.
   *
   * @return a long instance from the tag container.
   */
  @NotNull
  default Optional<Long> getLong(final int key) {
    return this.get(key)
      .filter(Tag::isLong)
      .map(Tag::asLong)
      .map(NumberTag::longValue);
  }

  /**
   * gets the long array from the tag container.
   *
   * @param key the key to get.
   *
   * @return a long array instance from the tag container.
   */
  @NotNull
  default Optional<Long[]> getLongArray(final int key) {
    return this.get(key)
      .filter(Tag::isLongArray)
      .map(Tag::asLongArray)
      .map(ArrayTag::value);
  }

  /**
   * gets the map from the tag container.
   *
   * @param key the key to get.
   *
   * @return a map instance from the tag container.
   */
  @NotNull
  default Optional<Map<String, Tag>> getMap(final int key) {
    return this.getCompoundTag(key)
      .map(CompoundTag::all);
  }

  /**
   * gets the primitive byte array from the tag container.
   *
   * @param key the key to get.
   *
   * @return a primitive byte array instance from the tag container.
   */
  @NotNull
  default Optional<byte[]> getPrimitiveByteArray(final int key) {
    return this.get(key)
      .filter(Tag::isByteArray)
      .map(Tag::asByteArray)
      .map(ByteArrayTag::primitiveValue);
  }

  /**
   * gets the short from the tag container.
   *
   * @param key the key to get.
   *
   * @return a short instance from the tag container.
   */
  @NotNull
  default Optional<Short> getShort(final int key) {
    return this.get(key)
      .filter(Tag::isShort)
      .map(Tag::asShort)
      .map(NumberTag::shortValue);
  }

  /**
   * gets the string from the tag container.
   *
   * @param key the key to get.
   *
   * @return a string instance from the tag container.
   */
  @NotNull
  default Optional<String> getString(final int key) {
    return this.get(key)
      .filter(Tag::isString)
      .map(Tag::asString)
      .map(PrimitiveTag::value);
  }

  /**
   * checks if the container tag has not any element in it.
   *
   * @return {@code true} if the container tag has not any element in it.
   */
  boolean isEmpty();

  /**
   * removes the tag at the given key.
   *
   * @param key the key to remove.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  S remove(int key);

  /**
   * sets the given tag into the given key.
   *
   * @param key the key to set.
   * @param tag the tag to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  S set(int key, @NotNull Tag tag);

  /**
   * sets the byte to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setByte(final int key, @NotNull final Byte value) {
    return this.set(key, Tag.createByte(value));
  }

  /**
   * sets the byte array to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setByteArray(final int key, final byte... value) {
    return this.set(key, Tag.createByteArray(value));
  }

  /**
   * sets the double to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setDouble(final int key, @NotNull final Double value) {
    return this.set(key, Tag.createDouble(value));
  }

  /**
   * sets the float to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setFloat(final int key, @NotNull final Float value) {
    return this.set(key, Tag.createFloat(value));
  }

  /**
   * sets the int array to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setIntArray(final int key, final int... value) {
    return this.set(key, Tag.createIntArray(value));
  }

  /**
   * sets the integer to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setInteger(final int key, @NotNull final Integer value) {
    return this.set(key, Tag.createInt(value));
  }

  /**
   * sets the list to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setList(final int key, @NotNull final List<Tag> value) {
    return this.set(key, Tag.createList(value));
  }

  /**
   * sets the long to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setLong(final int key, @NotNull final Long value) {
    return this.set(key, Tag.createLong(value));
  }

  /**
   * sets the long array to the tag container.
   *
   * @param key the key to set.
   * @param value the value set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setLongArray(final int key, final long @NotNull [] value) {
    return this.set(key, Tag.createLongArray(value));
  }

  /**
   * sets the map to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setMap(final int key, @NotNull final Map<String, Tag> value) {
    return this.set(key, Tag.createCompound(value));
  }

  /**
   * sets the short to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setShort(final int key, @NotNull final Short value) {
    return this.set(key, Tag.createShort(value));
  }

  /**
   * sets the string to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setString(final int key, @NotNull final String value) {
    return this.set(key, Tag.createString(value));
  }

  /**
   * gets the size.
   *
   * @return the size.
   */
  int size();
}
