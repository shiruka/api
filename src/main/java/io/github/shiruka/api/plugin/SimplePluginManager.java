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

package io.github.shiruka.api.plugin;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;
import java.io.File;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * a simple implementation for {@link PluginManager}.
 */
public final class SimplePluginManager implements PluginManager {

  /**
   * the dependency graph.
   */
  private final MutableGraph<String> dependencyGraph = GraphBuilder.directed().build();

  @Override
  public void clearPlugins() {
  }

  @Override
  public void disablePlugin(@NotNull final Plugin plugin) {
  }

  @Override
  public void disablePlugin(@NotNull final Plugin plugin, final boolean closeClassloader) {
  }

  @Override
  public void disablePlugins() {
  }

  @Override
  public void enablePlugin(@NotNull final Plugin plugin) {
  }

  @NotNull
  @Override
  public Optional<Plugin> getPlugin(@NotNull final String name) {
    return Optional.empty();
  }

  @NotNull
  @Override
  public Plugin[] getPlugins() {
    return new Plugin[0];
  }

  @Override
  public boolean isPluginEnabled(@NotNull final String name) {
    return false;
  }

  @Override
  public boolean isPluginEnabled(@NotNull final Plugin plugin) {
    return false;
  }

  @NotNull
  @Override
  public Optional<Plugin> loadPlugin(@NotNull final File file) throws InvalidPluginException,
    InvalidDescriptionException, UnknownDependencyException {
    return Optional.empty();
  }

  @NotNull
  @Override
  public Plugin[] loadPlugins(@NotNull final File directory) {
    return new Plugin[0];
  }

  /**
   * checks if the given {@code plugin} is transitive depend with the given {@code depend}.
   *
   * @param plugin the plugin to check.
   * @param depend the depend to check.
   *
   * @return {@code true} if the plugin is transitive depend with the given {@code depend}.
   */
  public boolean isTransitiveDepend(@NotNull final PluginDescriptionFile plugin,
                                    @NotNull final PluginDescriptionFile depend) {
    return this.dependencyGraph.nodes().contains(plugin.getName()) &&
      (Graphs.reachableNodes(this.dependencyGraph, plugin.getName()).contains(depend.getName()) ||
        depend.getProvides().stream()
          .anyMatch(provided -> Graphs.reachableNodes(this.dependencyGraph, plugin.getName()).contains(provided)));
  }
}
