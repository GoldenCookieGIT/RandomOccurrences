package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.player.PlayerDropItemEvent

interface PlayerDropItemOccurrence {
    fun onPlayerDropItem(event: PlayerDropItemEvent)
}