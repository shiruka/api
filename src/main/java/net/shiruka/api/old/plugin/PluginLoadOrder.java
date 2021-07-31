package net.shiruka.api.old.plugin;

/**
 * represents the order in which a plugin should be initialized and enabled.
 */
public enum PluginLoadOrder {
  /**
   * indicates that the plugin will be loaded at startup.
   */
  STARTUP,
  /**
   * indicates that the plugin will be loaded after the first/default world was created.
   */
  POST_WORLD
}
