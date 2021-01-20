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

import io.github.shiruka.api.permission.Permission;
import io.github.shiruka.api.permission.PermissionDefault;
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
   * the default permission.
   */
  private static final String DEFAULT_PERMISSION = "default-permission";

  /**
   * the depend key of the plugin.yml.
   */
  private static final String DEPEND = "depend";

  /**
   * the description key of the plugin.yml.
   */
  private static final String DESCRIPTION = "description";

  /**
   * the load key of the plugin.yml.
   */
  private static final String LOAD = "load";

  /**
   * the load before key of the plugin.yml.
   */
  private static final String LOAD_BEFORE = "load-before";

  /**
   * the main class key of the plugin.yml.
   */
  private static final String MAIN = "main";

  /**
   * the name key of the plugin.yml.
   */
  private static final String NAME = "name";

  /**
   * the order key of the plugin.yml.
   */
  private static final String ORDER = "order";

  /**
   * the permissions.
   */
  private static final String PERMISSIONS = "permissions";

  /**
   * the prefix key of the plugin.yml.
   */
  private static final String PREFIX = "prefix";

  /**
   * the provides key of the plugin.yml.
   */
  private static final String PROVIDES = "provides";

  /**
   * the soft depend key of the plugin.yml.
   */
  private static final String SOFT_DEPEND = "soft-depend";

  /**
   * validator pattern for plugin names.
   */
  private static final Pattern VALID_NAME = Pattern.compile("^[A-Za-z0-9 _.-]+$");

  /**
   * the version key of the plugin.yml.
   */
  private static final String VERSION = "version";

  /**
   * the website key of the plugin.yml.
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
   * the default perm of the plugin.
   */
  @NotNull
  private final PermissionDefault defaultPerm;

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
   * the permissions of the plugin.
   */
  @NotNull
  private final List<Permission> permissions;

  /**
   * the prefix of the plugin.
   */
  @NotNull
  private final String prefix;

  /**
   * the provides.
   */
  @NotNull
  private final List<String> provides;

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
   * @param authors the authors.
   * @param contributors the contributors.
   * @param defaultPerm the default perm.
   * @param depend the depend.
   * @param description the description.
   * @param loadBefore the load before.
   * @param main the main.
   * @param name the name.
   * @param order the order.
   * @param permissions the permissions.
   * @param prefix the prefix.
   * @param provides the provides.
   * @param softDepend the soft dependency.
   * @param version the version.
   * @param website the website.
   */
  private PluginDescriptionFile(@NotNull final List<String> authors, @NotNull final List<String> contributors,
                                @NotNull final PermissionDefault defaultPerm, @NotNull final List<String> depend,
                                @NotNull final String description, @NotNull final List<String> loadBefore,
                                @NotNull final String main, @NotNull final String name,
                                @NotNull final PluginLoadOrder order, @NotNull final List<Permission> permissions,
                                @NotNull final String prefix, @NotNull final List<String> provides,
                                @NotNull final List<String> softDepend, @NotNull final String version,
                                @NotNull final String website) {
    this.authors = Collections.unmodifiableList(authors);
    this.contributors = Collections.unmodifiableList(contributors);
    this.defaultPerm = defaultPerm;
    this.depend = Collections.unmodifiableList(depend);
    this.description = description;
    this.loadBefore = Collections.unmodifiableList(loadBefore);
    this.main = main;
    this.name = name;
    this.order = order;
    this.permissions = Collections.unmodifiableList(permissions);
    this.prefix = prefix;
    this.provides = Collections.unmodifiableList(provides);
    this.softDepend = Collections.unmodifiableList(softDepend);
    this.version = version;
    this.website = website;
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
    final var map = new HashMap<String, Object>();
    map.put(PluginDescriptionFile.NAME, name);
    map.put(PluginDescriptionFile.VERSION, version);
    map.put(PluginDescriptionFile.MAIN, main);
    return PluginDescriptionFile.init(map);
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
    final String name;
    try {
      final var rawName = map.get(PluginDescriptionFile.NAME).toString();
      if (!PluginDescriptionFile.VALID_NAME.matcher(rawName).matches()) {
        throw new InvalidDescriptionException(String.format("name '%s' contains invalid characters.", rawName));
      }
      name = rawName.replace(' ', '_');
    } catch (final NullPointerException ex) {
      throw new InvalidDescriptionException(ex, "name is not defined");
    } catch (final ClassCastException ex) {
      throw new InvalidDescriptionException(ex, "name is of wrong type");
    }
    final var provides = PluginDescriptionFile.makePluginNameList(map, "provides");
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
        throw new InvalidDescriptionException("main may not be within the io.github.shiruka namespace");
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
          .toUpperCase(Locale.ROOT)
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
    PermissionDefault defaultPerm = PermissionDefault.OP;
    final var defaultPermission = map.get(PluginDescriptionFile.DEFAULT_PERMISSION);
    if (defaultPermission != null) {
      try {
        defaultPerm = PermissionDefault.getByName(defaultPermission.toString()).orElse(PermissionDefault.OP);
      } catch (final ClassCastException ex) {
        throw new InvalidDescriptionException(ex, "default-permission is of wrong type");
      } catch (final IllegalArgumentException ex) {
        throw new InvalidDescriptionException(ex, "default-permission is not a valid choice");
      }
    }
    final Map<?, ?> lazyPermissions;
    try {
      lazyPermissions = (Map<?, ?>) map.getOrDefault(PluginDescriptionFile.PERMISSIONS, new HashMap<>());
    } catch (final ClassCastException ex) {
      throw new InvalidDescriptionException(ex, "permissions are of the wrong type");
    }
    final var permissions = Permission.loadPermissions(lazyPermissions,
      "Permission node '%s' in plugin description file for " + name + " v" + version + " is invalid!", defaultPerm);
    return new PluginDescriptionFile(authors, contributors, defaultPerm, depend, description, loadBefore, main, name,
      order, permissions, prefix, provides, softDepend, version, website);
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
      throw new InvalidDescriptionException(ex, String.format("%s is of wrong type", key));
    } catch (final NullPointerException ex) {
      throw new InvalidDescriptionException(ex, String.format("invalid %s format", key));
    }
    return list;
  }

  /**
   * obtains the authors.
   *
   * @return author list.
   */
  @NotNull
  public List<String> getAuthors() {
    return this.authors;
  }

  /**
   * obtains the description.
   *
   * @return description.
   */
  @NotNull
  public String getDescription() {
    return this.description;
  }

  /**
   * returns the name of a plugin, including the version.
   *
   * @return a descriptive name of the plugin and respective version.
   */
  @NotNull
  public String getFullName() {
    return this.name + " v" + this.version;
  }

  /**
   * obtains the main class path.
   *
   * @return main class path.
   */
  @NotNull
  public String getMain() {
    return this.main;
  }

  /**
   * obtains the plugin name.
   *
   * @return plugin name.
   */
  @NotNull
  public String getName() {
    return this.name;
  }

  /**
   * obtains the provides.
   *
   * @return provides.
   */
  @NotNull
  public List<String> getProvides() {
    return this.provides;
  }

  /**
   * obtains the version.
   *
   * @return version.
   */
  @NotNull
  public String getVersion() {
    return this.version;
  }

  /**
   * obtains the website.
   *
   * @return website.
   */
  @NotNull
  public String getWebsite() {
    return this.website;
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
    map.put(PluginDescriptionFile.PROVIDES, this.provides);
    map.put(PluginDescriptionFile.MAIN, this.main);
    map.put(PluginDescriptionFile.VERSION, this.version);
    map.put(PluginDescriptionFile.ORDER, this.order.toString());
    map.put(PluginDescriptionFile.DEFAULT_PERMISSION, this.defaultPerm.toString());
    map.put(PluginDescriptionFile.PERMISSIONS, this.permissions);
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
