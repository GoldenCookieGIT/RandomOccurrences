package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.PlayerJumpEvent
import me.cookie.randomoccurrences.occurrences.events.PlayerJumpOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerJump(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onPlayerJump(event: PlayerJumpEvent) {
        val currentOccurrence = occurrenceManager.currentOccurrence ?: return
        if (currentOccurrence is PlayerJumpOccurrence) {
            currentOccurrence.onPlayerJump(event.player)
        }
    }
}