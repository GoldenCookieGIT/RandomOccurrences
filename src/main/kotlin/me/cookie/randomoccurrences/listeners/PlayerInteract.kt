package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.PlayerInteractOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteract(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val currentOccurrence = occurrenceManager.currentOccurrence
        if (currentOccurrence is PlayerInteractOccurrence) {
            currentOccurrence.onPlayerInteract(event)
        }
    }
}