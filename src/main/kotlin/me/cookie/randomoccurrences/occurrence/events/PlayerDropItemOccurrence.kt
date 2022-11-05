package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.player.PlayerDropItemEvent

interface PlayerDropItemOccurrence {
    fun onPlayerDropItem(event: PlayerDropItemEvent)
}