package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.BlockPlaceOccurrence
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.plugin.java.JavaPlugin

class MasterBuilders(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "master-builders"), BlockPlaceOccurrence {
    override val friendlyName: String = "Master Builders"
    override val description: List<String> = listOf(
        "#4d4d4dPlace the most blocks to win!",
    )

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onBlockPlace(event: BlockPlaceEvent) {
        addScore(event.player, 1)
    }
}