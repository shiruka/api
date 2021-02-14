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

package net.shiruka.api.pack;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;
import net.shiruka.api.util.SemanticVersion;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents pack manifests.
 * <p>
 * full-example file:
 * <pre>
 *   {
 *     "capabilities": [
 *       "...", "...", "..."
 *     ],
 *     "dependencies": [
 *       {
 *         "uuid": "...-...-...",
 *         "version": [
 *           1, 0, 0
 *         ]
 *       }
 *     ],
 *     "format_version": "...",
 *     "header": {
 *       "description": "...",
 *       "load_before_game": true,
 *       "lock_template_options": false,
 *       "min_engine_version": [
 *         1, 0, 0
 *       ],
 *       "name": "...",
 *       "pack_scope": "global",
 *       "platform_locked": false,
 *       "population_control": true,
 *       "uuid": "...-...-...",
 *       "version": [
 *         1, 0, 0
 *       ]
 *     },
 *     "modules": [
 *       {
 *         "description": "...",
 *         type: "plugin",
 *         "uuid": "...-...-...",
 *         "version": [
 *            1, 0, 0
 *          ]
 *       }
 *     ],
 *     subpacks: [
 *       {
 *         "folder_name": "...",
 *         "memory_tier": 1,
 *         "name": "..."
 *       }
 *     ]
 *   }
 * </pre>
 *
 * @todo #1:60m Make jackson like java class creator from json using minimal-json then replace each load method.
 */
public final class PackManifest {

  /**
   * the capabilities.
   */
  @NotNull
  private final List<String> capabilities;

  /**
   * the dependencies.
   */
  @NotNull
  private final List<Dependency> dependencies;

  /**
   * the format version.
   */
  @NotNull
  private final String formatVersion;

  /**
   * the header.
   */
  @NotNull
  private final Header header;

  /**
   * the metadata.
   */
  @NotNull
  private final Metadata metadata;

  /**
   * the modules.
   */
  @NotNull
  private final List<Module> modules;

  /**
   * the sub packs.
   */
  @NotNull
  private final List<SubResourcePack> subPacks;

  /**
   * ctor.
   *
   * @param capabilities the capabilities.
   * @param dependencies the dependencies.
   * @param formatVersion the format version.
   * @param header the header.
   * @param metadata the metadata.
   * @param modules the modules.
   * @param subPacks the sub packs.
   */
  public PackManifest(@NotNull final List<String> capabilities, @NotNull final List<Dependency> dependencies,
                      @NotNull final String formatVersion, @NotNull final Header header,
                      @NotNull final Metadata metadata, @NotNull final List<Module> modules,
                      @NotNull final List<SubResourcePack> subPacks) {
    this.capabilities = Collections.unmodifiableList(capabilities);
    this.dependencies = Collections.unmodifiableList(dependencies);
    this.formatVersion = formatVersion;
    this.header = header;
    this.metadata = metadata;
    this.modules = Collections.unmodifiableList(modules);
    this.subPacks = Collections.unmodifiableList(subPacks);
  }

  /**
   * loads the manifest from the stream.
   *
   * @param stream the stream.
   *
   * @return load the manifest.
   */
  @NotNull
  public static PackManifest load(@NotNull final InputStream stream) throws IOException {
    final var parse = Json.parse(new InputStreamReader(stream));
    final var json = parse.asObject();
    final var capabilities = json.get("capabilities").asArray().values().stream()
      .map(JsonValue::asString)
      .collect(Collectors.toList());
    final var dependencies = Dependency.load(json.get("dependencies").asArray());
    final var formatVersion = json.get("format_version").asString();
    final var header = Header.load(json.get("header").asObject());
    final var metadata = Metadata.load(json.get("metadata").asObject());
    final var modules = Module.load(json.get("modules").asArray());
    final var subPacks = SubResourcePack.load(json.get("subpacks").asArray());
    return new PackManifest(capabilities, dependencies, formatVersion, header, metadata, modules, subPacks);
  }

  /**
   * obtains the capabilities.
   *
   * @return capabilities.
   */
  @NotNull
  public List<String> getCapabilities() {
    return this.capabilities;
  }

