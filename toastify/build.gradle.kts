import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.u-ali-dev"
version = "0.0.1"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "toastify"
            isStatic = true
        }
    }



    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
    }
}

android {
    namespace = "io.github.u-ali-dev.toastify"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "toastify", version.toString())

    pom {
        name = "Toastify"
        description = "Toastify is a lightweight library for Jetpack Compose and Compose Multiplatform that allows you to display customizable toasts, messages, and alerts on the screen with ease. Perfect for building consistent and responsive UI notifications across platforms."
        inceptionYear = "2025"
        url = "https://github.com/u-ali-dev/toastify-compose-multiplatform/"
        licenses {
            license {
                name = "MIT"
                url = "https://mit-license.org/"
            }
        }
        developers {
            developer {
                id = "u-ali-dev"
                name = "Usman Ali"
                url = "https://github.com/u-ali-dev/"
            }
        }
        scm {
            url = "https://github.com/u-ali-dev/toastify-compose-multiplatform/"
        }
    }
}
