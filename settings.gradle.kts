enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Fake_post_app"
include(":androidApp")
include(":shared")
include(":shared:core")
include(":shared:core:network")
include(":shared:domain")