  /**
   * obtains the dependencies.
   *
   * @return dependencies.
   */
  @NotNull
  public List<Dependency> getDependencies() {
    return this.dependencies;
  }

  /**
   * obtains the format version.
   *
   * @return format version.
   */
  @NotNull
  public String getFormatVersion() {
    return this.formatVersion;
  }

  /**
   * obtains the header.
   *
   * @return header.
   */
  @NotNull
  public Header getHeader() {
    return this.header;
  }

  /**
   * obtains the metadata.
   *
   * @return metadata.
   */
  @NotNull
  public Metadata getMetadata() {
    return this.metadata;
  }

  /**
   * obtains the modules.
   *
   * @return modules.
   */
  @NotNull
  public List<Module> getModules() {
    return this.modules;
  }

  /**
   * obtains the sub packs.
   *
   * @return sub packs.
   */
  @NotNull
  public List<SubResourcePack> getSubPacks() {
    return this.subPacks;
  }

  /**
   * an enum class that represents resource pack types.
   */
  public enum PackType {
    /**
     * the invalid.
     */
    INVALID,
    /**
     * the resources.
     */
    RESOURCES,
    /**
     * the data.
     */
    DATA,
    /**
     * the plugin.
     */
    PLUGIN,
    /**
     * the client data.
     */
    CLIENT_DATA,
    /**
     * the interface.
     */
    INTERFACE,
    /**
     * the mandatory.
     */
    MANDATORY,
    /**
     * the world template.
     */
    WORLD_TEMPLATE
  }

  /**
   * a class that represents dependencies.
   */
  public static final class Dependency {

    /**
     * the uuid.
     */
    @NotNull
    private final UUID uuid;

    /**
     * the version.
     */
    @NotNull
    private final SemanticVersion version;

    /**
     * ctor.
     *
     * @param uuid the uuid.
     * @param version the version.
     */
    private Dependency(@NotNull final UUID uuid, @NotNull final SemanticVersion version) {
      this.uuid = uuid;
      this.version = version;
    }

    /**
     * loads the dependencies from the json array.
     *
     * @param array the array to load.
     *
     * @return loaded dependencies.
     */
    @NotNull
    public static List<Dependency> load(@NotNull final JsonArray array) {
      return array.values().stream()
        .filter(JsonValue::isObject)
        .map(JsonValue::asObject)
        .map(json ->
          new Dependency(UUID.fromString(json.get("uuid").asString()),
            SemanticVersion.load(json.get("version").asArray())))
        .collect(Collectors.toUnmodifiableList());
    }

    /**
     * obtains the uuid.
     *
     * @return uuid.
     */
    @NotNull
    public UUID getUuid() {
      return this.uuid;
    }

    /**
     * obtains the version.
     *
     * @return version.
     */
    @NotNull
    public SemanticVersion getVersion() {
      return this.version;
    }
  }

  /**
   * a class that represents headers.
   */
  public static final class Header {

    /**
     * the description.
     */
    @NotNull
    private final String description;

    /**
     * the directory load.
     */
    private final boolean directoryLoad;

    /**
     * the load before game.
     */
    private final boolean loadBeforeGame;

    /**
     * the lock template options.
     */
    private final boolean lockTemplateOptions;

    /**
     * the minimum engine version.
     */
    @NotNull
    private final SemanticVersion minEngineVersion;

    /**
     * the name.
     */
    @NotNull
    private final String name;

    /**
     * the pack scope.
     */
    @NotNull
    private final String packScope;

    /**
     * the platform locked.
     */
    private final boolean platformLocked;

    /**
     * the population control.
     */
    private final boolean populationControl;

    /**
     * the uuid.
     */
    @NotNull
    private final UUID uuid;

    /**
     * the version.
     */
    @NotNull
    private final SemanticVersion version;

