package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.entity.EntityDamageEvent

interface EntityDamageOccurrence {
    fun onEntityDamage(event: EntityDamageEvent)
}