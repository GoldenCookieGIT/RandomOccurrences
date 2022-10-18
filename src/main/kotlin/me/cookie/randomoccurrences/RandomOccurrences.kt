package me.cookie.randomoccurrences

import me.cookie.randomoccurrences.commands.ForceOccurrence
import me.cookie.randomoccurrences.commands.ReloadMessages
import me.cookie.randomoccurrences.listeners.*
import org.bstats.bukkit.Metrics
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStreamReader

class RandomOccurrences: JavaPlugin() {
    lateinit var occurrenceManager: OccurrenceManager
    lateinit var messages: Messages

    override fun onEnable() {
        val pluginId = 15297
        Metrics(this, pluginId)

        messages = Messages(
            getResource("messages.yml")!!.let {
                YamlConfiguration.loadConfiguration(InputStreamReader(it))
            }
        )

        occurrenceManager = OccurrenceManager(this)

        val messagesFile = File(dataFolder, "messages.yml")

        if (!messagesFile.exists()) {
            saveResource("messages.yml", false)
        }

        if (config.getInt("minimum-players", -1) < 0) {
            logger.severe(" [!!!] The config has been changed! Please update the config file! (a backup of your old config has been made) [!!!] ")
            val file = File(dataFolder, "config.yml")
            file.renameTo(File(dataFolder, "config.old.yml"))
            saveDefaultConfig()
            reloadConfig()
        }

        saveDefaultConfig()
        loadMessages()
        registerCommands()

        server.pluginManager.registerEvents(BlockBreak(occurrenceManager), this)
        server.pluginManager.registerEvents(BlockPlace(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityDamage(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityDamageByEntity(occurrenceManager), this)
        server.pluginManager.registerEvents(EntityKill(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerJump(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerFish(occurrenceManager), this)
        server.pluginManager.registerEvents(PlayerMove(occurrenceManager), this)

        if (config.getString("update-checker", "OFF") != "OFF"){
            server.pluginManager.registerEvents(PlayerJoin(this), this)
        }
    }

    override fun onDisable() {
        // Disable logic
        if (occurrenceManager.currentOccurrence != null) {
            occurrenceManager.currentOccurrence!!.end()
        }
        return
    }

    private fun registerCommands() {
        getCommand("forceoccurrence")?.setExecutor(ForceOccurrence(occurrenceManager))
        getCommand("forceoccurrence")?.tabCompleter = ForceOccurrence(occurrenceManager)
        getCommand("roreloadmessages")?.setExecutor(ReloadMessages(this))
    }

    fun loadMessages() {
        val messageYml = YamlConfiguration.loadConfiguration(File(dataFolder, "messages.yml"))

        messageYml.getConfigurationSection("")!!.getKeys(false).forEach { key ->
            if (messageYml.isList(key)) {
                messages.map[key] = messageYml.getStringList(key).joinToString("\n")
            } else {
                messages.map[key] = messageYml.getString(key) ?: return@forEach
            }
        }

        OccurrenceManager.occurrences.forEach {
            it.friendlyName = messages.map[it.configName] ?: it.friendlyName
            it.description = messages.map[it.configName + "-description"]?.split("\n") ?: it.description
        }
    }
}