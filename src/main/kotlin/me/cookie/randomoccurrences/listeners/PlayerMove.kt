package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.PlayerMoveOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMove(private val occurrenceManager: OccurrenceManager): Listener { // This is a terrible idea
    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent){
        val currentOccurrence = occurrenceManager.currentOccurrence ?: return
        if(currentOccurrence is PlayerMoveOccurrence){
            currentOccurrence.onPlayerMove(event)
        }
    }
}