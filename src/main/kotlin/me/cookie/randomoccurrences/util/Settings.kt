package me.cookie.randomoccurrences.util

import me.cookie.randomoccurrences.occurrence.Occurrence
import me.cookie.randomoccurrences.occurrence.Reward

class Settings {
    val map = HashMap<String, String>()

    val updateChecker
        get() = map["update-checker"]!!

    val bossBarEnabled
        get() = map["bossbar"]!!.toBoolean()

    val minimumPlayers
        get() = map["minimum-players"]!!.toInt()

    val downTime
        get() = map["down-time"]!!.toInt()

    val worldBlacklist
        get() = map["world-blacklist"]!!.split("\n")

    var rewards = HashMap<Occurrence, HashMap<Int, Array<Reward>>>() // Leaderboard place -> rewards

    fun reload() {
        /* TODO:
        Recompile config
        Recompile Start/End events

        Get the rest of the options from the config
         */
    }
}