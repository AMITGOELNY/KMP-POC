rootProject.name = "KMP-RCCL_POC"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

buildscript {
    repositories {
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
    dependencies {
        classpath("org.drewcarlson:gradle-plugin:1.0.0-SNAPSHOT")
    }
}

apply(plugin = "org.drewcarlson.kmp-test-bench-settings")

include(":composeApp")
include(":shared")