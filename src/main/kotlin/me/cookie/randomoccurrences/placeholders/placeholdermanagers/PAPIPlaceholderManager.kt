package me.cookie.randomoccurrences.placeholders.placeholdermanagers

import me.clip.placeholderapi.PlaceholderAPI
import me.cookie.randomoccurrences.RandomOccurrences
import me.cookie.randomoccurrences.placeholders.PAPIPlaceholders
import me.cookie.randomoccurrences.placeholders.PlaceholderManager
import me.cookie.randomoccurrences.util.logger
import org.bukkit.entity.Player

class PAPIPlaceholderManager(private val plugin: RandomOccurrences): PlaceholderManager() {

    init {
        logger.info("Hooked into PlaceholderAPI")
        logger.warning("The use of \"()\" placeholders is NOT supported with placeholderapi," +
                " please use the new %% placeholders as they're compatible with and without placeholderapi")
        registerPlaceholders()
    }

    override fun setPlaceholders(player: Player?, message: String): String {
        return PlaceholderAPI.setPlaceholders(player, message)
    }

    override fun containsPlaceholders(message: String): Boolean {
        return PlaceholderAPI.containsPlaceholders(message)
    }

    private fun registerPlaceholders() {
        PAPIPlaceholders(plugin).register()
    }

}