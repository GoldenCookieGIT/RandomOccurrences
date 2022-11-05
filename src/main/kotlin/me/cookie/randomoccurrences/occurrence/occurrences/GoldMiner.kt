package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.BlockBreakOccurence
import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class GoldMiner(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "gold-miner",
        occurrenceManager.plugin.messages.goldMiner,
        occurrenceManager.plugin.messages.goldMinerDescription
    ), BlockBreakOccurence {

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onBlockBreak(event: BlockBreakEvent) {
        if (event.block.type == Material.GOLD_ORE || event.block.type == Material.DEEPSLATE_GOLD_ORE) {
            addScore(event.player, 1)
        }
    }
}