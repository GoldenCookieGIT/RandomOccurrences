package me.cookie.randomoccurrences

import me.cookie.randomoccurrences.listeners.EntityKill
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class RandomOccurrences: JavaPlugin(), Listener {
    lateinit var occurrenceManager: OccurrenceManager

    override fun onEnable() {
        // Enable logic
        occurrenceManager = OccurrenceManager(this)
        saveDefaultConfig()
        server.pluginManager.registerEvents(this, this)
        server.pluginManager.registerEvents(EntityKill(occurrenceManager), this)
        return
    }

    override fun onDisable() {
        // Disable logic
        if(occurrenceManager.currentOccurrence != null) {
            occurrenceManager.currentOccurrence!!.end()
        }
        return
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        object: BukkitRunnable() { // pick a random occurrence for debugging
            override fun run() {
                occurrenceManager.pickOccurrence()
            }
        }.runTaskLater(this, 20)
        return
    }
}