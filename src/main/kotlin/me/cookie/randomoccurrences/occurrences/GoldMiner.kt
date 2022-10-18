package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.BlockBreakOccurence
import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent

class GoldMiner(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "gold-miner",
        plugin.messages.goldMiner,
        plugin.messages.goldMinerDescription
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