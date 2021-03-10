import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
	kotlin("multiplatform")
	kotlin("plugin.serialization") version "1.4.31"
	id("com.android.library")
	id("kotlin-android-extensions")
}

group = "com.uramnoil"
version = "1.0-SNAPSHOT"

kotlin {
	android()
	ios {
		binaries {
			framework {
				baseName = "shared"
			}
		}
	}
	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
				implementation("io.ktor:ktor-serialization:1.5.2")
				implementation("io.insert-koin:koin-core:3.0.1-beta-1")
				implementation("io.ktor:ktor-client-core:1.5.2")
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
				implementation("io.ktor:ktor-client-android:1.5.2")
			}
		}
		val androidMain by getting {
			dependencies {
				implementation("com.google.android.material:material:1.3.0")
				implementation("io.ktor:ktor-client-android:1.5.2")
			}
		}
		val androidTest by getting {
			dependencies {
				implementation(kotlin("test-junit"))
				implementation("junit:junit:4.13")
			}
		}
		val iosMain by getting
		val iosTest by getting
	}
}

android {
	compileSdkVersion(29)
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	defaultConfig {
		minSdkVersion(24)
		targetSdkVersion(29)
	}
}

val packForXcode by tasks.creating(Sync::class) {
	group = "build"
	val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
	val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
	val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
	val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
	inputs.property("mode", mode)
	dependsOn(framework.linkTask)
	val targetDir = File(buildDir, "xcode-frameworks")
	from({ framework.outputDirectory })
	into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)