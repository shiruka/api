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

package io.github.shiruka.api.world;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.command.CommandSender;
import java.util.Optional;
import java.util.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents various types of options that may be used to create a world.
 */
public final class WorldCreator {

  /**
   * the name.
   */
  @NotNull
  private final String name;

  /**
   * the environment.
   */
  @NotNull
  private Environment environment = Environment.NORMAL;

  /**
   * the generate structures.
   */
  private boolean generateStructures = true;

  /**
   * the generator.
   */
  @Nullable
  private ChunkGenerator generator;

  /**
   * the genrator settings.
   */
  private String generatorSettings = "";

  /**
   * the hardcore.
   */
  private boolean hardcore;

  /**
   * the seed.
   */
  private long seed;

  /**
   * the type.
   */
  private WorldType type = WorldType.NORMAL;

  /**
   * ctor.
   *
   * @param name the name.
   */
  public WorldCreator(@NotNull final String name) {
    this.name = name;
    this.seed = new Random().nextLong();
  }

  /**
   * attempts to get the {@link ChunkGenerator} with the given name.
   *
   * @param world the name to create.
   * @param name the name to create.
   * @param output the output to get.
   *
   * @return resulting generator.
   */
  @NotNull
  public static Optional<ChunkGenerator> getGeneratorForName(@NotNull final String world, @Nullable final String name,
                                                             @NotNull final CommandSender output) {
    if (name == null) {
      return Optional.empty();
    }
    final var split = name.split(":", 2);
    final var id = split.length > 1 ? split[1] : null;
    final var pluginOptional = Shiruka.getPluginManager().getPlugin(split[0]);
    if (pluginOptional.isEmpty()) {
      output.sendMessage(String.format("Could not set generator for world '%s': Plugin '%s' does not exist",
        world, split[0]));
      return Optional.empty();
    }
    final var plugin = pluginOptional.get();
    if (!plugin.isEnabled()) {
      output.sendMessage(String.format("Could not set generator for world '%s': Plugin '%s' is not enabled",
        world, plugin.getDescription().getFullName()));
      return Optional.empty();
    }
    return plugin.getDefaultWorldGenerator(world, id);
  }

  /**
   * creates a new world creator instance.
   *
   * @param name the name to create.
   *
   * @return resulting world creator instance.
   */
  @NotNull
  public static WorldCreator name(@NotNull final String name) {
    return new WorldCreator(name);
  }

  /**
   * copies the options from the specified world.
   *
   * @param world the world to copy.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator copy(@NotNull final World world) {
    this.seed = world.getSeed();
    this.environment = world.getEnvironment();
    this.generator = world.getGenerator().orElse(null);
    this.type = world.getDefaultWorldType();
    this.generateStructures = world.canGenerateStructures();
    this.hardcore = world.isHardcore();
    return this;
  }

  /**
   * copies the options from the specified {@code this}.
   *
   * @param creator the creator to copy.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator copy(@NotNull final WorldCreator creator) {
    this.seed = creator.seed();
    this.environment = creator.environment();
    this.generator = creator.generator();
    this.type = creator.type();
    this.generateStructures = creator.generateStructures();
    this.generatorSettings = creator.generatorSettings();
    this.hardcore = creator.hardcore();
    return this;
  }

  /**
   * creates a world with the specified options.
   *
   * @return a newly created or loaded world.
   */
  @NotNull
  public Optional<World> createWorld() {
    return Shiruka.getWorldManager().createWorld(this);
  }

  /**
   * obtains the environment.
   *
   * @return environment.
   */
  @NotNull
  public Environment environment() {
    return this.environment;
  }

  /**
   * sets the environment.
   *
   * @param environment the environment to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator environment(@NotNull final Environment environment) {
    this.environment = environment;
    return this;
  }

  /**
   * sets the generate structures.
   *
   * @param generateStructures the generate structures to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator generateStructures(final boolean generateStructures) {
    this.generateStructures = generateStructures;
    return this;
  }

  /**
   * obtains the generate structures.
   *
   * @return generate structures.
   */
  public boolean generateStructures() {
    return this.generateStructures;
  }

  /**
   * obtains the generator.
   *
   * @return generator.
   */
  @Nullable
  public ChunkGenerator generator() {
    return this.generator;
  }

  /**
   * sets the generator.
   *
   * @param generator the generator to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator generator(@Nullable final ChunkGenerator generator) {
    this.generator = generator;
    return this;
  }

  /**
   * sets the generator by name.
   *
   * @param generator the name to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator generator(@Nullable final String generator) {
    return this.generator(generator, Shiruka.getConsoleCommandSender());
  }

  /**
   * sets the generator by name.
   *
   * @param generator the generator to set.
   * @param output the output to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator generator(@Nullable final String generator, @NotNull final CommandSender output) {
    this.generator = WorldCreator.getGeneratorForName(this.name, generator, output)
      .orElse(this.generator);
    return this;
  }

  /**
   * sets the generator settings.
   *
   * @param generatorSettings the generator settings to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator generatorSettings(@NotNull final String generatorSettings) {
    this.generatorSettings = generatorSettings;
    return this;
  }

  /**
   * obtains the generator settings.
   *
   * @return generator settings.
   */
  @NotNull
  public String generatorSettings() {
    return this.generatorSettings;
  }

  /**
   * sets the hardcore.
   *
   * @param hardcore the hardcore to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator hardcore(final boolean hardcore) {
    this.hardcore = hardcore;
    return this;
  }

  /**
   * obtains the hardcore.
   *
   * @return hardcore.
   */
  public boolean hardcore() {
    return this.hardcore;
  }

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  public String name() {
    return this.name;
  }

  /**
   * obtains the seed.
   *
   * @return seed.
   */
  public long seed() {
    return this.seed;
  }

  /**
   * sets the seed.
   *
   * @param seed the seed to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator seed(final long seed) {
    this.seed = seed;
    return this;
  }

  /**
   * obtains the world type.
   *
   * @return world type.
   */
  @NotNull
  public WorldType type() {
    return this.type;
  }

  /**
   * sets the world type.
   *
   * @param type the type to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public WorldCreator type(@NotNull final WorldType type) {
    this.type = type;
    return this;
  }
}
