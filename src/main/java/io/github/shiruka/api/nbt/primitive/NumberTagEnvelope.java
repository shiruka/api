package io.github.shiruka.api.nbt.primitive;

import io.github.shiruka.api.nbt.NumberTag;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTag}.
 */
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class NumberTagEnvelope implements NumberTag {

  /**
   * the number.
   */
  @NotNull
  @Delegate
  private final Number number;
}