    /**
     * ctor.
     *
     * @param description the description.
     * @param directoryLoad the directory load.
     * @param loadBeforeGame the load before game.
     * @param lockTemplateOptions the lock template options.
     * @param minEngineVersion the minimum engine version.
     * @param name the name.
     * @param packScope the pack scope.
     * @param platformLocked the platform locked.
     * @param populationControl the population control.
     * @param uuid the uuid.
     * @param version the version.
     */
    private Header(@NotNull final String description, final boolean directoryLoad, final boolean loadBeforeGame,
                   final boolean lockTemplateOptions, @NotNull final SemanticVersion minEngineVersion,
                   @NotNull final String name, @NotNull final String packScope, final boolean platformLocked,
                   final boolean populationControl, @NotNull final UUID uuid, @NotNull final SemanticVersion version) {
      this.description = description;
      this.directoryLoad = directoryLoad;
      this.loadBeforeGame = loadBeforeGame;
      this.lockTemplateOptions = lockTemplateOptions;
      this.minEngineVersion = minEngineVersion;
      this.name = name;
      this.packScope = packScope;
      this.platformLocked = platformLocked;
      this.populationControl = populationControl;
      this.uuid = uuid;
      this.version = version;
    }

    /**
     * loads the header from the json object.
     *
     * @param json the json to load.
     *
     * @return loaded header.
     */
    @NotNull
    public static Header load(@NotNull final JsonObject json) {
      final var description = json.get("description").asString();
      final var directoryLoad = json.get("directory_load").asBoolean();
      final var loadBeforeGame = json.get("load_before_game").asBoolean();
      final var lockTemplateOptions = json.get("lock_template_options").asBoolean();
      final var minEngineVersion = SemanticVersion.load(json.get("min_engine_version").asArray());
      final var name = json.get("name").asString();
      final var packScope = json.getString("pack_scope", "global");
      final var platformLocked = json.get("platform_locked").asBoolean();
      final var populationControl = json.get("population_control").asBoolean();
      final var uuid = UUID.fromString(json.get("uuid").asString());
      final var version = SemanticVersion.load(json.get("version").asArray());
      return new Header(description, directoryLoad, loadBeforeGame, lockTemplateOptions, minEngineVersion, name,
        packScope, platformLocked, populationControl, uuid, version);
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
     * obtains the minimum engine version.
     *
     * @return minimum engine version.
     */
    @NotNull
    public SemanticVersion getMinEngineVersion() {
      return this.minEngineVersion;
    }

    /**
     * obtains the name.
     *
     * @return name.
     */
    @NotNull
    public String getName() {
      return this.name;
    }

    /**
     * obtains the pack scope.
     *
     * @return pack scope.
     */
    @NotNull
    public String getPackScope() {
      return this.packScope;
    }

    /**
     * obtains the uuid.
     *
     * @return uuid.
     */
    @NotNull
    public UUID getUuid() {
      return this.uuid;
    }

    /**
     * obtains the version.
     *
     * @return version.
     */
    @NotNull
    public SemanticVersion getVersion() {
      return this.version;
    }

    /**
     * obtains the directory load.
     *
     * @return directory load.
     */
    public boolean isDirectoryLoad() {
      return this.directoryLoad;
    }

    /**
     * obtains the load before game.
     *
     * @return load before game.
     */
    public boolean isLoadBeforeGame() {
      return this.loadBeforeGame;
    }

    /**
     * obtains the lock template options.
     *
     * @return lock template options.
     */
    public boolean isLockTemplateOptions() {
      return this.lockTemplateOptions;
    }

    /**
     * obtains the platform locked.
     *
     * @return platform locked.
     */
    public boolean isPlatformLocked() {
      return this.platformLocked;
    }

    /**
     * obtains the population control.
     *
     * @return population control.
     */
    public boolean isPopulationControl() {
      return this.populationControl;
    }
  }

  /**
   * a class that represents metadata.
   */
  public static final class Metadata {

    /**
     * the authors.
     */
    @NotNull
    private final List<String> authors;

    /**
     * the license.
     */
    @NotNull
    private final String license;

    /**
     * the url.
     */
    @NotNull
    private final String url;

    /**
     * ctor.
     *
     * @param authors the authors.
     * @param license the license.
     * @param url the url.
     */
    private Metadata(@NotNull final List<String> authors, @NotNull final String license, @NotNull final String url) {
      this.authors = Collections.unmodifiableList(authors);
      this.license = license;
      this.url = url;
    }

