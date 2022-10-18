package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.BlockPlaceOccurrence
import org.bukkit.event.block.BlockPlaceEvent

class MasterBuilders(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "master-builders",
        plugin.messages.masterBuilders,
        plugin.messages.masterBuildersDescription
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