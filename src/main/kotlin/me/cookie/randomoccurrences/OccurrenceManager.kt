package me.cookie.randomoccurrences

import me.cookie.randomoccurrences.occurrences.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

class OccurrenceManager(val plugin: RandomOccurrences) {

    companion object {
        val occurrences = mutableListOf<Occurrence>()
        val items = mutableMapOf<String, ItemReward>()
        val commands = mutableMapOf<String, CommandReward>()
    }

    init {
        compileRewards()
        registerOccurrences()
        startDowntime()
    }

    private val downTime: Long = plugin.config.getInt("down-time").toLong() * 60 /* to seconds */ * 20 /* to ticks */

    var currentOccurrence: Occurrence? = null

    fun pickOccurrence() {
        if (occurrences.isEmpty()) {
            plugin.logger.warning("No occurrences found!")
            return
        }
        val randomOccurrence = occurrences.random()

        if (!randomOccurrence.isEnabled) { // Pick another occurrence if the current one is disabled
            pickOccurrence()
            return
        }

        setOccurrence(randomOccurrence)
    }

    fun setOccurrence(occurrence: Occurrence) {
        currentOccurrence?.end()
        downtimeTask?.cancel()
        currentOccurrence = occurrence
        occurrence.start()
    }

    private var downtimeTask: BukkitRunnable? = null
    fun startDowntime() {
        downtimeTask = object: BukkitRunnable() {
            override fun run() {
                if (currentOccurrence == null && Bukkit.getOnlinePlayers().size >= plugin.config.getInt("minimum-players")) // only pick an occurrence if there is none active, and there's enough players online
                    pickOccurrence()
                else
                    startDowntime()
            }
        }
        if (plugin.isEnabled)
            downtimeTask?.runTaskLater(plugin, downTime)
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

    private fun registerOccurrences() {
        occurrences.add(CreeperSlayer(plugin, this))
        occurrences.add(DamageDealer(plugin, this))
        occurrences.add(FishingPros(plugin, this))
        occurrences.add(GoldMiner(plugin, this))
        occurrences.add(Jumper(plugin, this))
        occurrences.add(LeafCutter(plugin, this))
        occurrences.add(Lumberjack(plugin, this))
        occurrences.add(Masochist(plugin, this))
        occurrences.add(MasterBuilders(plugin, this))
        occurrences.add(StoneMiner(plugin, this))

        // broken :(
        /*registerOccurrence(CreeperSlayer::class.java)
        registerOccurrence(DamageDealer::class.java)
        registerOccurrence(FishingPros::class.java)
        registerOccurrence(GoldMiner::class.java)
        registerOccurrence(Jumper::class.java)
        registerOccurrence(LeafCutter::class.java)
        registerOccurrence(Lumberjack::class.java)
        registerOccurrence(Masochist::class.java)
        registerOccurrence(MasterBuilders::class.java)
        registerOccurrence(StoneMiner::class.java)*/
    }


    // Completely Broken
    /*fun registerOccurrence(occurrenceClass: Class<out Occurrence>) { // TODO: Make this more generic (rework this to allow adding custom occurrences via api or smthn)
        val occurrence = occurrenceClass.getDeclaredConstructor(JavaPlugin::class.java, this::class.java).newInstance(plugin, this)
        plugin.logger.info("Registered occurrence: ${occurrence.configName}")
        occurrences.add(occurrence)
    }*/

    private fun compileRewards() {
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
                val executor = commandSection.getString("as", "PLAYER")!!.uppercase()
                val ignorePerms = commandSection.getBoolean("ignore-permissions", false)
                commands[configReward] = CommandReward(command, Executor.valueOf(executor), ignorePerms)
            }

        }
    }
}