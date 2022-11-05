package me.cookie.randomoccurrences.placeholders.placeholdermanagers

import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.placeholders.PlaceholderManager
import me.cookie.randomoccurrences.util.logger
import org.bukkit.entity.Player

class DefaultPlaceholderManager(private val plugin: RandomOccurrences): PlaceholderManager() {
    init {
        logger.info("PlaceholderAPI not found, placeholderapi-specific placeholders will not work")
    }

    override fun setPlaceholders(player: Player?, message: String): String {
        TODO("Not yet implemented")
    }

    override fun containsPlaceholders(message: String): Boolean {
        return placeholders.any { message.contains(it) }
    }

    private val placeholders = arrayOf(
        "(playerName)",
        "%player_name%", // PlaceholderAPI placeholder lol, requires the Player expansion (most servers have it [hopefully])
        "(friendlyName)",
        "%ro_occurrence_friendly_name%", // Gets the name of the current occurrence
        "(description)",
        "%ro_occurrence_description%", // Gets the description of the current occurrence
        "(first)",
        "%ro_leaderboard_1_player%", // Gets the player name of the specified leaderboard place
        "(second)",
        "%ro_leaderboard_2_player%",
        "(third)",
        "%ro_leaderboard_3_player%",
        "(firstScore)",
        "%ro_leaderboard_{%ro_leaderboard_1_player%}_score%",
        "(secondScore)",
        "%ro_leaderboard_{%ro_leaderboard_2_player%}_score%",
        "(thirdScore)",
        "%ro_leaderboard_{%ro_leaderboard_3_player%}_score%",
        "(playerPlace)",
        "%ro_leaderboard_place", // Get the leaderboard place of the player that requested the placeholder
        "%ro_leaderboard_{%player_name%}_place%", // Get the place of a specific player
        "(playerScore)",
        "%ro_leaderboard_score%",// Get the score of the player that requested the placeholder
        "%ro_leaderboard_{%player_name%}_score%", // Get the score of a specific player
    )
}