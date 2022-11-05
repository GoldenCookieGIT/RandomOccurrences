package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.entity.EntityDamageByEntityEvent

interface EntityDamageEntityOccurrence {
    fun onEntityDamageEntity(event: EntityDamageByEntityEvent)
}