package me.cookie.randomoccurrences.occurrence

import org.bukkit.entity.Player

interface Reward {
    fun reward(player: Player)
}