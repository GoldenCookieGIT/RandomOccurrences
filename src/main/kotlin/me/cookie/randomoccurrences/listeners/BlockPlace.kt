package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.BlockPlaceOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class BlockPlace(private val occurrenceManager: OccurrenceManager): Listener {
    // listen for block place events
    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent){
        val currentOccurrence = occurrenceManager.currentOccurrence ?: return

        if(currentOccurrence is BlockPlaceOccurrence){
            currentOccurrence.onBlockPlace(event)
        }
    }
}