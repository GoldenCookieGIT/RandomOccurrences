package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.EntityDamageOccurrence
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.plugin.java.JavaPlugin

class Masochist(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "masochist",
        occurrenceManager.plugin.messages.masochist,
        occurrenceManager.plugin.messages.masochistDescription
    ), EntityDamageOccurrence {

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