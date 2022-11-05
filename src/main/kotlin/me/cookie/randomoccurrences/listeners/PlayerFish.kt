package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.PlayerFishOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent

class PlayerFish(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onPlayerFish(event: PlayerFishEvent) {
        val currentOccurrence = occurrenceManager.currentOccurrence
        if (currentOccurrence is PlayerFishOccurrence) {
            currentOccurrence.onPlayerFish(event)
        }
    }
}