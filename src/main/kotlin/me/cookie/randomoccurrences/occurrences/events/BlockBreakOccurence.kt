package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.block.BlockBreakEvent

interface BlockBreakOccurence {
    fun onBlockBreak(event: BlockBreakEvent)
}