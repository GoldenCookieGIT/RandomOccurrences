package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.PlayerJumpOccurrence
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Jumper(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "jumper",
        occurrenceManager.plugin.messages.jumper,
        occurrenceManager.plugin.messages.jumperDescription
    ), PlayerJumpOccurrence {

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