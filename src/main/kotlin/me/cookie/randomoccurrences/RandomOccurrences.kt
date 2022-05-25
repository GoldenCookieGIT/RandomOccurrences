package me.cookie.randomoccurrences

import me.cookie.randomoccurrences.listeners.*
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

    var started = false

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if(started) return
        started = true
        event.player.inventory.addItem(*OccurrenceManager.items.values.toTypedArray()) // add all items to players inventory, for debugging purposes
        object: BukkitRunnable() { // pick a random occurrence for debugging
            override fun run() {
                occurrenceManager.pickOccurrence()
            }
        }.runTaskLater(this, 20)
        return
    }
}