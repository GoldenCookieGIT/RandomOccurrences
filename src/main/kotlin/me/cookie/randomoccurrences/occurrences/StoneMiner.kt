package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.BlockBreakOccurence
import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class StoneMiner(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(plugin, occurrenceManager, "stone-miner"), BlockBreakOccurence {
    override val friendlyName: String = "Stone Miner"
    override val description: List<String> = listOf(
        "#4d4d4dMine the most stone to win!",
    )

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        val player = event.player
        if (block.type != Material.STONE) return
        addScore(player, 1)
    }

}