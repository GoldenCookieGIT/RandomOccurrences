package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.block.BlockBreakEvent

interface BlockBreakOccurence {
    fun onBlockBreak(event: BlockBreakEvent)
}