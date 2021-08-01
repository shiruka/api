package net.shiruka.api.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import de.skuzzle.semantic.Version;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine plugins.
 */
public interface Plugin {

  /**
   * obtains the description.
   *
   * @return description.
   */
  @NotNull
  Description getDescription();

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
   * a simple record class that implements {@link Description}.
   * <p>
   * example to show scheme of the plugin file:
   * <pre>
   *   # Required
   *   name: Test Plugin
   *   main: net.shiruka.test.TestPlugin
   *
   *   # Optional
   *   version: "1.0.0-SNAPSHOT" # default is 1.0.0
   *   description: "A simple plugin to show developers how to create a plugin." # default is empty
   *   load: startup # default is post-world
   *   authors: # default is empty
   *     - "Shiru ka"
   *   contributors: # default is empty
   *     - "portlek"
   *   website: https://shiruka.net
   *   depend: # default is empty
   *     - "test-depend"
   *   soft-depend: # default is empty
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
   * @param depends dependencies, which the server HAVE TO have, of the plugin.
   * @param softDepends soft-dependencies, which the server DON'T HAVE TO have, of the plugin.
   * @param loadBefore loads the plugin before these plugins.
   * @param website website of the plugin.
   *
   * @see <a href="https://semver.org/">version syntax</a>
   */
  @NotNull
  record Description(
    @NotNull String name,
    @NotNull String main,
    @NotNull Version version,
    @NotNull String description,
    @NotNull LoadOrder loadOrder,
    @NotNull Collection<String> authors,
    @NotNull Collection<String> contributors,
    @NotNull String prefix,
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
    private static final Pattern VALID_NAME = Pattern.compile("^[A-Za-z0-9 _.-]+$");

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
      // Essential keys.
      final var nameKey = "name";
      final var mainKey = "main";
      // Parse name of the plugin.
      @NotNull final String name;
      try {
        name = Objects.requireNonNull((String) map.get(nameKey));
      } catch (final NullPointerException e) {
        throw new InvalidDescriptionException(String.format("The key called %s not found in the plugin file!",
          nameKey));
      } catch (final ClassCastException e) {
        throw new InvalidDescriptionException(String.format("Invalid type for %s key, found %s!",
          nameKey, map.get(nameKey).getClass()));
      }
      // Parse main class path of the plugin.
      @NotNull final String main;
      try {
        main = Objects.requireNonNull((String) map.get(mainKey));
      } catch (final NullPointerException e) {
        throw new InvalidDescriptionException(String.format("The key called %s not found in the plugin file!",
          mainKey));
      } catch (final ClassCastException e) {
        throw new InvalidDescriptionException(String.format("Invalid type for %s key, found %s!",
          mainKey, map.get(mainKey).getClass()));
      }
      // Keys.
      final var versionKey = "version";
      final var descriptionKey = "description";
      final var loadKey = "load";
      final var authorsKey = "authors";
      final var contributorsKey = "contributors";
      final var prefixKey = "prefix";
      final var dependsKey = "depends";
      final var softDependsKey = "soft-depends";
      final var loadBeforeKey = "load-before";
      final var websiteKey = "website";
      // Default values.
      var version = Version.create(1);
      final var description = "";
      final var loadOrder = LoadOrder.POST_WORLD;
      final var authors = Collections.<String>emptySet();
      final var contributors = Collections.<String>emptySet();
      final var prefix = name;
      final var depends = Collections.<String>emptySet();
      final var softDepends = Collections.<String>emptySet();
      final var loadBefore = Collections.<String>emptySet();
      final var website = "";
      // Parse version.
      try {
        final var versionString = (String) map.get(versionKey);
        if (versionString != null) {
          version = Version.parseVersion(versionString, true);
        }
      } catch (final ClassCastException e) {
        if (map.containsKey(versionKey)) {
          throw new InvalidDescriptionException(String.format("Invalid type for %s key, found %s!",
            versionKey, map.get(versionKey).getClass()));
        }
      }
      return new Description(name, main, version, description, loadOrder, authors, contributors, prefix, depends,
        softDepends, loadBefore, website);
    }
  }
}
