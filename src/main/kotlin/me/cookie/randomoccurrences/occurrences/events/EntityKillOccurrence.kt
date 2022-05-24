package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.entity.EntityDeathEvent

interface EntityKillOccurrence {
    fun onEntityKill(event: EntityDeathEvent)
}