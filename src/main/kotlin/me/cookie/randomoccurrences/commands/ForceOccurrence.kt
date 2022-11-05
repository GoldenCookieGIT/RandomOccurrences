package me.cookie.randomoccurrences.commands

import me.cookie.randomoccurrences.occurrence.OccurrenceManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class ForceOccurrence(private val occurrenceManager: OccurrenceManager): CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty())
            return false

        val pickedOccurrence = OccurrenceManager.occurrences.firstOrNull {
            (it.friendlyName == args.joinToString(" "))
        } ?: return false

        occurrenceManager.setOccurrence(pickedOccurrence)

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        return if (args.size == 1) {
            OccurrenceManager.occurrences
                .map { it.friendlyName }
                .filter { it.lowercase().contains(args[0].lowercase()) }
                .toMutableList()
        } else {
            null
        }
    }
}