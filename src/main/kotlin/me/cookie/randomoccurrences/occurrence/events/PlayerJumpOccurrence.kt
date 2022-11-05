package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.entity.Player

interface PlayerJumpOccurrence {
    fun onPlayerJump(player: Player)
}