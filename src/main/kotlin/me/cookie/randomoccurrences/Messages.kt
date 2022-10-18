package me.cookie.randomoccurrences

import org.bukkit.configuration.file.YamlConfiguration

class Messages(private val defaultConfig: YamlConfiguration) {
    val map = HashMap<String, String>()

    // occurrence independent
    val header
        get() = map["header"] ?: defaultConfig.getString("header")!!
    val footer
        get() = map["footer"] ?: defaultConfig.getString("footer")!!
    val occurrenceStart
        get() = map["occurrence-start"] ?: defaultConfig.getString("occurrence-start")!!
    val occurrenceEnd
        get() = map["occurrence-end"] ?: defaultConfig.getString("occurrence-end")!!
    val occurrenceLeaderboard
        get() = (map["occurrence-leaderboard"] ?: defaultConfig.getString("occurrence-leaderboard"))!!.split("\n")

    // occurrence names
    val creeperSlayer
        get() = map["creeper-slayer"] ?: defaultConfig.getString("creeper-slayer")!!
    val damageDealer
        get() = map["damage-dealer"] ?: defaultConfig.getString("damage-dealer")!!
    val fishingPros
        get() = map["fishing-pros"] ?: defaultConfig.getString("fishing-pros")!!
    val goldMiner
        get() = map["gold-miner"] ?: defaultConfig.getString("gold-miner")!!
    val jumper
        get() = map["jumper"] ?: defaultConfig.getString("jumper")!!
    val leafCutter
        get() = map["leaf-cutter"] ?: defaultConfig.getString("leaf-cutter")!!
    val lumberjack
        get() = map["lumberjack"] ?: defaultConfig.getString("lumberjack")!!
    val masochist
        get() = map["masochist"] ?: defaultConfig.getString("masochist")!!
    val masterBuilders
        get() = map["master-builders"] ?: defaultConfig.getString("master-builders")!!
    val stoneMiner
        get() = map["stone-miner"] ?: defaultConfig.getString("stone-miner")!!

    // occurrence descriptions
    val creeperSlayerDescription
        get() = (map["creeper-slayer-description"] ?: defaultConfig.getString("creeper-slayer-description"))!!.split("\n")
    val damageDealerDescription
        get() = (map["damage-dealer-description"] ?: defaultConfig.getString("damage-dealer-description"))!!.split("\n")
    val fishingProsDescription
        get() = (map["fishing-pros-description"] ?: defaultConfig.getString("fishing-pros-description"))!!.split("\n")
    val goldMinerDescription
        get() = (map["gold-miner-description"] ?: defaultConfig.getString("gold-miner-description"))!!.split("\n")
    val jumperDescription
        get() = (map["jumper-description"] ?: defaultConfig.getString("jumper-description"))!!.split("\n")
    val leafCutterDescription
        get() = (map["leaf-cutter-description"] ?: defaultConfig.getString("leaf-cutter-description"))!!.split("\n")
    val lumberjackDescription
        get() = (map["lumberjack-description"] ?: defaultConfig.getString("lumberjack-description"))!!.split("\n")
    val masochistDescription
        get() = (map["masochist-description"] ?: defaultConfig.getString("masochist-description"))!!.split("\n")
    val masterBuildersDescription
        get() = (map["master-builders-description"] ?: defaultConfig.getString("master-builders-description"))!!.split("\n")
    val stoneMinerDescription
        get() = (map["stone-miner-description"] ?: defaultConfig.getString("stone-miner-description"))!!.split("\n")
}