package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.PlayerInteractOccurrence
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin

class DJ(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "dj",
        occurrenceManager.plugin.messages.dj,
        occurrenceManager.plugin.messages.djDescription
    ), PlayerInteractOccurrence {

    override fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.clickedBlock?.type == Material.NOTE_BLOCK)
            addScore(event.player, 1)
    }

    override fun occur() { return }

    override fun cleanup() { return }
}