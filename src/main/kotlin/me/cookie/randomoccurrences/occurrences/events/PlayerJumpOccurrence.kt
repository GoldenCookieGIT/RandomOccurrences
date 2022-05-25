package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.entity.Player

interface PlayerJumpOccurrence {
    fun onPlayerJump(player: Player)
}