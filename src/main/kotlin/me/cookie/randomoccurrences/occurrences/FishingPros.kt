package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.PlayerFishOccurrence
import org.bukkit.entity.Item
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.plugin.java.JavaPlugin

class FishingPros(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "fishing-pros", "Fishing Pros"),
    PlayerFishOccurrence {

    override val description: List<String> = listOf(
        "#4d4d4dFish up the most items to win!",
    )

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