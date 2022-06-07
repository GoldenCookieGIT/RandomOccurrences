package me.cookie.randomoccurrences

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.reflections.Reflections

class OccurrenceManager(val plugin: JavaPlugin) {

    companion object {
        val occurrences = mutableListOf<Occurrence>()
        val items = mutableMapOf<String, ItemReward>()
        val commands = mutableMapOf<String, CommandReward>()
    }

    private val downTime: Long = plugin.config.getInt("down-time").toLong() * 60 /* to seconds */ * 20 /* to ticks */

    var currentOccurrence: Occurrence? = null

    fun pickOccurrence() {
        if (occurrences.isEmpty()) {
            plugin.logger.warning("No occurrences found!")
            return
        }
        val randomOccurrence = occurrences.random()

        if (!randomOccurrence.isEnabled){ // Pick another occurrence if the current one is disabled
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
                if (currentOccurrence == null && Bukkit.getOnlinePlayers().size >= plugin.config.getInt("minimum-players")) // only pick an occurrence if there is none active, and there's enough players online)
                    pickOccurrence()
                else
                    startDowntime()

            }
        }.runTaskLater(plugin, downTime)
    }

    fun getRewards(configName: String): Map<Int, Array<Reward>> {
        val rewardMap = mutableMapOf<Int, Array<Reward>>()
        val configurationSection = plugin.config.getConfigurationSection("occurrences.$configName.rewards") ?: return mapOf()

        configurationSection.getKeys(false).forEach { place ->
            val rewards: Array<Reward> = configurationSection.getStringList(place).map {
                val item = items[it]
                val command = commands[it]
                Reward(item, command)
            }.toTypedArray()
            rewardMap[place.toInt()] = rewards.clone().toMutableList().apply {
                addAll(rewardMap[place.toInt()] ?: arrayOf())
            }.toTypedArray()
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
        val configRewards = config.getConfigurationSection("rewards")
        if (configRewards == null) {
            plugin.logger.warning("No items found in config!")
            return
        }
        configRewards.getKeys(false).forEach { configReward ->
            val configSection = configRewards.getConfigurationSection(configReward)!!

            if (configSection.contains("item", true)) {
                val itemSection = configSection.getConfigurationSection("item")!!
                plugin.logger.warning("No items found in config for reward: $configReward")
                val material = Material.valueOf(itemSection.getString("material", "AIR")!!)
                val amount = itemSection.getInt("amount", 1)
                val itemName = itemSection.getString("item-name", "#ff0000Unknown")!!.formatHexColors()
                val lore = itemSection.getStringList("lore").stream().map {
                    it.formatHexColors()
                }.toList()

                val item = ItemStack(material, amount)
                val meta = item.itemMeta!!
                meta.setDisplayName(itemName)

                configSection.getStringList("item.enchantments").forEach enchantmentLoop@{ // add enchants to item
                    val enchantment = Enchantment.getByName(it.split(":")[0]) ?: return@enchantmentLoop
                    meta.addEnchant(
                        enchantment,
                        it.split(":")[1].toInt(),
                        true
                    )
                }

                meta.lore = lore ?: listOf<String>()
                item.itemMeta = meta

                items[configReward] = ItemReward(item)
            }

            if (configSection.contains("command", true)) {
                val commandSection = configSection.getConfigurationSection("command")!!
                val command = commandSection.getString("run", "/help")!!.apply {
                    if (this[0] != '/')
                        this.padStart(1, '/')
                }
                val executor = commandSection.getString("executor", "PLAYER")!!.uppercase()
                val ignorePerms = commandSection.getBoolean("ignore-permissions", false)
                commands[configReward] = CommandReward(command, Executor.valueOf(executor), ignorePerms)
            }

        }
    }
}