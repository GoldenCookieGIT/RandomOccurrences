package me.cookie.randomoccurrences.occurrence.events

import org.bukkit.event.entity.EntityPickupItemEvent

interface EntityPickUpItemOccurrence {
    fun onEntityPickUpItem(event: EntityPickupItemEvent)
}