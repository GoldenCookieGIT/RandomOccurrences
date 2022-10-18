package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.EntityDamageOccurrence
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

class Masochist(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "masochist",
        plugin.messages.masochist,
        plugin.messages.masochistDescription
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