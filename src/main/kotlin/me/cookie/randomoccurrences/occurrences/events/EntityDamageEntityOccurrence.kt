package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.entity.EntityDamageByEntityEvent

interface EntityDamageEntityOccurrence {
    fun onEntityDamageEntity(event: EntityDamageByEntityEvent)
}