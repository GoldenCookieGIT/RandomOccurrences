package me.cookie.randomoccurrences.occurrence

import me.cookie.randomoccurrences.util.isFull
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ItemReward(private val item: ItemStack): Reward {
    override fun reward(player: Player) {
        if (!player.inventory.isFull)
            player.inventory.addItem(item)
        else
            player.world.dropItem(player.location, item)
    }
}