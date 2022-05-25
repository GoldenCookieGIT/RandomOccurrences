package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.EntityDamageByEntityOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class EntityDamageByEntity(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onEntityDamageEntity(event: EntityDamageByEntityEvent){
        val currentOccurrence = occurrenceManager.currentOccurrence ?: return

        if(currentOccurrence is EntityDamageByEntityOccurrence){
            currentOccurrence.onEntityDamageEntity(event)
        }
    }
}