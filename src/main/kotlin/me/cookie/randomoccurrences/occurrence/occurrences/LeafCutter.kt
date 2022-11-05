package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.BlockBreakOccurence
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class LeafCutter(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "leaf-cutter",
        occurrenceManager.plugin.messages.leafCutter,
        occurrenceManager.plugin.messages.leafCutterDescription
    ), BlockBreakOccurence {

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