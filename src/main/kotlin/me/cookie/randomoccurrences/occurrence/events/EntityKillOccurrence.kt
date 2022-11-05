package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.entity.EntityDeathEvent

interface EntityKillOccurrence {
    fun onEntityKill(event: EntityDeathEvent)
}