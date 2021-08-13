package io.github.shiruka.api.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.github.shiruka.api.version.Version;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.infumialib.maps.MutableMap;

/**
 * an interface to determine plugins.
 */
public interface Plugin {

  /**
   * runs after the plugin disable.
   */
  void onDisable();

  /**
   * runs after the plugin enable.
   */
  void onEnable();

  /**
   * runs after the plugin load.
   */
  void onLoad();

  /**
   * an interface to determine load order for plugins.
   */
  @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
  enum LoadOrder {
    /**
     * loads the plugin before all world loaded.
     */
    STARTUP("startup", "start", "before", "pre"),
    /**
     * loads the plugin after all worlds loaded.
     */
    POST_WORLD("post_world", "post-world", "post world", "after", "post");

    /**
     * the types.
     */
    @NotNull
    private final Collection<String> types;

    /**
     * ctor.
     *
     * @param types the types.
     */
    LoadOrder(@NotNull final String... types) {
      this(Set.of(types));
    }

    /**
     * gets the load order from the given type.
     *
     * @param type the type to get.
     *
     * @return load order.
     */
    @NotNull
    public static Optional<LoadOrder> byType(@NotNull final String type) {
      final var lowerCase = type.toLowerCase(Locale.ROOT);
      return Arrays.stream(LoadOrder.values())
        .filter(loadOrder -> loadOrder.is(lowerCase))
        .findFirst();
    }

    /**
     * checks if {@link #types} contains the given type.
     *
     * @param type the type to check.
     *
     * @return {@code true} if {@link #types} contains the given type.
     */
    public boolean is(@NotNull final String type) {
      return this.types.contains(type);
    }
  }

  /**
   * an interface to determine plugin loaders.
   */
  interface Loader {

    /**
     * disables the plugin.
     *
     * @param plugin the plugin to disable.
     */
    default void disablePlugin(@NotNull final Container plugin) {
      this.disablePlugin(plugin, false);
    }

    /**
     * disables the plugin.
     *
     * @param plugin the plugin to disable.
     * @param closeClassLoaders the close class loaders to disable.
     */
    void disablePlugin(@NotNull Container plugin, boolean closeClassLoaders);

    /**
     * enables the plugin.
     *
     * @param plugin the plugin to enable.
     */
    void enablePlugin(@NotNull Container plugin);

    /**
     * loads the plugin.
     *
     * @param file the file to load.
     *
     * @return loaded plugin.
     *
     * @throws InvalidDescriptionException if something goes wrong when parsing the description.
     */
    @NotNull
    Description loadDescription(@NotNull File file) throws InvalidDescriptionException;

    /**
     * loads the plugin from the file.
     *
     * @param file the file to load.
     *
     * @return loaded plugin.
     *
     * @throws InvalidPluginException when the specified file is not a plugin.
     * @throws UnknownDependencyException if a required dependency could not be found.
     */
    @NotNull
    Plugin.Container loadPlugin(@NotNull File file) throws InvalidPluginException, UnknownDependencyException;
  }

  /**
   * an interface to determine plugin managers.
   */
  interface Manager {

    /**
     * clears all the plugins.
     */
    void clearPlugins();

    /**
     * disables the plugin.
     *
     * @param plugin the plugin to disable.
     */
    default void disablePlugin(@NotNull final Container plugin) {
      this.disablePlugin(plugin, false);
    }

    /**
     * disables the plugin.
     *
     * @param plugin the plugin to disable.
     * @param closeClassLoaders the close class loaders to disable.
     */
    void disablePlugin(@NotNull Container plugin, boolean closeClassLoaders);

    /**
     * disables all the plugins.
     */
    default void disablePlugins() {
      this.disablePlugins(false);
    }

    /**
     * disables all the plugins.
     *
     * @param closeClassLoaders the close class loaders to disable.
     */
    default void disablePlugins(final boolean closeClassLoaders) {
      for (final var plugin : this.plugins()) {
        this.disablePlugin(plugin, closeClassLoaders);
      }
    }

    /**
     * enables the plugin.
     *
     * @param plugin the plugin to enable.
     */
    void enablePlugin(@NotNull Container plugin);

    /**
     * checks if the plugin is enabled.
     *
     * @param plugin the plugin to check.
     *
     * @return {@code true} if the plugin enabled.
     */
    default boolean isPluginEnabled(@NotNull final String plugin) {
      return this.plugin(plugin)
        .map(this::isPluginEnabled)
        .orElse(false);
    }

