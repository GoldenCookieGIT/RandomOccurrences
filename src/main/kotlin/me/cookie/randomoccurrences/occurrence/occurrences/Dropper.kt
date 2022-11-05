package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.PlayerDropItemOccurrence
import org.bukkit.Material
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.plugin.java.JavaPlugin

class Dropper(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "dropper",
        occurrenceManager.plugin.messages.dropper,
        occurrenceManager.plugin.messages.dropperDescription
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