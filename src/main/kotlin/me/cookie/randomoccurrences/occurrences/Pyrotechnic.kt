package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.PlayerInteractOccurrence
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent

class Pyrotechnic(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "pyrotechnic",
        plugin.messages.pyrotechnic,
        plugin.messages.pyrotechnicDescription
    ), PlayerInteractOccurrence {

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onPlayerInteract(event: PlayerInteractEvent) {
        val item = event.item ?: return
        val block = event.clickedBlock ?: return

        if (block.type.name.contains("AIR")) return

        if (item.type == Material.FLINT_AND_STEEL || item.type == Material.FIRE_CHARGE)
            addScore(event.player, 1)
    }
}