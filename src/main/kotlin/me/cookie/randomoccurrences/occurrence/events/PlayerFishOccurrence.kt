package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.player.PlayerFishEvent

interface PlayerFishOccurrence {
    fun onPlayerFish(event: PlayerFishEvent)
}