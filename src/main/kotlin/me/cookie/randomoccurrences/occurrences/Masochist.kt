package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.EntityDamageOccurrence
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.plugin.java.JavaPlugin

class Masochist(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager), EntityDamageOccurrence {
    override val configName: String = "masochist"
    override val friendlyName: String = "Masochist"
    override val description: List<String> = listOf(
        "#4d4d4dTake the most damage to win!",
    )

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onEntityDamage(event: EntityDamageEvent) {
        val player = event.entity
        if (player !is Player) return

        addScore(player, event.damage.toInt())
    }
}