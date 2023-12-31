plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.serialization)
            implementation(libs.decompose.core)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines)

            implementation(project(":shared:core"))
            implementation(project(":shared:core:network"))
            implementation(project(":shared:domain"))
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "by.wolfcup.fakepostapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 29
    }
}
