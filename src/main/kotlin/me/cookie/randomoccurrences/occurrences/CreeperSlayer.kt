package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.EntityKillOccurrence
import org.bukkit.entity.Creeper
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDeathEvent

class CreeperSlayer(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "creeper-slayer",
        plugin.messages.creeperSlayer,
        plugin.messages.creeperSlayerDescription
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