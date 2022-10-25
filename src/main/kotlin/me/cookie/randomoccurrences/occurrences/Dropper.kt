package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.PlayerDropItemOccurrence
import org.bukkit.Material
import org.bukkit.event.player.PlayerDropItemEvent

class Dropper(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "dropper",
        plugin.messages.dropper,
        plugin.messages.dropperDescription
    ), PlayerDropItemOccurrence {

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onPlayerDropItem(event: PlayerDropItemEvent) {
        val item = event.itemDrop.itemStack
        if (item.type == Material.AIR) return

        addScore(event.player, 1)
    }
}