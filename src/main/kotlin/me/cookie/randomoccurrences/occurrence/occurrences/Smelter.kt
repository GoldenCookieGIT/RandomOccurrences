package me.cookie.randomoccurrences.occurrence.occurrences

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import me.cookie.randomoccurrences.occurrence.events.PlayerInventoryClickOccurrence
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.plugin.java.JavaPlugin
import kotlin.math.ceil

class Smelter(plugin: JavaPlugin, occurrenceManager: OccurrenceManager):
    Occurrence(
        plugin,
        occurrenceManager,
        "smelter",
        occurrenceManager.plugin.messages.smelter,
        occurrenceManager.plugin.messages.smelterDescription
    ), PlayerInventoryClickOccurrence {

    override fun occur() {
        return
    }

    override fun cleanup() {
        return
    }

    override fun onPlayerInventoryClick(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        val invType = event.clickedInventory?.type ?: return
        if (invType != InventoryType.FURNACE &&
            invType != InventoryType.BLAST_FURNACE &&
            invType != InventoryType.SMOKER) return
        if (event.slot != 2) return
        val item = event.currentItem ?: return
        if (item.type == Material.AIR) return

        addScore(event.whoClicked as Player,
            if (event.click == ClickType.RIGHT) ceil(item.amount.toFloat() * 0.5f).toInt()
            else item.amount
        )
    }
}