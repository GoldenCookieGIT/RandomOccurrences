package me.cookie.randomoccurrences

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

private val HANDLERS_LIST = HandlerList()
class PlayerJumpEvent(val player: Player): Event() {

    override fun getHandlers(): HandlerList {
        return HANDLERS_LIST
    }
    companion object {
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS_LIST
        }
    }
}