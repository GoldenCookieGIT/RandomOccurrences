package me.cookie.util

import org.bukkit.Bukkit
import org.bukkit.entity.Player

class ExecutableCommand(private val command: String, private val executor: Executor, private val ignorePerms: Boolean) {
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