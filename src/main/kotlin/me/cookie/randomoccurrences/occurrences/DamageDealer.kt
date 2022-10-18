package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.EntityDamageEntityOccurrence
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageDealer(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "damage-dealer",
        plugin.messages.damageDealer,
        plugin.messages.damageDealerDescription
    ), EntityDamageEntityOccurrence {

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