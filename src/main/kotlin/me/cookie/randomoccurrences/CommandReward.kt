package me.cookie.randomoccurrences

import org.bukkit.Bukkit
import org.bukkit.entity.Player

class CommandReward(private val command: String, private val executor: Executor, private val ignorePerms: Boolean) {
    fun performCommand(player: Player): Boolean {
        val commandString = command.replace("%player_name%", player.name)
        if (executor == Executor.PLAYER) {
            if (ignorePerms)
                Bukkit.getServer().dispatchCommand(player, commandString.replaceFirst("/", ""))
            else
                player.chat(commandString)
            return true
        }

        if (executor == Executor.SERVER) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), commandString.replaceFirst("/", ""))
            return true
        }

        return false // uh oh?
    }
}