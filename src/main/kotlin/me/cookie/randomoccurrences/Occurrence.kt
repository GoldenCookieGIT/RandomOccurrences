package me.cookie.randomoccurrences

import org.bukkit.Bukkit
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
    val rewardMap: Map<Int /* leaderboard place */, Array<ItemStack> /* rewards to give */> = occurrenceManager.getRewards(configName)

    fun start() {
        occurrenceManager.currentOccurrence = this
        Bukkit.getServer().onlinePlayers.forEach {
            it.sendMessage("started $configName")
        }
        startTimer()
        occur()
    }

    fun end(){
        // Give rewards
        // Send victory message
        val sortedMap = playerScore.toList().sortedBy { (_, value) -> value }.toMap()
        var place = 1
        sortedMap.forEach { (uuid, score) ->
            place++
            val player = Bukkit.getPlayer(uuid) ?: return@forEach
            if(rewardMap.containsKey(place)) {
                player.inventory.addItem(*rewardMap[place]!!)
                player.sendMessage("You have won the $configName occurrence with score $score!")
            }else if(score != 0 && true /* TODO: check for if participation awards are enabled */){
                player.sendMessage("You got a participation award!")
            }else{
                player.sendMessage("You did not participate in the occurrence")
            }
        }

        Bukkit.getServer().onlinePlayers.forEach {
            it.sendMessage("ended $configName")
        }
        cleanup()
        playerScore.clear()
        occurrenceManager.currentOccurrence = null
        occurrenceManager.startDowntime()
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