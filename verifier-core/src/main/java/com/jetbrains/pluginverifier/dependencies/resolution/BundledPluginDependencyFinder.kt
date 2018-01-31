package com.jetbrains.pluginverifier.dependencies.resolution

import com.jetbrains.plugin.structure.ide.Ide
import com.jetbrains.plugin.structure.intellij.plugin.PluginDependency
import com.jetbrains.pluginverifier.plugin.PluginDetailsCache
import com.jetbrains.pluginverifier.repository.bundled.BundledPluginsRepository

/**
 * [DependencyFinder] that searches for the plugin
 * among the [bundled] [Ide.getBundledPlugins] [ide] plugins.
 */
class BundledPluginDependencyFinder(val ide: Ide,
                                    private val pluginDetailsCache: PluginDetailsCache) : DependencyFinder {

  private val bundledPluginsRepository = BundledPluginsRepository(ide, ide.idePath.toURI().toURL())

  override fun findPluginDependency(dependency: PluginDependency): DependencyFinder.Result {
    val id = dependency.id
    val bundledPluginInfo = if (dependency.isModule) {
      bundledPluginsRepository.findPluginByModule(id)
    } else {
      bundledPluginsRepository.findPluginById(id)
    }

    if (bundledPluginInfo != null) {
      return DependencyFinder.Result.DetailsProvided(pluginDetailsCache.getPluginDetailsCacheEntry(bundledPluginInfo))
    }
    return DependencyFinder.Result.NotFound("Dependency $id is not found among the bundled plugins of $ide")
  }

}