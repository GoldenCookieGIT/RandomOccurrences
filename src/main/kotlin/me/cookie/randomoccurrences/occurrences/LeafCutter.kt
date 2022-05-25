package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.BlockBreakOccurence
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class LeafCutter(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager), BlockBreakOccurence{
    override val configName: String
        get() = "leaf-cutter"
    override val friendlyName: String
        get() = "Leaf Cutter"
    override val description: List<String>
        get() = listOf(
            "#4d4d4dBreak the most leaves to win!",
        )
    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onBlockBreak(event: BlockBreakEvent) {
        if(!event.block.type.toString().contains("LEAVES")) return
        addScore(event.player, 1)
    }
}