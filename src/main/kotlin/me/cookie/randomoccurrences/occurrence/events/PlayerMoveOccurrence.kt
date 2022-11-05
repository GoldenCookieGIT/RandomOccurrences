package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.player.PlayerMoveEvent

interface PlayerMoveOccurrence {
    fun onPlayerMove(event: PlayerMoveEvent)
}