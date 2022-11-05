package me.cookie.randomoccurrences.util

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


    fun reloadConfig() {

    }
}