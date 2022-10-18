package me.cookie.randomoccurrences.commands

import me.cookie.randomoccurrences.RandomOccurrences
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ReloadMessages(private val plugin: RandomOccurrences): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        sender.sendMessage("Reloading messages.yml...")
        plugin.loadMessages()
        sender.sendMessage("Done!")
        return true
    }
}