package me.cookie.randomoccurrences.listeners

import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.getVersion
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoin(private val randomOccurrences: RandomOccurrences): Listener {
    private val versionCheck = randomOccurrences.config.getString("update-checker")?.uppercase()
    private val version = randomOccurrences.description.version
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        if (!player.isOp) return
        getVersion(randomOccurrences, "102217") {
            if (versionCheck == ("PATCH")) {
                if (it != version) {
                    warnOutdated(player, it)
                }
            } else {
                if ("${it.split('.')[0]}.${it.split('.')[1]}" != "${version.split('.')[0]}.${version.split('.')[1]}") {
                    warnOutdated(player, it)
                }
            }
        }
    }

    fun warnOutdated(player: Player, latest: String) {
        player.sendMessage("${ChatColor.RED}[RandomOccurrences] You are using an outdated version")
        player.sendMessage("")
        player.sendMessage("${ChatColor.RED}Current: {${randomOccurrences.description.version}} Latest: {$latest}")
        player.sendMessage("")
        player.sendMessage("${ChatColor.RED}Consider downloading the latest version as it contains the latest features and fixes.")
        player.sendMessage("")
        player.sendMessage("${ChatColor.RED}You can find the latest version at: https://www.spigotmc.org/resources/randomoccurrences.102217/")
    }
}