    /**
     * checks if the plugin is enabled.
     *
     * @param plugin the plugin to check.
     *
     * @return {@code true} if the plugin enabled.
     */
    boolean isPluginEnabled(@NotNull Container plugin);

    /**
     * checks if the plugin transitive depend.
     *
     * @param plugin the plugin to check.
     * @param depend the depend to check.
     *
     * @return {@code true} if the plugin transitive depend.
     */
    boolean isTransitiveDepend(@NotNull Container plugin, @NotNull Container depend);

    /**
     * loads the plugin from the given.
     *
     * @param file the file to load.
     *
     * @return loaded plugin.
     *
     * @throws InvalidPluginException when the file is not a valid plugin.
     * @throws InvalidDescriptionException when the file contains an invalid description.
     * @throws UnknownDependencyException if a required dependency could not be resolved.
     */
    @Nullable
    Container loadPlugin(@NotNull File file) throws InvalidPluginException, InvalidDescriptionException,
      UnknownDependencyException;

    /**
     * loads the plugin in the folder.
     *
     * @param folder the folder to load.
     *
     * @return all loaded plugins in the folder.
     */
    @NotNull
    Collection<Container> loadPlugins(@NotNull File folder);

    /**
     * obtains all plugin loaders.
     *
     * @return all plugin loaders.
     */
    @NotNull
    Map<Pattern, Loader> loaders();

    /**
     * gets a plugin by name.
     *
     * @param plugin the plugin to get.
     *
     * @return plugin.
     */
    @NotNull
    Optional<Plugin.Container> plugin(@NotNull String plugin);

    /**
     * obtains the plugins.
     *
     * @return plugins.
     */
    @NotNull
    Collection<Plugin.Container> plugins();

    /**
     * obtains the plugins directory.
     *
     * @return plugins directory.
     */
    @NotNull
    File pluginsDirectory();

    /**
     * registers a new plugin loader.
     *
     * @param pattern the pattern to register.
     * @param loader the loader to register.
     */
    void registerLoader(@NotNull Pattern pattern, @NotNull Loader loader);
  }

  /**
   * a record class that represents plugin containers to store date of plugins.
   */
  @Getter
  @Accessors(fluent = true)
  @RequiredArgsConstructor
  final class Container {

    /**
     * the class loader.
     */
    @NotNull
    private final ClassLoader classLoader;

    /**
     * the data folder.
     */
    @NotNull
    private final File dataFolder;

    /**
     * the description.
     */
    @NotNull
    private final Description description;

    /**
     * the loader.
     */
    @NotNull
    private final Loader loader;

    /**
     * the logger.
     */
    @NotNull
    private final Logger logger;

    /**
     * the plugin.
     */
    @NotNull
    private final Plugin plugin;

    /**
     * the plugin file.
     */
    @NotNull
    private final File pluginFile;

    /**
     * the enabled.
     */
    private boolean enabled;

    /**
     * sets the enabled.
     *
     * @param enabled the enabled to set.
     */
    public void setEnabled(final boolean enabled) {
      if (this.enabled == enabled) {
        return;
      }
      this.enabled = enabled;
      if (this.enabled) {
        this.plugin.onEnable();
      } else {
        this.plugin.onDisable();
      }
    }
  }

