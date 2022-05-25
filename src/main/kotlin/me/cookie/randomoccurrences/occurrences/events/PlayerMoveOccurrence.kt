package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.player.PlayerMoveEvent

interface PlayerMoveOccurrence {
    fun onPlayerMove(event: PlayerMoveEvent)
}