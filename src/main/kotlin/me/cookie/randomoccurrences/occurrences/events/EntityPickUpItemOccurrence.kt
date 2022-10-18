package me.cookie.randomoccurrences.occurrences.events

import org.bukkit.event.entity.EntityPickupItemEvent

interface EntityPickUpItemOccurrence {
    fun onEntityPickUpItem(event: EntityPickupItemEvent)
}