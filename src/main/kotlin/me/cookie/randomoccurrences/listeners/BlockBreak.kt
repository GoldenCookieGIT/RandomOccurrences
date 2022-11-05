package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.BlockBreakOccurence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreak(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val occurrence = occurrenceManager.currentOccurrence ?: return
        if (occurrence is BlockBreakOccurence) {
            occurrence.onBlockBreak(event)
        }
    }
}