package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.EntityDamageEntityOccurrence
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.plugin.java.JavaPlugin

class DamageDealer(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "damage-dealer",
        occurrenceManager.plugin.messages.damageDealer,
        occurrenceManager.plugin.messages.damageDealerDescription
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