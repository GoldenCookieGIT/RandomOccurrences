package me.cookie.randomoccurrences

import org.bukkit.plugin.java.JavaPlugin

class RandomOccurrences: JavaPlugin() {
    override fun onEnable() {
        // Enable logic
        logger.info("enabled")
        return
    }

    override fun onDisable() {
        // Disable logic
        logger.info("disabled")
        return
    }
}