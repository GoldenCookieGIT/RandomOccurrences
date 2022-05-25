package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.EntityKillOccurrence
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class CreeperSlayer(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager), EntityKillOccurrence {

    override val configName: String
        get() = "creeper-slayer"
    override val friendlyName: String
        get() = "Creeper Slayer"

    override fun occur() {
        Bukkit.getServer().onlinePlayers.stream().forEach { it.sendMessage("YAY!") }
    }

    override fun cleanup() {
        return
    }

    override fun onEntityKill(event: EntityDeathEvent) {
        val entity = event.entity
        val killer = entity.killer ?: return

        if (entity.type != EntityType.CREEPER) return

        killer.sendMessage("YOU FUCKER!!")

        addScore(killer, 1)
    }
}