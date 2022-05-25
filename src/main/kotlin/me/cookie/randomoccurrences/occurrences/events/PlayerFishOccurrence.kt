package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.player.PlayerFishEvent

interface PlayerFishOccurrence {
    fun onPlayerFish(event: PlayerFishEvent)
}