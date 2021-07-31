package net.shiruka.api.pack;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import net.shiruka.api.Shiruka;
import net.shiruka.api.util.SemanticVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
 */
@Data
public final class PackManifest {

  /**
   * the capabilities.
   */
  @Nullable
  private List<String> capabilities = Collections.emptyList();

  /**
   * the dependencies.
   */
  @NotNull
  private List<Dependency> dependencies = Collections.emptyList();

  /**
   * the format version.
   */
  @NotNull
  @JsonProperty("format_version")
  private String formatVersion;

  /**
   * the header.
   */
  @NotNull
  private Header header;

  /**
   * the metadata.
   */
  @Nullable
  private Metadata metadata;

  /**
   * the modules.
   */
  @NotNull
  private List<Module> modules = Collections.emptyList();

  /**
   * the sub packs.
   */
  @Nullable
  private List<SubResourcePack> subPacks = Collections.emptyList();

  /**
   * loads the pack manifest from the input stream.
   *
   * @param stream the stream to load.
   *
   * @return loaded pack manifest instance.
   *
   * @throws IOException if something goes wrong when reading the stream.
   */
  @NotNull
  public static PackManifest load(@NotNull final InputStream stream) throws IOException {
    return Shiruka.JSON_MAPPER.readValue(stream, PackManifest.class);
  }

  /**
   * a class that represents dependencies.
   */
  @Data
  public static final class Dependency {

    /**
     * the uuid.
     */
    @Nullable
    private UUID uuid;

    /**
     * the version.
     */
    @Nullable
    private SemanticVersion version;
  }

  /**
   * a class that represents headers.
   */
  @Data
  public static final class Header {

    /**
     * the description.
     */
    @NotNull
    private String description;

    /**
     * the directory load.
     */
    @JsonProperty("directory_load")
    private boolean directoryLoad;

    /**
     * the load before game.
     */
    @JsonProperty("load_before_game")
    private boolean loadBeforeGame;

    /**
     * the lock template options.
     */
    @JsonProperty("lock_template_options")
    private boolean lockTemplateOptions;

    /**
     * the minimum engine version.
     */
    @Nullable
    @JsonProperty("min_engine_version")
    private SemanticVersion minEngineVersion;

    /**
     * the name.
     */
    @NotNull
    private String name;

    /**
     * the pack scope.
     */
    @Nullable
    @JsonProperty("pack_scope")
    private String packScope;

    /**
     * the platform locked.
     */
    @JsonProperty("platform_locked")
    private boolean platformLocked;

    /**
     * the population control.
     */
    @JsonProperty("population_control")
    private boolean populationControl;

    /**
     * the uuid.
     */
    @NotNull
    @JsonProperty("uuid")
    private UUID uniqueId;

    /**
     * the version.
     */
    @NotNull
    private SemanticVersion version;
  }

  /**
   * a class that represents metadata.
   */
  @Data
  public static final class Metadata {

    /**
     * the authors.
     */
    @Nullable
    private List<String> authors;

    /**
     * the license.
     */
    @Nullable
    private String license;

    /**
     * the url.
     */
    @Nullable
    private String url;
  }

  /**
   * a class that represents modules.
   */
  @Data
  public static final class Module {

    /**
     * the description.
     */
    @Nullable
    private String description;

    /**
     * the type.
     */
    @Nullable
    private PackType type;

    /**
     * the uuid.
     */
    @Nullable
    private UUID uuid;

    /**
     * the version.
     */
    @Nullable
    private SemanticVersion version;
  }

  /**
   * a class that represents sub resource packs.
   */
  @Data
  public static final class SubResourcePack {

    /**
     * the folder name.
     */
    @Nullable
    @JsonProperty("folder_name")
    private String folderName;

    /**
     * the memory tier.
     */
    @JsonProperty("memory_tier")
    private int memoryTier;

    /**
     * the name.
     */
    @Nullable
    private String name;
  }
}
