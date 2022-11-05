package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.block.BlockPlaceEvent

interface BlockPlaceOccurrence {
    fun onBlockPlace(event: BlockPlaceEvent)
}