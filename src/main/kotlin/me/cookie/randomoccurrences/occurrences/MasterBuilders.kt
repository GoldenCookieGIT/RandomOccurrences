package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.BlockPlaceOccurrence
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.plugin.java.JavaPlugin

class MasterBuilders(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager), BlockPlaceOccurrence {
    override val configName: String
        get() = "master-builders"
    override val friendlyName: String
        get() = "Master Builders"

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