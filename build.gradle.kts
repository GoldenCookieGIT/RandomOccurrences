import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.cookie"
version = "1.1.1"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/central")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Shade Kotlin
    implementation("org.reflections:reflections:0.10.2") // Shade Reflections
    implementation("org.bstats:bstats-bukkit:3.0.0") // Shade bStats
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
tasks.withType<ShadowJar> {
    relocate("org.bstats", "me.cookie.bstats")
    archiveClassifier.set("")
    destinationDirectory.set(File("G:\\coding\\Test Servers\\RandomOccurrences\\plugins"))
}