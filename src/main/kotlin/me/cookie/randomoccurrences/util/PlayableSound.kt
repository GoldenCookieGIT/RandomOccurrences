package me.cookie.randomoccurrences.util

import org.bukkit.Sound
import org.bukkit.entity.Player

class PlayableSound(private val sound: Sound, private val volume: Float, private val pitch: Float) {
    fun play(player: Player) {
       player.playSound(player.location, sound, volume, pitch)
    }
}