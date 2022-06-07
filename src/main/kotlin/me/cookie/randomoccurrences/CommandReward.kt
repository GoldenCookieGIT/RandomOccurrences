package me.cookie.randomoccurrences

import org.bukkit.Bukkit
import org.bukkit.entity.Player

class CommandReward(private val command: String, private val executor: Executor, private val ignorePerms: Boolean) {
    fun performCommand(player: Player? = null): Boolean {
        if (player != null && executor == Executor.PLAYER) {
            if (ignorePerms)
                Bukkit.getServer().dispatchCommand(player, command.replaceFirst("/", ""))
            else
                player.chat(command)
            return true
        } else if (player == null && executor == Executor.PLAYER) {
            throw IllegalArgumentException("Player cannot be null when Executor is PLAYER")
        }

        if (executor == Executor.CONSOLE) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command)
            return true
        }

        return false // uh oh?
    }
}