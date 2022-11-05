package me.cookie.randomoccurrences.placeholders

import org.bukkit.entity.Player

abstract class PlaceholderManager {

    fun replacePlaceholders(message: String): String {
        return replacePlaceholders(null, message)
    }

    fun replacePlaceholders(player: Player?, message: String): String {
        return if (containsPlaceholders(message)) {
            replacePlaceholders(player, setPlaceholders(player, message))
        } else {
            message
        }
    }

    protected abstract fun setPlaceholders(player: Player?, message: String): String
    protected abstract fun containsPlaceholders(message: String): Boolean
}