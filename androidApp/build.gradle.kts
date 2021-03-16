plugins {
    id("com.android.application")
    kotlin("android")
}

group = "com.uramnoil"
version = "1.0-SNAPSHOT"

dependencies {
    val kodeinVersion: String by project

    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.kodein.di:kodein-jvm:${kodeinVersion}")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.uramnoil.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures.viewBinding = true
}