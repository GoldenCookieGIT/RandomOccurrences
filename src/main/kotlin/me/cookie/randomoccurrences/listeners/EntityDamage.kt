package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.EntityDamageOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class EntityDamage(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        val currentOccurrence = occurrenceManager.currentOccurrence ?: return
        if (currentOccurrence is EntityDamageOccurrence) {
            currentOccurrence.onEntityDamage(event)
        }
    }
}