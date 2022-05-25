package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.PlayerJumpOccurrence
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Jumper(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager), PlayerJumpOccurrence{
    override val configName: String
        get() = "jumper"
    override val friendlyName: String
        get() = "Jumper"

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