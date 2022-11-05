package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.inventory.InventoryClickEvent

interface PlayerInventoryClickOccurrence {
    fun onPlayerInventoryClick(event: InventoryClickEvent)
}