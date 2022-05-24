package me.cookie.randomoccurrences

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

abstract class Occurrence(val plugin: JavaPlugin, val occurrenceManager: OccurrenceManager) {
    abstract val configName: String

    abstract fun occur() // start logic, occurrence specific
    abstract fun cleanup() // cleanup logic, occurrence specific

    val playerScore = mutableMapOf<UUID, Int>()
    val isEnabled = plugin.config.getBoolean("occurrences.$configName.enabled")
    /* length in minutes of this occurrence */
    private val time: Long = plugin.config.getInt("occurrences.$configName.time").toLong() * 1200 /* from minutes to ticks */
    val rewardMap: Map<Int /* leaderboard place */, List<ItemStack> /* rewards to give */> = occurrenceManager.getRewards(configName)

    fun start() {
        occurrenceManager.currentOccurrence = this
        startTimer()
        occur()
    }

    fun end(){
        // Give rewards
        // Send victory message
        cleanup()
        playerScore.clear()
        occurrenceManager.startDowntime()
        occurrenceManager.currentOccurrence = null
    }

    fun addScore(player: Player, score: Int){
        if(!playerScore.containsKey(player.uniqueId)){
            playerScore[player.uniqueId] = 0
        }
        playerScore[player.uniqueId] = playerScore[player.uniqueId]!! + score
    }

    private fun startTimer(){
        object: BukkitRunnable() {
            override fun run() {
                end()
            }
        }.runTaskLater(plugin, time)
    }
}