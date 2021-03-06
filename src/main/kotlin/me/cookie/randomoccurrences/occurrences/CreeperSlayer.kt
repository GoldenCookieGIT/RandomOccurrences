package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.EntityKillOccurrence
import org.bukkit.entity.Creeper
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class CreeperSlayer(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "creeper-slayer", "Creeper Slayer"),
    EntityKillOccurrence {

    override val description: List<String> = listOf(
        "#4d4d4dKill the most creepers to win!",
        "#4d4d4dCreepers are worth 1 point while charged ones are 10!"
    )

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