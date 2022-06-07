package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.EntityDamageEntityOccurrence
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.plugin.java.JavaPlugin

class DamageDealer(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager), EntityDamageEntityOccurrence {
    override val configName: String = "damage-dealer"
    override val friendlyName: String = "Damage Dealer"
    override val description: List<String> = listOf(
        "#4d4d4dDeal the most damage to win!",
    )

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onEntityDamageEntity(event: EntityDamageByEntityEvent) {
        if (event.damager !is Player) return
        addScore(event.damager as Player, event.damage.toInt())
    }
}