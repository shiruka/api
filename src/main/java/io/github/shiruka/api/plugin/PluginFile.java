/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
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

import io.github.shiruka.api.misc.Optionals;
import java.io.InputStream;
import java.io.Writer;
import java.util.*;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

/**
 * a file interface to describes the plugins.
 */
public final class PluginFile {

  /**
   * the name key of the plugin.yml
   */
  private static final String NAME = "name";

  /**
   * the main class key of the plugin.yml
   */
  private static final String MAIN = "main";

  /**
   * the version key of the plugin.yml
   */
  private static final String VERSION = "version";

  /**
   * the description key of the plugin.yml
   */
  private static final String DESCRIPTION = "description";

  /**
   * the website key of the plugin.yml
   */
  private static final String WEBSITE = "website";

  /**
   * the prefix key of the plugin.yml
   */
  private static final String PREFIX = "prefi";

  /**
   * the order key of the plugin.yml
   */
  private static final String ORDER = "order";

  /**
   * the contributors of the plugin.yml.
   */
  private static final String CONTRIBUTORS = "contributors";

  /**
   * the authors key of the plugin.yml.
   */
  private static final String AUTHORS = "authors";

  /**
   * the load key of the plugin.yml
   */
  private static final String LOAD = "load";

  /**
   * the depend key of the plugin.yml
   */
  private static final String DEPEND = "depend";

  /**
   * the soft depend key of the plugin.yml
   */
  private static final String SOFT_DEPEND = "softdepend";

  /**
   * the load before key of the plugin.yml
   */
  private static final String LOAD_BEFORE = "loadbefore";

  /**
   * validator pattern for plugin names.
   */
  private static final Pattern VALID_NAME = Pattern.compile("^[A-Za-z0-9 _.-]+$");

  /**
   * a {@link Yaml} instance to determine plugin.yml.
   */
  private static final ThreadLocal<Yaml> YAML = ThreadLocal.withInitial(() ->
    new Yaml(new SafeConstructor()));

  /**
   * the name of the plugin.
   */
  @NotNull
  private final String name;

  /**
   * the main class of the plugin.
   */
  @NotNull
  private final String main;

  /**
   * the version of the plugin.
   */
  @NotNull
  private final String version;

  /**
   * the description of the plugin.
   */
  @NotNull
  private final String description;

  /**
   * the website of the plugin.
   */
  @NotNull
  private final String website;

  /**
   * the prefix of the plugin.
   */
  @NotNull
  private final String prefix;

  /**
   * the load order of the plugin.
   * <p>
   * the default is {@link PluginLoadOrder#POST_WORLD}.
   */
  @NotNull
  private final PluginLoadOrder order;

  /**
   * the contributors of the plugin.
   */
  @NotNull
  private final List<String> contributors;

  /**
   * the authors the plugin.
   */
  @NotNull
  private final List<String> authors;

  /**
   * the dependency of the plugin.
   */
  @NotNull
  private final List<String> depend;

  /**
   * the soft dependency of the plugin.
   */
  @NotNull
  private final List<String> softDepend;

  /**
   * the plugin list which have to load before the plugin.
   */
  @NotNull
  private final List<String> loadBefore;

  /**
   * ctor.
   *
   * @param name the name.
   * @param main the main.
   * @param version the version.
   * @param description the description.
   * @param website the website.
   * @param prefix the prefix.
   * @param order the order.
   * @param contributors the contributors.
   * @param authors the authors.
   * @param depend the depend.
   * @param softDepend the soft dependency.
   * @param loadBefore the load before.
   */
  private PluginFile(@NotNull final String name, @NotNull final String main,
                     @NotNull final String version, @NotNull final String description, @NotNull final String website,
                     @NotNull final String prefix, @NotNull final PluginLoadOrder order,
                     @NotNull final List<String> contributors, @NotNull final List<String> authors,
                     @NotNull final List<String> depend, @NotNull final List<String> softDepend,
                     @NotNull final List<String> loadBefore) {
    this.name = name;
    this.main = main;
    this.version = version;
    this.description = description;
    this.website = website;
    this.prefix = prefix;
    this.order = order;
    this.contributors = contributors;
    this.authors = authors;
    this.depend = depend;
    this.softDepend = softDepend;
    this.loadBefore = loadBefore;
  }

  /**
   * creates and returns a new plugin file instance from the input stream.
   *
   * @param name the name to create.
   * @param version the version to create.
   * @param main the main to create.
   *
   * @return a new instance of plugin file.
   *
   * @throws InvalidDescriptionException if something went wrong in the plugin.yml file.
   */
  @NotNull
  public static PluginFile init(@NotNull final String name, @NotNull final String version,
                                @NotNull final String main) throws InvalidDescriptionException {
    return PluginFile.init(Optionals.useAndGet(new HashMap<>(), map -> {
      map.put(PluginFile.NAME, name);
      map.put(PluginFile.VERSION, version);
      map.put(PluginFile.MAIN, main);
    }));
  }

  /**
   * creates and returns a new plugin file instance from the input stream.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of plugin file.
   *
   * @throws InvalidDescriptionException if something went wrong in the plugin.yml file.
   */
  @NotNull
  public static PluginFile init(@NotNull final InputStream stream) throws InvalidDescriptionException {
    return PluginFile.init(PluginFile.YAML.get().<Map<String, Object>>load(stream));
  }

