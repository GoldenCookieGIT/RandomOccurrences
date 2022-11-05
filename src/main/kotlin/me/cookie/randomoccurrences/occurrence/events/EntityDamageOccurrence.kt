package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.entity.EntityDamageEvent

interface EntityDamageOccurrence {
    fun onEntityDamage(event: EntityDamageEvent)
}