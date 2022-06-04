package me.cookie.randomoccurrences

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.inventory.PlayerInventory
import org.bukkit.plugin.java.JavaPlugin
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.function.Consumer
import java.util.regex.Pattern

val hexPattern = Pattern.compile("#[a-fA-F0-9]{6}")

val logger = JavaPlugin.getPlugin(RandomOccurrences::class.java).logger

fun String.formatHexColors(): String {
    var tempMsg = this
    var matcher = hexPattern.matcher(tempMsg)
    while (matcher.find()) {
        val hex = tempMsg.substring(matcher.start(), matcher.end())
        tempMsg = tempMsg.replace(hex, ChatColor.of(hex).toString())
        matcher = hexPattern.matcher(tempMsg)
    }
    return ChatColor.translateAlternateColorCodes('&', tempMsg)
}
fun getVersion(plugin: JavaPlugin, resourceId: String, consumer: Consumer<String>) {
    Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
        try {
            URL("https://api.spigotmc.org/legacy/update.php?resource=$resourceId").openStream()
                .use { inputStream ->
                    Scanner(inputStream).use { scanner ->
                        if (scanner.hasNext()) {
                            consumer.accept(scanner.next())
                        }
                    }
                }
        } catch (exception: IOException) {
            plugin.logger.info("Unable to check for updates: " + exception.message)
            consumer.accept("0.0.0")
        }
    })
}

val PlayerInventory.isFull: Boolean
    get() {
        for (i in 0 until this.size) {
            if (this.getItem(i) == null || this.getItem(i)?.type == org.bukkit.Material.AIR) {
                return false
            }
        }
        return true
    }