  /**
   * creates and returns a new plugin file instance from the map.
   *
   * @param map the map to create.
   *
   * @return a new instance of plugin file.
   *
   * @throws InvalidDescriptionException if something went wrong in the plugin.yml file.
   */
  @NotNull
  public static PluginFile init(@NotNull final Map<String, Object> map) throws InvalidDescriptionException {
    String name;
    try {
      name = map.get(PluginFile.NAME).toString();
      if (!PluginFile.VALID_NAME.matcher(name).matches()) {
        throw new InvalidDescriptionException("name '" + name + "' contains invalid characters.");
      }
      name = name.replace(' ', '_');
    } catch (final NullPointerException ex) {
      throw new InvalidDescriptionException(ex, "name is not defined");
    } catch (final ClassCastException ex) {
      throw new InvalidDescriptionException(ex, "name is of wrong type");
    }
    final String version;
    try {
      version = map.get(PluginFile.VERSION).toString();
    } catch (final NullPointerException ex) {
      throw new InvalidDescriptionException(ex, "version is not defined");
    } catch (final ClassCastException ex) {
      throw new InvalidDescriptionException(ex, "version is of wrong type");
    }
    final String main;
    try {
      main = map.get(PluginFile.MAIN).toString();
      if (main.startsWith("io.github.shiruka.")) {
        throw new InvalidDescriptionException("main may not be within the org.bukkit namespace");
      }
    } catch (final NullPointerException ex) {
      throw new InvalidDescriptionException(ex, "main is not defined");
    } catch (final ClassCastException ex) {
      throw new InvalidDescriptionException(ex, "main is of wrong type");
    }
    final var website = map.getOrDefault(PluginFile.WEBSITE, "").toString();
    final var description = map.getOrDefault(PluginFile.DESCRIPTION, "").toString();
    final var prefix = map.getOrDefault(PluginFile.PREFIX, "").toString();
    final var contributors = new ArrayList<String>();
    if (map.containsKey(PluginFile.CONTRIBUTORS)) {
      try {
        for (final var o : (Iterable<?>) map.get(PluginFile.CONTRIBUTORS)) {
          contributors.add(o.toString());
        }
      } catch (final ClassCastException ex) {
        throw new InvalidDescriptionException(ex, "contributors are of wrong type");
      }
    }
    final var authors = new ArrayList<String>();
    if (map.containsKey(PluginFile.AUTHORS)) {
      try {
        for (final var o : (Iterable<?>) map.get(PluginFile.AUTHORS)) {
          authors.add(o.toString());
        }
      } catch (final ClassCastException ex) {
        throw new InvalidDescriptionException(ex, "authors are of wrong type");
      } catch (final NullPointerException ex) {
        throw new InvalidDescriptionException(ex, "authors are improperly defined");
      }
    }
    final PluginLoadOrder order;
    if (map.containsKey(PluginFile.LOAD)) {
      try {
        order = PluginLoadOrder.valueOf(((String) map.get(PluginFile.LOAD))
          .toUpperCase(Locale.ENGLISH)
          .replaceAll("\\W", ""));
      } catch (final ClassCastException ex) {
        throw new InvalidDescriptionException(ex, "load is of wrong type");
      } catch (final IllegalArgumentException ex) {
        throw new InvalidDescriptionException(ex, "load is not a valid choice");
      }
    } else {
      order = PluginLoadOrder.POST_WORLD;
    }
    final var depend = PluginFile.makePluginNameList(map, PluginFile.DEPEND);
    final var softDepend = PluginFile.makePluginNameList(map, PluginFile.SOFT_DEPEND);
    final var loadBefore = PluginFile.makePluginNameList(map, PluginFile.LOAD_BEFORE);
    return new PluginFile(name, main, version, description, website, prefix, order, contributors, authors,
      depend, softDepend, loadBefore);
  }

  /**
   * creates and returns a list of plugin names from the given map and map-key.
   *
   * @param map the map to create.
   * @param key the key to create.
   *
   * @return a plugin name list.
   *
   * @throws InvalidDescriptionException if the key's value in the map has wrong class type or invalid type.
   */
  @NotNull
  private static List<String> makePluginNameList(@NotNull final Map<?, ?> map,
                                                 @NotNull final String key) throws InvalidDescriptionException {
    if (!map.containsKey(key)) {
      return Collections.emptyList();
    }
    final var list = new ArrayList<String>();
    try {
      for (final var entry : (Iterable<?>) map.get(key)) {
        list.add(entry.toString().replace(' ', '_'));
      }
    } catch (final ClassCastException ex) {
      throw new InvalidDescriptionException(ex, key + " is of wrong type");
    } catch (final NullPointerException ex) {
      throw new InvalidDescriptionException(ex, "invalid " + key + " format");
    }
    return list;
  }

  /**
   * saves {@code this} to the given writer.
   *
   * @param writer the writer to save.
   */
  public void save(@NotNull final Writer writer) {
    PluginFile.YAML.get().dump(this.saveMap(), writer);
  }

  /**
   * creates a map which has {@code this}'s values.
   *
   * @return a map that contains plugin file's values.
   */
  @NotNull
  private Map<String, Object> saveMap() {
    final var map = new HashMap<String, Object>();
    map.put(PluginFile.NAME, this.name);
    map.put(PluginFile.MAIN, this.main);
    map.put(PluginFile.VERSION, this.version);
    map.put(PluginFile.ORDER, this.order.toString());
    map.put(PluginFile.DEPEND, this.depend);
    map.put(PluginFile.SOFT_DEPEND, this.softDepend);
    map.put(PluginFile.LOAD_BEFORE, this.loadBefore);
    map.put(PluginFile.WEBSITE, this.website);
    map.put(PluginFile.DESCRIPTION, this.description);
    map.put(PluginFile.AUTHORS, this.authors);
    map.put(PluginFile.CONTRIBUTORS, this.contributors);
    map.put(PluginFile.PREFIX, this.prefix);
    return map;
  }
}
