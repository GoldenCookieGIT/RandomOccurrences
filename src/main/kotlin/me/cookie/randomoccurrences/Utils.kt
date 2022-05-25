package me.cookie.randomoccurrences

import net.md_5.bungee.api.ChatColor
import java.util.regex.Pattern

val hexPattern = Pattern.compile("#[a-fA-F0-9]{6}")

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