package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.PlayerInventoryClickOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class PlayerInventoryClick(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onPlayerInventoryClick(event: InventoryClickEvent) {
        val currentOccurrence = occurrenceManager.currentOccurrence
        if (currentOccurrence is PlayerInventoryClickOccurrence) {
            currentOccurrence.onPlayerInventoryClick(event)
        }
    }
}