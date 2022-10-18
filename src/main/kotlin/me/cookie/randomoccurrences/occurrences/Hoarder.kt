package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.EntityPickUpItemOccurrence
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityPickupItemEvent

class Hoarder(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "hoarder",
        plugin.messages.hoarder,
        plugin.messages.hoarderDescription
    ), EntityPickUpItemOccurrence {

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onEntityPickUpItem(event: EntityPickupItemEvent) {
        if (event.entity !is Player) return
        val item = event.item.itemStack
        if (item.type == Material.AIR) return

        addScore(event.entity as Player, 1)
    }
}