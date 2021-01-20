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

import java.util.Locale;
import net.shiruka.api.base.Keyed;
import net.shiruka.api.base.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains biomes of the Minecraft.
 */
public enum Biome implements Keyed {
  /**
   * the ocean.
   */
  OCEAN,
  /**
   * the plains.
   */
  PLAINS,
  /**
   * the desert.
   */
  DESERT,
  /**
   * the mountains.
   */
  MOUNTAINS,
  /**
   * the forest.
   */
  FOREST,
  /**
   * the taiga.
   */
  TAIGA,
  /**
   * the swamp.
   */
  SWAMP,
  /**
   * the river.
   */
  RIVER,
  /**
   * the nether wastes.
   */
  NETHER_WASTES,
  /**
   * the end.
   */
  THE_END,
  /**
   * the frozen ocean.
   */
  FROZEN_OCEAN,
  /**
   * the frozen river.
   */
  FROZEN_RIVER,
  /**
   * the snowy tundra.
   */
  SNOWY_TUNDRA,
  /**
   * the snowy mountains.
   */
  SNOWY_MOUNTAINS,
  /**
   * the mushroom fields.
   */
  MUSHROOM_FIELDS,
  /**
   * the mushroom field shore.
   */
  MUSHROOM_FIELD_SHORE,
  /**
   * the beach.
   */
  BEACH,
  /**
   * the desert hills.
   */
  DESERT_HILLS,
  /**
   * the wooded hills.
   */
  WOODED_HILLS,
  /**
   * the taiga hills.
   */
  TAIGA_HILLS,
  /**
   * the mountain edge.
   */
  MOUNTAIN_EDGE,
  /**
   * the jungle.
   */
  JUNGLE,
  /**
   * the jungle hills.
   */
  JUNGLE_HILLS,
  /**
   * the jungle edge.
   */
  JUNGLE_EDGE,
  /**
   * the deep ocean.
   */
  DEEP_OCEAN,
  /**
   * the stone shore.
   */
  STONE_SHORE,
  /**
   * the snow beach.
   */
  SNOWY_BEACH,
  /**
   * the birch forest.
   */
  BIRCH_FOREST,
  /**
   * the birch forest hills.
   */
  BIRCH_FOREST_HILLS,
  /**
   * the dark forest.
   */
  DARK_FOREST,
  /**
   * the snow taiga.
   */
  SNOWY_TAIGA,
  /**
   * the snowy taiga hills.
   */
  SNOWY_TAIGA_HILLS,
  /**
   * the giant tree taiga.
   */
  GIANT_TREE_TAIGA,
  /**
   * the giant tree taiga hilss.
   */
  GIANT_TREE_TAIGA_HILLS,
  /**
   * the wooded mountains.
   */
  WOODED_MOUNTAINS,
  /**
   * the savanna.
   */
  SAVANNA,
  /**
   * the savanna plateau.
   */
  SAVANNA_PLATEAU,
  /**
   * the badlands.
   */
  BADLANDS,
  /**
   * the wooded badlands plateau.
   */
  WOODED_BADLANDS_PLATEAU,
  /**
   * the badlands plateau.
   */
  BADLANDS_PLATEAU,
  /**
   * the small end islands.
   */
  SMALL_END_ISLANDS,
  /**
   * the end midlands.
   */
  END_MIDLANDS,
  /**
   * the end highlands.
   */
  END_HIGHLANDS,
  /**
   * the end barrens.
   */
  END_BARRENS,
  /**
   * the warm ocean.
   */
  WARM_OCEAN,
  /**
   * the lukewarm ocean.
   */
  LUKEWARM_OCEAN,
  /**
   * the cold ocean.
   */
  COLD_OCEAN,
  /**
   * the deep warm ocean.
   */
  DEEP_WARM_OCEAN,
  /**
   * the deep lukewarm ocean.
   */
  DEEP_LUKEWARM_OCEAN,
  /**
   * the deep cold ocean.
   */
  DEEP_COLD_OCEAN,
  /**
   * the deep frozen ocean.
   */
  DEEP_FROZEN_OCEAN,
  /**
   * the void.
   */
  THE_VOID,
  /**
   * the sunflower plains.
   */
  SUNFLOWER_PLAINS,
  /**
   * the deset lakes.
   */
  DESERT_LAKES,
  /**
   * the gravelly mountains.
   */
  GRAVELLY_MOUNTAINS,
  /**
   * the flower forest.
   */
  FLOWER_FOREST,
  /**
   * the taiga mountains.
   */
  TAIGA_MOUNTAINS,
  /**
   * the swamp hills.
   */
  SWAMP_HILLS,
  /**
   * the ice spikes.
   */
  ICE_SPIKES,
  /**
   * the modified jungle.
   */
  MODIFIED_JUNGLE,
  /**
   * the modified jungle edge.
   */
  MODIFIED_JUNGLE_EDGE,
  /**
   * the tall birch forest.
   */
  TALL_BIRCH_FOREST,
  /**
   * the tall birch hills.
   */
  TALL_BIRCH_HILLS,
  /**
   * the dar forest hills.
   */
  DARK_FOREST_HILLS,
  /**
   * the snowy taiga mountains.
   */
  SNOWY_TAIGA_MOUNTAINS,
  /**
   * the giant spruce taiga.
   */
  GIANT_SPRUCE_TAIGA,
  /**
   * the giant spruce taiga hills.
   */
  GIANT_SPRUCE_TAIGA_HILLS,
  /**
   * the modified gravelly mountains.
   */
  MODIFIED_GRAVELLY_MOUNTAINS,
  /**
   * the shattered savanna.
   */
  SHATTERED_SAVANNA,
  /**
   * the shattered savanna plateau.
   */
  SHATTERED_SAVANNA_PLATEAU,
  /**
   * the eroded badlands.
   */
  ERODED_BADLANDS,
  /**
   * the modified wooded badlands plateau.
   */
  MODIFIED_WOODED_BADLANDS_PLATEAU,
  /**
   * the modified badlands plateau.
   */
  MODIFIED_BADLANDS_PLATEAU,
  /**
   * the bamboo jungle.
   */
  BAMBOO_JUNGLE,
  /**
   * the bamboo jungle hills.
   */
  BAMBOO_JUNGLE_HILLS,
  /**
   * the sould sand valley.
   */
  SOUL_SAND_VALLEY,
  /**
   * the crimson forest.
   */
  CRIMSON_FOREST,
  /**
   * the warped forest.
   */
  WARPED_FOREST,
  /**
   * the basalt deltas.
   */
  BASALT_DELTAS;

  /**
   * the key.
   */
  @NotNull
  private final NamespacedKey key;

  /**
   * ctor.
   */
  Biome() {
    this.key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));
  }

  @NotNull
  @Override
  public NamespacedKey getKey() {
    return this.key;
  }
}
