/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package net.shiruka.api.world;

import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.shiruka.api.base.AxisAlignedBoundingBox;
import net.shiruka.api.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine collision accessor objects.
 */
public interface CollisionAccessor {

  /**
   * obtains the block collisions.
   *
   * @param entity the entity to obtain.
   * @param boundingBox the bounding box to obtain.
   *
   * @return obtained block collisions.
   */
  @NotNull
  Stream<VoxelShape> getBlockCollisions(@Nullable Entity entity, @NotNull AxisAlignedBoundingBox boundingBox);

  /**
   * obtains the collisions.
   *
   * @param entity the entity to obtains.
   * @param boundingBox the bounding box to obtains.
   * @param predicate the predicate to obtains.
   *
   * @return obtained collisions.
   */
  @NotNull
  default Stream<VoxelShape> getCollisions(@Nullable final Entity entity,
                                           @NotNull final AxisAlignedBoundingBox boundingBox,
                                           @NotNull final Predicate<Entity> predicate) {
    return Stream.concat(
      this.getBlockCollisions(entity, boundingBox),
      this.getEntityCollisions(entity, boundingBox, predicate));
  }

  /**
   * obtains the entity collisions.
   *
   * @param entity the entity to obtains.
   * @param boundingBox the bounding box to obtains.
   * @param predicate the predicate to obtains.
   *
   * @return obtained entity collisions.
   */
  @NotNull
  Stream<VoxelShape> getEntityCollisions(@Nullable Entity entity, @NotNull AxisAlignedBoundingBox boundingBox,
                                         @NotNull Predicate<Entity> predicate);

  /**
   * checks the collision.
   *
   * @param entity the entity to check.
   *
   * @return {@code false} if the entity is within a something.
   */
  default boolean noCollision(@NotNull final Entity entity) {
    return this.noCollision(entity, entity.getBoundingBox(), e -> true);
  }

  /**
   * checks the collision.
   *
   * @param entity the entity to check.
   * @param boundingBox the bounding box to check.
   * @param predicate the predicate to check.
   *
   * @return {@code false} if the entity is within a something.
   */
  default boolean noCollision(@Nullable final Entity entity, @NotNull final AxisAlignedBoundingBox boundingBox,
                              @NotNull final Predicate<Entity> predicate) {
    try {
      if (entity != null) {
        entity.activateCollisionLoadChunks();
      }
      return this.getCollisions(entity, boundingBox, predicate)
        .allMatch(VoxelShape::isEmpty);
    } finally {
      if (entity != null) {
        entity.deactivateCollisionLoadChunks();
      }
    }
  }

  /**
   * checks the collision.
   *
   * @param boundingBox the bounding box to check.
   *
   * @return {@code false} if the entity is within a something.
   */
  default boolean noCollision(@NotNull final AxisAlignedBoundingBox boundingBox) {
    return this.noCollision(null, boundingBox, e -> true);
  }
}
