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
public final class PluginDescriptionFile {

  /**
   * the authors key of the plugin.yml.
   */
  private static final String AUTHORS = "authors";

  /**
   * the contributors of the plugin.yml.
   */
  private static final String CONTRIBUTORS = "contributors";

  /**
   * the depend key of the plugin.yml
   */
  private static final String DEPEND = "depend";

  /**
   * the description key of the plugin.yml
   */
  private static final String DESCRIPTION = "description";

  /**
   * the load key of the plugin.yml
   */
  private static final String LOAD = "load";

  /**
   * the load before key of the plugin.yml
   */
  private static final String LOAD_BEFORE = "loadbefore";

  /**
   * the main class key of the plugin.yml
   */
  private static final String MAIN = "main";

  /**
   * the name key of the plugin.yml
   */
  private static final String NAME = "name";

  /**
   * the order key of the plugin.yml
   */
  private static final String ORDER = "order";

  /**
   * the prefix key of the plugin.yml
   */
  private static final String PREFIX = "prefix";

  /**
   * the soft depend key of the plugin.yml
   */
  private static final String SOFT_DEPEND = "softdepend";

  /**
   * validator pattern for plugin names.
   */
  private static final Pattern VALID_NAME = Pattern.compile("^[A-Za-z0-9 _.-]+$");

  /**
   * the version key of the plugin.yml
   */
  private static final String VERSION = "version";

  /**
   * the website key of the plugin.yml
   */
  private static final String WEBSITE = "website";

  /**
   * a {@link Yaml} instance to determine plugin.yml.
   */
  private static final ThreadLocal<Yaml> YAML = ThreadLocal.withInitial(() ->
    new Yaml(new SafeConstructor()));

  /**
   * the authors the plugin.
   */
  @NotNull
  private final List<String> authors;

  /**
   * the contributors of the plugin.
   */
  @NotNull
  private final List<String> contributors;

  /**
   * the dependency of the plugin.
   */
  @NotNull
  private final List<String> depend;

  /**
   * the description of the plugin.
   */
  @NotNull
  private final String description;

  /**
   * the plugin list which have to load before the plugin.
   */
  @NotNull
  private final List<String> loadBefore;

  /**
   * the main class of the plugin.
   */
  @NotNull
  private final String main;

  /**
   * the name of the plugin.
   */
  @NotNull
  private final String name;

  /**
   * the load order of the plugin.
   * <p>
   * the default is {@link PluginLoadOrder#POST_WORLD}.
   */
  @NotNull
  private final PluginLoadOrder order;

  /**
   * the prefix of the plugin.
   */
  @NotNull
  private final String prefix;

  /**
   * the soft dependency of the plugin.
   */
  @NotNull
  private final List<String> softDepend;

  /**
   * the version of the plugin.
   */
  @NotNull
  private final String version;

