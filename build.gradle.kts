import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "me.cookie"
version = "1.6.2"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-20220607.160742-53")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.6.0") // Use kotlin
    compileOnly("me.clip:placeholderapi:2.11.2") // Use placeholderapi
    implementation("org.bstats:bstats-bukkit:3.0.0") // Shade bStats
}


bukkit {
    main = "me.cookie.randomoccurrences.RandomOccurrences"
    apiVersion = "1.13"
    libraries = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:1.7.20",
    )
    bukkit.version = version

    commands {
        register("forceoccurrence") {
            description = "Force an occurrence to happen"
            permission = "randomoccurrences.forceoccurrence"
            usage = "/forceoccurrence <occurrence>"
            aliases = listOf("fo")
        }
        register("roreloadmessages") {
            description = "Reloads the messages.yml file"
            permission = "randomoccurrences.reloadmessages"
            usage = "/roreloadmessages"
            aliases = listOf("rorm")
        }
    }

    permissions {
        register("randomoccurrences.*") {
            children = listOf("randomoccurrences.forceoccurrence", "randomoccurrences.reloadmessages")
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("randomoccurrences.forceoccurrence") {
            description = "Allows the player to force an occurrence to happen"
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("randomoccurrences.reloadmessages") {
            description = "Allows the player to reload the messages.yml file"
            default = BukkitPluginDescription.Permission.Default.OP
        }
    }

    softDepend = listOf("PlaceholderAPI")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<ShadowJar> {
    relocate("org.bstats", "me.cookie.bstats")
    relocate("org.reflections", "me.cookie.reflections")
    archiveClassifier.set("")
    destinationDirectory.set(File("server\\plugins"))
}