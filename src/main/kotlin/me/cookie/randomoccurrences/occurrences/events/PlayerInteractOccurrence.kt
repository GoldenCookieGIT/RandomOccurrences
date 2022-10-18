package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.player.PlayerInteractEvent

interface PlayerInteractOccurrence {
    fun onPlayerInteract(event: PlayerInteractEvent)
}