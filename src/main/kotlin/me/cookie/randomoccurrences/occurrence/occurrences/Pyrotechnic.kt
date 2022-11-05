package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.PlayerInteractOccurrence
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin

class Pyrotechnic(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "pyrotechnic",
        occurrenceManager.plugin.messages.pyrotechnic,
        occurrenceManager.plugin.messages.pyrotechnicDescription
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