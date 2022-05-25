package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.PlayerFishOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent

class PlayerFish(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onPlayerFish(event: PlayerFishEvent){
        val currentOccurrence = occurrenceManager.currentOccurrence
        if(currentOccurrence is PlayerFishOccurrence){
            currentOccurrence.onPlayerFish(event)
        }
    }
}