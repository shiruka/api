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

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import net.shiruka.api.registry.Resourced;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine world managers.
 */
public interface WorldManager {

  /**
   * gets the world by name.
   *
   * @param name the name to get.
   *
   * @return world instance by name.
   */
  @NotNull
  Optional<World> getWorldByName(@NotNull String name);

  /**
   * gets the world by resource.
   *
   * @param resource the resource to get.
   *
   * @return world instance by resource.
   */
  @NotNull
  Optional<World> getWorldByResource(@NotNull Resourced resource);

  /**
   * gets the world by unique id.
   *
   * @param uniqueId the unique to get.
   *
   * @return world instance by unique id.
   */
  @NotNull
  Optional<World> getWorldByUniqueId(@NotNull UUID uniqueId);

  /**
   * obtains all the worlds.
   *
   * @return worlds.
   */
  @NotNull
  Collection<World> getWorlds();
}
