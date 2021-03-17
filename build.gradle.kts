buildscript {
    val kotlin_version by extra("1.4.31")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        val kotlinVersion: String by project

        classpath("com.android.tools.build:gradle:4.2.0-beta06")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

group = "com.uramnoil"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}