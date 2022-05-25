package me.cookie.randomoccurrences

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

abstract class Occurrence(val plugin: JavaPlugin, val occurrenceManager: OccurrenceManager) {
    abstract val configName: String
    abstract val friendlyName: String

    abstract fun occur() // start logic, occurrence specific
    abstract fun cleanup() // cleanup logic, occurrence specific

    val playerScore = mutableMapOf<UUID, Int?>()
    val isEnabled = plugin.config.getBoolean("occurrences.$configName.enabled")
    val spaces = 13 - friendlyName.length
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
        val sortedMap = playerScore.toList().sortedBy { (_, value) -> value }.reversed().toMap()
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

            val first = try {
                Bukkit.getPlayer(sortedMap.keys.toList()[0])?.name
            }catch (e: IndexOutOfBoundsException){
                "Nobody"
            }
            val firstScore = try {
                sortedMap.values.toList()[0] ?: 0
            }catch (e: IndexOutOfBoundsException){
                0
            }

            val second = try {
                Bukkit.getPlayer(sortedMap.keys.toList()[1])?.name
            }catch (e: IndexOutOfBoundsException){
                "Nobody"
            }
            val secondScore = try {
                sortedMap.values.toList()[1] ?: 0
            }catch (e: IndexOutOfBoundsException){
                0
            }

            val third = try {
                Bukkit.getPlayer(sortedMap.keys.toList()[2])?.name
            }catch (e: IndexOutOfBoundsException){
                "Nobody"
            }
            val thirdScore = try {
                sortedMap.values.toList()[2] ?: 0
            }catch (e: IndexOutOfBoundsException){
                0
            }

            player.sendMessage("${ChatColor.GOLD}-============================-")
            player.sendMessage("$friendlyName has ended")
            player.sendMessage("")
            player.sendMessage("1. $first - $firstScore")
            player.sendMessage("2. $second - $secondScore")
            player.sendMessage("3. $third - $thirdScore")
            player.sendMessage("")
            player.sendMessage("${ChatColor.GOLD}-============================-")
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