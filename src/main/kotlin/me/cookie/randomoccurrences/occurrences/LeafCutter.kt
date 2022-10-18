package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.BlockBreakOccurence
import org.bukkit.event.block.BlockBreakEvent

class LeafCutter(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "leaf-cutter", plugin.messages.leafCutter), BlockBreakOccurence {

    override var description: List<String> = plugin.messages.leafCutterDescription
    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onBlockBreak(event: BlockBreakEvent) {
        if (!event.block.type.toString().contains("LEAVES")) return
        addScore(event.player, 1)
    }
}