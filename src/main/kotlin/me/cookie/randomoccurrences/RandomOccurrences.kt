package me.cookie.randomoccurrences

import me.cookie.randomoccurrences.listeners.*
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class RandomOccurrences: JavaPlugin() {
    lateinit var occurrenceManager: OccurrenceManager

    override fun onEnable() {
        val pluginId = 15297
        val metrics = Metrics(this, pluginId)

        if (config.getInt("minimum-players", -1) < 0) {
            logger.severe(" [!!!] The config has been changed! Please update the config file! (a backup of your old config has been made) [!!!] ")
            val file = File(dataFolder, "config.yml")
            file.renameTo(File(dataFolder, "config.old.yml"))
            saveDefaultConfig()
            reloadConfig()
        }

        occurrenceManager = OccurrenceManager(this)
        saveDefaultConfig()

        server.pluginManager.registerEvents(BlockBreak(occurrenceManager), this)
        server.pluginManager.registerEvents(BlockPlace(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityDamage(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityDamageByEntity(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityKill(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerJump(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerFish(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerMove(occurrenceManager), this)

        if (config.getString("update-checker", "OFF") != "OFF"){
            server.pluginManager.registerEvents(PlayerJoin(this), this)
        }
    }

    override fun onDisable() {
        // Disable logic
        if (occurrenceManager.currentOccurrence != null) {
            occurrenceManager.currentOccurrence!!.end()
        }
        return
    }
}