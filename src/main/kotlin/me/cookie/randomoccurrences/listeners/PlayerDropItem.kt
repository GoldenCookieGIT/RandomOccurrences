package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.PlayerDropItemOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent

class PlayerDropItem(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        println("dropped item")
        val currentOccurrence = occurrenceManager.currentOccurrence
        if (currentOccurrence is PlayerDropItemOccurrence) {
            currentOccurrence.onPlayerDropItem(event)
        }
    }
}