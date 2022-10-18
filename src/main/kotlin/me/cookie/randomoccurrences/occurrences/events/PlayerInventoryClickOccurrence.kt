package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.inventory.InventoryClickEvent

interface PlayerInventoryClickOccurrence {
    fun onPlayerInventoryClick(event: InventoryClickEvent)
}