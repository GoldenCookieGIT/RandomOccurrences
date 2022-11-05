package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.PlayerFishOccurrence
import org.bukkit.entity.Item
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.plugin.java.JavaPlugin

class FishingPros(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "fishing-pros",
        occurrenceManager.plugin.messages.fishingPros,
        occurrenceManager.plugin.messages.fishingProsDescription
    ), PlayerFishOccurrence {

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onPlayerFish(event: PlayerFishEvent) {
        if (event.caught !is Item) return

        addScore(event.player, 1)
    }

}