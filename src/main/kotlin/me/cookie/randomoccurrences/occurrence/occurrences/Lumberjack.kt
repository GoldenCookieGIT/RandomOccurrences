package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.BlockBreakOccurence
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class Lumberjack(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "lumberjack",
        occurrenceManager.plugin.messages.lumberjack,
        occurrenceManager.plugin.messages.lumberjackDescription
    ), BlockBreakOccurence {

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