import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "me.cookie"
version = "1.3.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/central")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-20220607.160742-53")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.6.0") // Use kotlin
    implementation("org.bstats:bstats-bukkit:3.0.0") // Shade bStats
}


bukkit {
    main = "me.cookie.randomoccurrences.RandomOccurrences"
    apiVersion = "1.13"
    libraries = listOf(
                    "org.jetbrains.kotlin:kotlin-stdlib:1.7.0",
                )
    bukkit.version = version
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
tasks.withType<ShadowJar> {
    relocate("org.bstats", "me.cookie.bstats")
    relocate("org.reflections", "me.cookie.reflections")
    archiveClassifier.set("")
    destinationDirectory.set(File("G:\\coding\\Test Servers\\RandomOccurrences\\plugins"))
}