  /**
   * the website of the plugin.
   */
  @NotNull
  private final String website;

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
  private PluginDescriptionFile(@NotNull final String name, @NotNull final String main,
                                @NotNull final String version, @NotNull final String description,
                                @NotNull final String website, @NotNull final String prefix,
                                @NotNull final PluginLoadOrder order, @NotNull final List<String> contributors,
                                @NotNull final List<String> authors, @NotNull final List<String> depend,
                                @NotNull final List<String> softDepend, @NotNull final List<String> loadBefore) {
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
   * creates and returns a new plugin description file instance from the input stream.
   *
   * @param name the name to create.
   * @param version the version to create.
   * @param main the main to create.
   *
   * @return a new instance of plugin description file.
   *
   * @throws InvalidDescriptionException if something went wrong in the plugin.yml file.
   */
  @NotNull
  public static PluginDescriptionFile init(@NotNull final String name, @NotNull final String version,
                                           @NotNull final String main) throws InvalidDescriptionException {
    return PluginDescriptionFile.init(Optionals.useAndGet(new HashMap<>(), map -> {
      map.put(PluginDescriptionFile.NAME, name);
      map.put(PluginDescriptionFile.VERSION, version);
      map.put(PluginDescriptionFile.MAIN, main);
    }));
  }

  /**
   * creates and returns a new plugin description file instance from the input stream.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of plugin description file.
   *
   * @throws InvalidDescriptionException if something went wrong in the plugin.yml file.
   */
  @NotNull
  public static PluginDescriptionFile init(@NotNull final InputStream stream) throws InvalidDescriptionException {
    return PluginDescriptionFile.init(PluginDescriptionFile.YAML.get().<Map<String, Object>>load(stream));
  }

  /**
   * creates and returns a new plugin description file instance from the map.
   *
   * @param map the map to create.
   *
   * @return a new instance of plugin description file.
   *
   * @throws InvalidDescriptionException if something went wrong in the plugin.yml file.
   */
  @NotNull
  public static PluginDescriptionFile init(@NotNull final Map<String, Object> map) throws InvalidDescriptionException {
    String name;
    try {
      name = map.get(PluginDescriptionFile.NAME).toString();
      if (!PluginDescriptionFile.VALID_NAME.matcher(name).matches()) {
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
      version = map.get(PluginDescriptionFile.VERSION).toString();
    } catch (final NullPointerException ex) {
      throw new InvalidDescriptionException(ex, "version is not defined");
    } catch (final ClassCastException ex) {
      throw new InvalidDescriptionException(ex, "version is of wrong type");
    }
    final String main;
    try {
      main = map.get(PluginDescriptionFile.MAIN).toString();
      if (main.startsWith("io.github.shiruka.")) {
        throw new InvalidDescriptionException("main may not be within the org.bukkit namespace");
      }
    } catch (final NullPointerException ex) {
      throw new InvalidDescriptionException(ex, "main is not defined");
    } catch (final ClassCastException ex) {
      throw new InvalidDescriptionException(ex, "main is of wrong type");
    }
    final var website = map.getOrDefault(PluginDescriptionFile.WEBSITE, "").toString();
    final var description = map.getOrDefault(PluginDescriptionFile.DESCRIPTION, "").toString();
    final var prefix = map.getOrDefault(PluginDescriptionFile.PREFIX, "").toString();
    final var contributors = new ArrayList<String>();
    if (map.containsKey(PluginDescriptionFile.CONTRIBUTORS)) {
      try {
        for (final var o : (Iterable<?>) map.get(PluginDescriptionFile.CONTRIBUTORS)) {
          contributors.add(o.toString());
        }
      } catch (final ClassCastException ex) {
        throw new InvalidDescriptionException(ex, "contributors are of wrong type");
      }
    }
    final var authors = new ArrayList<String>();
    if (map.containsKey(PluginDescriptionFile.AUTHORS)) {
      try {
        for (final var o : (Iterable<?>) map.get(PluginDescriptionFile.AUTHORS)) {
          authors.add(o.toString());
        }
      } catch (final ClassCastException ex) {
        throw new InvalidDescriptionException(ex, "authors are of wrong type");
      } catch (final NullPointerException ex) {
        throw new InvalidDescriptionException(ex, "authors are improperly defined");
      }
    }
    final PluginLoadOrder order;
    if (map.containsKey(PluginDescriptionFile.LOAD)) {
      try {
        order = PluginLoadOrder.valueOf(((String) map.get(PluginDescriptionFile.LOAD))
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
    final var depend = PluginDescriptionFile.makePluginNameList(map, PluginDescriptionFile.DEPEND);
    final var softDepend = PluginDescriptionFile.makePluginNameList(map, PluginDescriptionFile.SOFT_DEPEND);
    final var loadBefore = PluginDescriptionFile.makePluginNameList(map, PluginDescriptionFile.LOAD_BEFORE);
    return new PluginDescriptionFile(name, main, version, description, website, prefix, order, contributors, authors,
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
    PluginDescriptionFile.YAML.get().dump(this.saveMap(), writer);
  }

  /**
   * creates a map which has {@code this}'s values.
   *
   * @return a map that contains plugin description file's values.
   */
  @NotNull
  private Map<String, Object> saveMap() {
    final var map = new HashMap<String, Object>();
    map.put(PluginDescriptionFile.NAME, this.name);
    map.put(PluginDescriptionFile.MAIN, this.main);
    map.put(PluginDescriptionFile.VERSION, this.version);
    map.put(PluginDescriptionFile.ORDER, this.order.toString());
    map.put(PluginDescriptionFile.DEPEND, this.depend);
    map.put(PluginDescriptionFile.SOFT_DEPEND, this.softDepend);
    map.put(PluginDescriptionFile.LOAD_BEFORE, this.loadBefore);
    map.put(PluginDescriptionFile.WEBSITE, this.website);
    map.put(PluginDescriptionFile.DESCRIPTION, this.description);
    map.put(PluginDescriptionFile.AUTHORS, this.authors);
    map.put(PluginDescriptionFile.CONTRIBUTORS, this.contributors);
    map.put(PluginDescriptionFile.PREFIX, this.prefix);
    return map;
  }
}
