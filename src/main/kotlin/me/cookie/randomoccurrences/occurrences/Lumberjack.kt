package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.BlockBreakOccurence
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class Lumberjack(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "lumberjack", "Lumberjack"), BlockBreakOccurence {

    override val description: List<String> = listOf(
        "#4d4d4dChop down the most logs to win!",
    )

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