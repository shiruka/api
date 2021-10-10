package io.github.shiruka.api.nbt;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.nbt.primitive.EndTag;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an enum class that contains type of nbt tags.
 */
@RequiredArgsConstructor
public enum TagTypes {
  /**
   * the none tag.
   */
  NONE(() -> null, -1),
  /**
   * the end tag.
   */
  END(EndTag::new, 0),
  /**
   * the byte tag.
   */
  BYTE(Tag::createByte, 1),
  /**
   * the short tag.
   */
  SHORT(Tag::createShort, 2),
  /**
   * the int tag.
   */
  INT(Tag::createInt, 3),
  /**
   * the long tag.
   */
  LONG(Tag::createLong, 4),
  /**
   * the float tag.
   */
  FLOAT(Tag::createFloat, 5),
  /**
   * the double tag.
   */
  DOUBLE(Tag::createDouble, 6),
  /**
   * the byte array tag.
   */
  BYTE_ARRAY(Tag::createByteArray, 7),
  /**
   * the string tag.
   */
  STRING(Tag::createString, 8),
  /**
   * the list tag.
   */
  LIST(Tag::createList, 9),
  /**
   * the compound tag.
   */
  COMPOUND(Tag::createCompound, 10),
  /**
   * the int array tag.
   */
  INT_ARRAY(Tag::createIntArray, 11),
  /**
   * the long array tag.
   */
  LONG_ARRAY(Tag::createLongArray, 12),
  /**
   * the all tag.
   */
  ALL(() -> null, 99);

  /**
   * the empty tag supplier.
   */
  @NotNull
  private final Supplier<@Nullable Tag> emptyTagSupplier;

  /**
   * the id.
   */
  @Getter
  private final byte id;

  /**
   * ctor.
   *
   * @param emptyTagSupplier the empty tag supplier.
   * @param id the id.
   */
  TagTypes(@NotNull final Supplier<@Nullable Tag> emptyTagSupplier, final int id) {
    this(emptyTagSupplier, (byte) id);
  }

  /**
   * obtains an empty tag.
   *
   * @return empty tag.
   */
  @NotNull
  public Tag emptyTag() {
    return Preconditions.checkNotNull(this.emptyTagSupplier.get(), "%s", this.id);
  }
}
