package me.cookie.randomoccurrences.listeners

import com.google.common.collect.Sets
import me.cookie.randomoccurrences.OccurrenceManager
import me.cookie.randomoccurrences.PlayerJumpEvent
import me.cookie.randomoccurrences.occurrences.events.PlayerMoveOccurrence
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import java.util.*


class PlayerMove(private val occurrenceManager: OccurrenceManager): Listener { // This is a terrible idea

    private val prevPlayersOnGround: MutableSet<UUID> = Sets.newHashSet()

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent){
        val player = event.player

        if (player.velocity.y > 0) {
            var jumpVelocity = 0.41999998688697815
            if (player.hasPotionEffect(PotionEffectType.JUMP)) {
                jumpVelocity += ((player.getPotionEffect(PotionEffectType.JUMP)!!.amplifier + 1).toFloat() * 0.1f).toDouble()
            }
            if (player.location.block.type != Material.LADDER && prevPlayersOnGround.contains(player.uniqueId)) {
                if (!player.isOnGround && player.velocity.y.compareTo(jumpVelocity) == 0) {
                    occurrenceManager.plugin.server.pluginManager.callEvent(PlayerJumpEvent(player))
                }
            }
        }
        if (player.isOnGround) {
            prevPlayersOnGround.add(player.uniqueId)
        } else {
            prevPlayersOnGround.remove(player.uniqueId)
        }

        val currentOccurrence = occurrenceManager.currentOccurrence ?: return
        if(currentOccurrence is PlayerMoveOccurrence){
            currentOccurrence.onPlayerMove(event)
        }
    }
}