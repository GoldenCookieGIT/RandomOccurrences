package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.entity.EntityDamageByEntityEvent

interface EntityDamageByEntityOccurrence {
    fun onEntityDamageEntity(event: EntityDamageByEntityEvent)
}