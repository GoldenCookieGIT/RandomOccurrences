package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.player.PlayerInteractEvent

interface PlayerInteractOccurrence {
    fun onPlayerInteract(event: PlayerInteractEvent)
}