  /**
   * a simple record class that represents description file of plugins.
   * <p>
   * example to show scheme of the plugin file:
   * <pre>
   *   # Required
   *   name: Test Plugin
   *   main: io.github.shiruka.test.TestPlugin
   *
   *   # Optional
   *   version: "1.0.0-SNAPSHOT" # default is 1.0.0
   *   description: "A simple plugin to show developers how to create a plugin." # default is empty
   *   load: startup # default is post-world
   *   authors: # default is empty
   *     - "Shiru ka"
   *   contributors: # default is empty
   *     - "portlek"
   *   website: https://shiruka.github.io
   *   provides:
   *     - "test-provide"
   *   depends: # default is empty
   *     - "test-depend"
   *   soft-depends: # default is empty
   *     - "test-soft-depend"
   *   load-before: # default is empty
   *     - "test-load-before"
   *   prefix: "Test Plugin" # default is name
   * </pre>
   *
   * @param name name of the plugin.
   * @param main plugin's main class path. the separator could be {@literal .} or {@literal /} or {@literal \} or
   *   {@literal -}.
   * @param version version of the plugin.
   * @param description description of the plugin.
   * @param loadOrder load order of the plugin.
   * @param authors authors of the plugin.
   * @param contributors contributors of the plugin.
   * @param prefix prefix, which will use for logging, of the plugin.
   * @param provides plugin APIs which this plugin provides.
   * @param depends dependencies, which the server HAVE TO have, of the plugin.
   * @param softDepends soft-dependencies, which the server DON'T HAVE TO have, of the plugin.
   * @param loadBefore loads the plugin before these plugins.
   * @param website website of the plugin.
   *
   * @see <a href="https://semver.org/">version syntax</a>
   */
  @Log4j2
  @NotNull
  final record Description(
    @NotNull String name,
    @NotNull String main,
    @NotNull Version version,
    @NotNull String description,
    @NotNull LoadOrder loadOrder,
    @NotNull Collection<String> authors,
    @NotNull Collection<String> contributors,
    @NotNull String prefix,
    @NotNull Collection<String> provides,
    @NotNull Collection<String> depends,
    @NotNull Collection<String> softDepends,
    @NotNull Collection<String> loadBefore,
    @NotNull String website
  ) {

    /**
     * the mapper.
     */
    private static final ObjectMapper MAPPER = new YAMLMapper()
      .enable(YAMLGenerator.Feature.INDENT_ARRAYS)
      .enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * the map type.
     */
    private static final MapType MAP_TYPE = Description.MAPPER.getTypeFactory()
      .constructMapType(HashMap.class, String.class, Object.class);

    /**
     * the pattern for validate plugin names.
     */
    private static final Predicate<String> VALID_NAME = Pattern.compile("^[A-Za-z0-9 _.-]+$")
      .asMatchPredicate();

    /**
     * creates a description instance from the stream.
     *
     * @param stream the stream to create.
     *
     * @return a newly created description from stream.
     *
     * @throws IOException if something goes wrong when reading values in the stream.
     * @throws InvalidDescriptionException if something goes wrong when parsing the map.
     */
    @NotNull
    public static Description of(@NotNull final InputStream stream) throws IOException, InvalidDescriptionException {
      return Description.of(Description.MAPPER.<Map<String, Object>>readValue(stream, Description.MAP_TYPE));
    }

    /**
     * creates a description instance from the map.
     *
     * @param map the map to create.
     *
     * @return a newly created description from map.
     *
     * @throws InvalidDescriptionException if something goes wrong when parsing the map.
     */
    @NotNull
    public static Description of(@NotNull final Map<String, Object> map) throws InvalidDescriptionException {
      final var name = Description.essential(map, "name", String.class).replace(' ', '_');
      Description.validateName(name);
      final var main = Description.essential(map, "main", String.class);
      final var version = Description.optional(map, "version", String.class, Version.of(1), s -> {
        try {
          Version.of(s);
        } catch (final ParseException e) {
          Description.log.error("Couldn't parse the version %s".formatted(s), e);
          Description.log.info("Using default version(1.0.0) instead");
        }
        return Version.of(1);
      });
      final var description = Description.optional(map, "description", String.class, "");
      final var loadOrder = Description.optional(map, "load", String.class, LoadOrder.POST_WORLD, s ->
        LoadOrder.byType(s).orElse(null));
      final var authors = Description.optionalStringCollection(map, "authors");
      final var contributors = Description.optionalStringCollection(map, "contributors");
      final var prefix = Description.optional(map, "prefix", String.class, name);
      final var depends = Description.optionalStringCollection(map, "depends");
      final var provides = Description.optionalStringCollection(map, "provides");
      final var softDepends = Description.optionalStringCollection(map, "soft-depends");
      final var loadBefore = Description.optionalStringCollection(map, "load-before");
      final var website = Description.optional(map, "website", String.class, "");
      return new Description(name, main, version, description, loadOrder, authors, contributors, prefix, depends,
        provides, softDepends, loadBefore, website);
    }

    /**
     * gets the value from the map at the given key.
     *
     * @param map the map to get.
     * @param key the key to get.
     * @param type the type to get..
     * @param <T> type of the final value.
     *
     * @return requested value.
     *
     * @throws InvalidDescriptionException if something goes wrong when parsing the map.
     */
    @NotNull
    private static <T> T essential(@NotNull final Map<String, Object> map, @NotNull final String key,
                                   @NotNull final Class<T> type)
      throws InvalidDescriptionException {
      try {
        return Objects.requireNonNull(type.cast(map.get(key)));
      } catch (final NullPointerException e) {
        throw new InvalidDescriptionException("The key called %s not found in the plugin file!",
          key);
      } catch (final ClassCastException e) {
        throw new InvalidDescriptionException("Invalid type for %s key, found %s!",
          key, map.get(key).getClass());
      }
    }

    /**
     * gets the value from the map at the given key.
     *
     * @param map the map to get.
     * @param key the key to get.
     * @param type the type to get.
     * @param defaultValue the default value to get.
     * @param <T> type of the final value.
     *
     * @return requested value.
     *
     * @throws InvalidDescriptionException if something goes wrong when parsing the map.
     */
    @NotNull
    private static <T> T optional(@NotNull final Map<String, Object> map, @NotNull final String key,
                                  @NotNull final Class<T> type, @NotNull final T defaultValue)
      throws InvalidDescriptionException {
      return Description.optional(map, key, type, defaultValue, UnaryOperator.identity());
    }

    /**
     * gets the value from the map at the given key.
     *
     * @param map the map to get.
     * @param key the key to get.
     * @param type the type to get.
     * @param defaultValue the default value to get.
     * @param mappingFunction the mapping function to get.
     * @param <T> type of the map value.
     * @param <Y> type of the final value.
     *
     * @return requested value.
     *
     * @throws InvalidDescriptionException if something goes wrong when parsing the map.
     */
    @NotNull
    private static <T, Y> Y optional(@NotNull final Map<String, Object> map, @NotNull final String key,
                                     @NotNull final Class<T> type, @NotNull final Y defaultValue,
                                     @NotNull final Function<@NotNull T, @Nullable Y> mappingFunction)
      throws InvalidDescriptionException {
      final var value = map.get(key);
      if (value == null) {
        return defaultValue;
      }
      try {
        final var mapped = mappingFunction.apply(type.cast(value));
        if (mapped == null) {
          return defaultValue;
        }
        return mapped;
      } catch (final NullPointerException e) {
        throw new InvalidDescriptionException("The key called %s not found in the plugin file!",
          key);
      } catch (final ClassCastException e) {
        throw new InvalidDescriptionException("Invalid type for %s key, found %s!",
          key, value.getClass());
      }
    }

    /**
     * gets the value from the map at the given key.
     *
     * @param map the map to get.
     * @param key the key to get.
     *
     * @return requested value.
     *
     * @throws InvalidDescriptionException if something goes wrong when parsing the map.
     */
    @NotNull
    private static Collection<String> optionalStringCollection(@NotNull final Map<String, Object> map,
                                                               @NotNull final String key)
      throws InvalidDescriptionException {
      return Description.optional(map, key, Collection.class, Collections.emptySet(), collection ->
        ((Collection<?>) collection).stream()
          .map(Object::toString)
          .collect(Collectors.toSet()));
    }

    /**
     * validates the name and returns it back.
     *
     * @param name the name to validate.
     *
     * @throws InvalidDescriptionException if the name has invalid characters.
     * @see #VALID_NAME
     */
    private static void validateName(@NotNull final String name) throws InvalidDescriptionException {
      if (!Description.VALID_NAME.test(name)) {
        throw new InvalidDescriptionException("The name %s contains invalid characters!",
          name);
      }
    }

    /**
     * obtains the full name.
     *
     * @return full name.
     */
    @NotNull
    public String fullName() {
      return this.name + " v" + this.version;
    }

    /**
     * converts all variables into a map.
     *
     * @return serialized map.
     */
    @NotNull
    public Map<String, Object> serialize() {
      return MutableMap.<String, Object>of()
        .with("name", this.name)
        .with("main", this.main)
        .with("version", this.version.toString())
        .with("description", this.description)
        .with("load", this.loadOrder.name().toLowerCase(Locale.ROOT))
        .with("authors", this.authors)
        .with("contributors", this.contributors)
        .with("prefix", this.prefix)
        .with("depends", this.depends)
        .with("soft-depends", this.softDepends)
        .with("load-before", this.loadBefore)
        .with("website", this.website);
    }
  }
}
