package me.cookie.randomoccurrences.placeholders

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.cookie.randomoccurrences.RandomOccurrences
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer

class PAPIPlaceholders(private val plugin: RandomOccurrences): PlaceholderExpansion() {
    override fun getIdentifier(): String {
        return "ro"
    }

    override fun getAuthor(): String {
        return "Cookie#8723"
    }

    override fun getVersion(): String {
        return plugin.description.version
    }

    override fun getRequiredPlugin(): String {
        return "RandomOccurrences"
    }

    override fun onRequest(offlinePlayer: OfflinePlayer?, params: String): String {
        println("$params requested")
        when (params) {
            "occurrence_name" -> {
                return plugin.occurrenceManager.currentOccurrence?.friendlyName ?: "None"
            }
            "occurrence_description" -> {
                return plugin.occurrenceManager.currentOccurrence?.description?.joinToString { it + '\n' } ?: ""
            }
        }

        if (params.endsWith("_score")) {
            println("score requested of ${params.substringBefore("_score")}")
            val player = Bukkit.getPlayer(params.substringBefore("_score")) ?: return "No Player Found"

            return plugin.occurrenceManager.currentOccurrence?.playerScore?.get(player.uniqueId)?.toString() ?: "-1"
        }

        if (params.endsWith("_place")) {
            val player = Bukkit.getPlayer(params.substringBefore("_place")) ?: return "No Player Found"

            return plugin.occurrenceManager.currentOccurrence
                ?.playerScore
                ?.entries
                ?.sortedByDescending { it.value }
                ?.indexOfFirst { it.key == player.uniqueId }
                ?.plus(1)
                ?.toString() ?: "-1"
        }
        return "null"
    }
}