package me.cookie.randomoccurrences

import me.cookie.randomoccurrences.listeners.*
import org.bstats.bukkit.Metrics
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class RandomOccurrences: JavaPlugin(), Listener {
    lateinit var occurrenceManager: OccurrenceManager

    override fun onEnable() {
        val pluginId = 15297
        val metrics = Metrics(this, pluginId)

        // Enable logic
        occurrenceManager = OccurrenceManager(this)
        saveDefaultConfig()
        server.pluginManager.registerEvents(this, this)
        server.pluginManager.registerEvents(BlockBreak(occurrenceManager), this)
        server.pluginManager.registerEvents(BlockPlace(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityDamage(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityDamageByEntity(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityKill(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerJump(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerFish(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerMove(occurrenceManager), this)
        return
    }

    override fun onDisable() {
        // Disable logic
        if(occurrenceManager.currentOccurrence != null) {
            occurrenceManager.currentOccurrence!!.end()
        }
        return
    }
}