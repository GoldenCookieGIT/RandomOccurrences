package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.PlayerJumpOccurrence
import org.bukkit.entity.Player

class Jumper(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "jumper", plugin.messages.jumper), PlayerJumpOccurrence {

    override var description: List<String> = plugin.messages.jumperDescription

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onPlayerJump(player: Player) {
        addScore(player, 1)
    }
}