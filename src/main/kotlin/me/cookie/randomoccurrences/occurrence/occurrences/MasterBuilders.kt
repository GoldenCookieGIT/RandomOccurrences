package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.BlockPlaceOccurrence
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.plugin.java.JavaPlugin

class MasterBuilders(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "master-builders",
        occurrenceManager.plugin.messages.masterBuilders,
        occurrenceManager.plugin.messages.masterBuildersDescription
    ), BlockPlaceOccurrence {

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