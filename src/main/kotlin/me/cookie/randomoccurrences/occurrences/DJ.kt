package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.PlayerInteractOccurrence
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent

class DJ(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "dj",
        plugin.messages.dj,
        plugin.messages.djDescription
    ), PlayerInteractOccurrence {

    override fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.clickedBlock?.type == Material.NOTE_BLOCK)
            addScore(event.player, 1)
    }

    override fun occur() {}

    override fun cleanup() {}
}