    /**
     * loads the metadata from the json object.
     *
     * @param json the json to load.
     *
     * @return loaded metadata.
     */
    @NotNull
    public static Metadata load(@NotNull final JsonObject json) {
      final var authors = json.get("authors").asArray().values().stream()
        .map(JsonValue::asString)
        .collect(Collectors.toList());
      final var license = json.get("license").asString();
      final var url = json.get("url").asString();
      return new Metadata(authors, license, url);
    }

    /**
     * obtains the authors.
     *
     * @return authors.
     */
    @NotNull
    public List<String> getAuthors() {
      return this.authors;
    }

    /**
     * obtains the license.
     *
     * @return license.
     */
    @NotNull
    public String getLicense() {
      return this.license;
    }

    /**
     * obtains the url.
     *
     * @return url.
     */
    @NotNull
    public String getUrl() {
      return this.url;
    }
  }

  /**
   * a class that represents modules.
   */
  public static final class Module {

    /**
     * the description.
     */
    @NotNull
    private final String description;

    /**
     * the type.
     */
    @NotNull
    private final PackType type;

    /**
     * the uuid.
     */
    @NotNull
    private final UUID uuid;

    /**
     * the version.
     */
    @NotNull
    private final SemanticVersion version;

    /**
     * ctor.
     *
     * @param description the description.
     * @param type the type.
     * @param uuid the uuid.
     * @param version the version.
     */
    private Module(@NotNull final String description, @NotNull final PackType type, @NotNull final UUID uuid,
                   @NotNull final SemanticVersion version) {
      this.description = description;
      this.type = type;
      this.uuid = uuid;
      this.version = version;
    }

    /**
     * loads modules from the json array.
     *
     * @param array the array to load.
     *
     * @return loaded modules.
     */
    @NotNull
    public static List<Module> load(@NotNull final JsonArray array) {
      return array.values().stream()
        .filter(JsonValue::isObject)
        .map(JsonValue::asObject)
        .map(json ->
          new Module(json.get("description").asString(),
            PackType.valueOf(json.get("type").asString().toLowerCase(Locale.ROOT)),
            UUID.fromString(json.get("uuid").asString()),
            SemanticVersion.load(json.get("version").asArray())))
        .collect(Collectors.toUnmodifiableList());
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
     * obtains the type.
     *
     * @return type.
     */
    @NotNull
    public PackType getType() {
      return this.type;
    }

    /**
     * obtains the uuid.
     *
     * @return uuid.
     */
    @NotNull
    public UUID getUuid() {
      return this.uuid;
    }

    /**
     * obtains the version.
     *
     * @return version.
     */
    @NotNull
    public SemanticVersion getVersion() {
      return this.version;
    }
  }

  /**
   * a class that represents sub resource packs.
   */
  public static final class SubResourcePack {

    /**
     * the folder name.
     */
    @NotNull
    private final String folderName;

    /**
     * the memory tier.
     */
    private final int memoryTier;

    /**
     * the name.
     */
    @NotNull
    private final String name;

    /**
     * ctor.
     *
     * @param folderName the folder name.
     * @param memoryTier the memory tier.
     * @param name the name.
     */
    private SubResourcePack(@NotNull final String folderName, final int memoryTier, @NotNull final String name) {
      this.folderName = folderName;
      this.memoryTier = memoryTier;
      this.name = name;
    }

    /**
     * loads sub resource packs from the json array.
     *
     * @param array the array to load.
     *
     * @return loaded packs.
     */
    @NotNull
    public static List<SubResourcePack> load(@NotNull final JsonArray array) {
      return array.values().stream()
        .filter(JsonValue::isObject)
        .map(JsonValue::asObject)
        .map(json ->
          new SubResourcePack(json.get("folder_name").asString(),
            json.get("memory_tier").asInt(),
            json.get("name").asString()))
        .collect(Collectors.toUnmodifiableList());
    }

    /**
     * obtains the folder name.
     *
     * @return folder name.
     */
    @NotNull
    public String getFolderName() {
      return this.folderName;
    }

    /**
     * obtains the memory tier.
     *
     * @return memory tier.
     */
    public int getMemoryTier() {
      return this.memoryTier;
    }

    /**
     * obtains the name.
     *
     * @return name.
     */
    @NotNull
    public String getName() {
      return this.name;
    }
  }
}
