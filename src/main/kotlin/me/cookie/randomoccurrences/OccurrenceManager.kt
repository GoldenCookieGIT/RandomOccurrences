package me.cookie.randomoccurrences

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.reflections.Reflections

class OccurrenceManager(val plugin: JavaPlugin) {

    companion object {
        val occurrences = mutableListOf<Occurrence>()
        val items = mutableMapOf<String, ItemStack>()
    }

    private val downTime: Long = plugin.config.getInt("down-time").toLong() * 60 /* to seconds */ * 20 /* to ticks */

    var currentOccurrence: Occurrence? = null

    fun pickOccurrence() {
        val randomOccurrence = occurrences.random()

        if(!randomOccurrence.isEnabled){ // Pick another occurrence if the current one is disabled
            pickOccurrence()
            return
        }

        randomOccurrence.start()
    }

    init {
        compileItems()
        registerOccurrences()
        startDowntime()
    }

    fun startDowntime(){
        object: BukkitRunnable() {
            override fun run() {
                if(currentOccurrence == null){ // only pick an occurrence if there is none active.
                    pickOccurrence()
                }
            }
        }.runTaskLater(plugin, downTime)
    }

    fun getRewards(configName: String): Map<Int, Array<ItemStack>> {
        val rewardMap = mutableMapOf<Int, Array<ItemStack>>()
        val configurationSection = plugin.config.getConfigurationSection("occurrences.$configName.rewards") ?: return mapOf()

        configurationSection.getKeys(false).forEach { place ->
            val rewards: Array<ItemStack> = configurationSection.getStringList(place).map {
                val item = items[it] ?: return@map null
                item.clone()
            }.filterNotNull().toTypedArray()
            rewardMap[place.toInt()] = rewards
        }
        return rewardMap
    }

    private fun registerOccurrences(){
        val reflections = Reflections("me.cookie.randomoccurrences.occurrences")
        val occurrenceClasses = reflections.getSubTypesOf(Occurrence::class.java)

        occurrenceClasses.forEach {
            registerOccurrence(it)
        }
    }

    fun registerOccurrence(occurrenceClass: Class<out Occurrence>){ // TODO: Make this more generic (rework this to allow adding custom occurrences via api or smthn)
        val occurrence = occurrenceClass.getDeclaredConstructor(JavaPlugin::class.java, this::class.java).newInstance(plugin, this@OccurrenceManager)
        plugin.logger.info("Registered occurrence: ${occurrence.configName}")
        occurrences.add(occurrence)
    }

    private fun compileItems(){
        val config = plugin.config
        val configItems = config.getConfigurationSection("items")
        if(configItems == null) {
            plugin.logger.warning("No items found in config!")
            return
        }
        configItems.getKeys(false).forEach { configItem ->
            val material = Material.valueOf(config.getString("items.$configItem.material", "DIRT")!!)
            val amount = config.getInt("items.$configItem.amount", 1)
            val itemName = ChatColor.translateAlternateColorCodes(
                '&',
                config.getString("items.$configItem.item-name", "&cUnknown")!!
            )
            val lore = config.getStringList("items.$configItem.lore").stream().map {
                ChatColor.translateAlternateColorCodes('&', it)
            }.toList()


            val item = ItemStack(material, amount)
            val meta = item.itemMeta!!
            meta.setDisplayName(itemName)

            config.getStringList("items.$configItem.enchantments").forEach enchantmentLoop@{ // add enchants to item
                val enchantment = Enchantment.getByName(it.split(":")[0]) ?: return@enchantmentLoop
                meta.addEnchant(
                    enchantment,
                    it.split(":")[1].toInt(),
                    true
                )
            }

            meta.lore = lore ?: listOf<String>()
            item.itemMeta = meta

            items[configItem] = item
        }
    }
}