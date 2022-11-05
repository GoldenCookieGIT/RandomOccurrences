package me.cookie.randomoccurrences.occurrence

import org.bukkit.entity.Player

class CommandReward(private val executableCommand: ExecutableCommand) : Reward {
    override fun reward(player: Player) {
        executableCommand.performCommand(player)
    }
}