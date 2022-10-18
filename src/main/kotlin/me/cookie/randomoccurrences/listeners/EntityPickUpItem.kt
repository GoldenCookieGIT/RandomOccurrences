package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.EntityPickUpItemOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent

class EntityPickUpItem(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onEntityPickUpItem(event: EntityPickupItemEvent) {
        val currentOccurrence = occurrenceManager.currentOccurrence
        if (currentOccurrence is EntityPickUpItemOccurrence) {
            currentOccurrence.onEntityPickUpItem(event)
        }
    }
}