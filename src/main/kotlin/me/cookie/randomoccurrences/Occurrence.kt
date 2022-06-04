package me.cookie.randomoccurrences

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

abstract class Occurrence(val plugin: JavaPlugin, val occurrenceManager: OccurrenceManager) {
    abstract val configName: String
    abstract val friendlyName: String
    abstract val description: List<String>
    abstract fun occur() // start logic, occurrence specific
    abstract fun cleanup() // cleanup logic, occurrence specific

    val playerScore = mutableMapOf<UUID, Int?>()
    val isEnabled = plugin.config.getBoolean("occurrences.$configName.enabled")
    val giveParticipationAwards = plugin.config.getStringList("occurrences.$configName.participation-awards").isNotEmpty()
    // val spaces = 13 - friendlyName.length
    /* length in minutes of this occurrence */
    private val time: Long = plugin.config.getInt("occurrences.$configName.time").toLong() * 1200 /* from minutes to ticks */
    val rewardMap: Map<Int /* leaderboard place */, Array<Reward> /* rewards to give */> = occurrenceManager.getRewards(configName)

    fun start() {
        occurrenceManager.currentOccurrence = this
        Bukkit.getServer().onlinePlayers.forEach { player ->
            player.sendMessage("#4a4a4a--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--".formatHexColors())
            player.sendMessage("       #07f543$friendlyName #808080has started".formatHexColors())
            description.forEach {
                player.sendMessage("    #808080$it".formatHexColors())
            }
            player.sendMessage("")
            player.sendMessage("#4a4a4a--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--".formatHexColors())
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
                rewardMap[place]?.forEach {
                    it.itemReward?.giveItem(player)
                    it.commandReward?.performCommand(player)
                }
            }else if(score != 0 && giveParticipationAwards){
                plugin.config.getStringList("occurrences.$configName.participation-awards").forEach {
                    OccurrenceManager.items[it]?.giveItem(player)
                    OccurrenceManager.commands[it]?.performCommand(player)
                }
            }

            val first = sortedMap.keys.toList().getOrNull(0)?.let { Bukkit.getPlayer(it)?.name } ?: "Nobody"
            val firstScore = sortedMap.values.toList().getOrElse(0) { 0 } ?: 0

            val second = sortedMap.keys.toList().getOrNull(1)?.let { Bukkit.getPlayer(it)?.name } ?: "Nobody"
            val secondScore = sortedMap.values.toList().getOrElse(1) { 0 } ?: 0

            val third = sortedMap.keys.toList().getOrNull(2)?.let { Bukkit.getPlayer(it)?.name } ?: "Nobody"
            val thirdScore = sortedMap.values.toList().getOrElse(2) { 0 } ?: 0

            player.sendMessage("#4a4a4a--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--".formatHexColors())
            player.sendMessage("       #07f543$friendlyName #808080has ended".formatHexColors())
            player.sendMessage("")
            player.sendMessage("#f0c711 1#b5b5b5. #f0c711$first#b5b5b5 - #ea00ff$firstScore".formatHexColors())
            player.sendMessage("#b5b5b5 2#b5b5b5. #b5b5b5$second#b5b5b5 - #ea00ff$secondScore".formatHexColors())
            player.sendMessage("#CD7F32 3#b5b5b5. #CD7F32$third#b5b5b5 - #ea00ff$thirdScore".formatHexColors())
            player.sendMessage("")
            player.sendMessage("#4a4a4a--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--".formatHexColors())
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