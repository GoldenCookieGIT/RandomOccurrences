package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.EntityPickUpItemOccurrence
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.plugin.java.JavaPlugin

class Hoarder(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "hoarder",
        occurrenceManager.plugin.messages.hoarder,
        occurrenceManager.plugin.messages.hoarderDescription
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