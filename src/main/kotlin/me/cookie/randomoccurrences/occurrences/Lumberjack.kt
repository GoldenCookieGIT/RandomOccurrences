package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.BlockBreakOccurence
import org.bukkit.event.block.BlockBreakEvent

class Lumberjack(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "lumberjack", plugin.messages.lumberjack), BlockBreakOccurence {

    override var description: List<String> = plugin.messages.lumberjackDescription

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onBlockBreak(event: BlockBreakEvent) {
        if (!event.block.type.name.contains("LOG")) return
        addScore(event.player, 1)
    }
}