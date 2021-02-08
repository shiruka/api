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

package net.shiruka.api.plugin.java;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import net.shiruka.api.plugin.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.configuration.serialization.ConfigurationSerializable;
import org.simpleyaml.configuration.serialization.ConfigurationSerialization;

/**
 * a class that represents Java plugin loader.
 */
public final class JavaPluginLoader implements PluginLoader {

  /**
   * the disable class prioritization.
   */
  private static final boolean DISABLE_CLASS_PRIORITIZATION = Boolean.getBoolean("Shiruka.DisableClassPrioritization");

  /**
   * the class load lock.
   */
  private final Map<String, ReentrantReadWriteLock> classLoadLock = new Object2ObjectOpenHashMap<>();

  /**
   * the class load lock count.
   */
  private final Map<String, Integer> classLoadLockCount = new Object2ObjectOpenHashMap<>();

  /**
   * the classes.
   */
  private final Map<String, Class<?>> classes = new ConcurrentHashMap<>();

  /**
   * the loaders.
   */
  private final List<JavaPluginClassLoader> loaders = new CopyOnWriteArrayList<>();

  @Override
  public void disablePlugin(final @NotNull Plugin plugin, final boolean closeClassloader) {
  }

  @Override
  public void enablePlugin(@NotNull final Plugin plugin) {
  }

  @NotNull
  @Override
  public PluginDescriptionFile getPluginDescription(@NotNull final File file) throws InvalidDescriptionException {
    return null;
  }

  @NotNull
  @Override
  public Pattern[] getPluginFileFilters() {
    return new Pattern[0];
  }

  @NotNull
  @Override
  public Plugin loadPlugin(@NotNull final File file) throws InvalidPluginException, UnknownDependencyException {
    return null;
  }

  @Nullable
  Class<?> getClassByName(@NotNull final String name, @Nullable final JavaPluginClassLoader requester) {
    var cachedClass = this.classes.get(name);
    if (cachedClass != null) {
      return cachedClass;
    }
    final ReentrantReadWriteLock lock;
    synchronized (this.classLoadLock) {
      lock = this.classLoadLock.computeIfAbsent(name, x -> new ReentrantReadWriteLock());
      this.classLoadLockCount.compute(name, (x, prev) -> prev != null ? prev + 1 : 1);
    }
    lock.writeLock().lock();
    try {
      if (!JavaPluginLoader.DISABLE_CLASS_PRIORITIZATION && requester != null) {
        try {
          cachedClass = requester.findClass(name, false);
        } catch (final ClassNotFoundException ignored) {
        }
        if (cachedClass != null) {
          return cachedClass;
        }
      }
      cachedClass = this.classes.get(name);
      if (cachedClass != null) {
        return cachedClass;
      }
      for (final var loader : this.loaders) {
        try {
          cachedClass = loader.findClass(name, false);
        } catch (final ClassNotFoundException ignored) {
        }
        if (cachedClass != null) {
          return cachedClass;
        }
      }
    } finally {
      synchronized (this.classLoadLock) {
        lock.writeLock().unlock();
        if (this.classLoadLockCount.get(name) == 1) {
          this.classLoadLock.remove(name);
          this.classLoadLockCount.remove(name);
        } else {
          //noinspection ConstantConditions
          this.classLoadLockCount.compute(name, (x, prev) -> prev - 1);
        }
      }
    }
    return null;
  }

  /**
   * sets the given {@code name} to the given {@code clazz}.
   *
   * @param name the name to set.
   * @param clazz the clazz to set.
   */
  void setClass(@NotNull final String name, @NotNull final Class<?> clazz) {
    if (!this.classes.containsKey(name)) {
      this.classes.put(name, clazz);
      if (ConfigurationSerializable.class.isAssignableFrom(clazz)) {
        final Class<? extends ConfigurationSerializable> serializable = clazz.asSubclass(ConfigurationSerializable.class);
        ConfigurationSerialization.registerClass(serializable);
      }
    }
  }
}
