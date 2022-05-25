package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.block.BlockPlaceEvent

interface BlockPlaceOccurrence {
    fun onBlockPlace(event: BlockPlaceEvent)
}