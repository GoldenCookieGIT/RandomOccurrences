package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.occurrences.events.EntityKillOccurrence
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class EntityKill(private val occurrenceManager: OccurrenceManager): Listener {
    @EventHandler
    fun onEntityKill(event: EntityDeathEvent) {
        val occurrence = occurrenceManager.currentOccurrence ?: return // return if no occurrence is running
        if(occurrence is EntityKillOccurrence) { // check if occurrence is an EntityKillOccurrence
            occurrence.onEntityKill(event)
        }
    }
}