package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.EntityKillOccurrence
import org.bukkit.entity.Creeper
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class CreeperSlayer(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "creeper-slayer",
        occurrenceManager.plugin.messages.creeperSlayer,
        occurrenceManager.plugin.messages.creeperSlayerDescription
    ), EntityKillOccurrence {

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onEntityKill(event: EntityDeathEvent) {
        val entity = event.entity
        val killer = entity.killer ?: return

        if (entity.type != EntityType.CREEPER) return
        entity as Creeper
        if (entity.isPowered) {
            addScore(killer, 10)
        } else{
            addScore(killer, 1)
        }
    }
}