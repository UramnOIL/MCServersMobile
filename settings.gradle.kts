pluginManagement {
    val kotlinVersion: String by settings
    val ktorVersion: String by settings
    val kodeinVersion: String by settings

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("android") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        id("com.android.library")
    }
}
rootProject.name = "MCServersMobile"


include(":androidApp", ":shared")