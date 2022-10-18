package me.cookie.randomoccurrences.occurrences

import me.cookie.randomoccurrences.Occurrence
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.occurrences.events.PlayerInventoryClickOccurrence
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType

class Smelter(plugin: RandomOccurrences, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "smelter",
        plugin.messages.smelter,
        plugin.messages.smelterDescription
    ), PlayerInventoryClickOccurrence {

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onPlayerInventoryClick(event: InventoryClickEvent) {
        if (event.click != ClickType.LEFT) return
        if (event.whoClicked !is Player) return
        val invType = event.clickedInventory?.type ?: return
        if (invType != InventoryType.FURNACE &&
            invType != InventoryType.BLAST_FURNACE &&
            invType != InventoryType.SMOKER) return
        if (event.slot != 2) return
        val item = event.currentItem ?: return
        if (item.type == Material.AIR) return

        addScore(event.whoClicked as Player, item.amount)
    }
}