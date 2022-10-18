package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.PlayerFishOccurrence
import org.bukkit.entity.Item
import org.bukkit.event.player.PlayerFishEvent

class FishingPros(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "fishing-pros", plugin.messages.fishingPros),
    PlayerFishOccurrence {

    override var description: List<String> = plugin.messages.fishingProsDescription

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