import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
    id("co.touchlab.skie")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            export(libs.androidx.lifecycle.viewmodel)
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.datetime)
            implementation(libs.kermit)

            implementation(libs.kotlinx.coroutines.core)
            api(libs.androidx.lifecycle.viewmodel)
            implementation(libs.bundles.commonKtor)
            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.coroutines)
            implementation(libs.sqldelight.coroutines)

            api(libs.koinCore)
            api(libs.koinCompose)
            implementation(libs.koinAnnotations)
        }

        androidMain.dependencies {
            implementation(libs.android.sqldelight.driver)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)

            implementation("org.drewcarlson:client-core:1.0.0-SNAPSHOT")
            implementation("org.drewcarlson:network-client-ktor:1.0.0-SNAPSHOT")
        }

        iosMain.dependencies {
            implementation(libs.ios.sqldelight.driver)
            implementation(libs.ktor.client.ios)
        }

        commonTest.dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
            implementation(libs.bundles.commonTest)
        }
    }
}

skie {
    features {
        enableSwiftUIObservingPreview = true
    }
}

dependencies {
    add("kspCommonMainMetadata", "io.insert-koin:koin-ksp-compiler:1.3.0")
//    add("kspJvm", "io.insert-koin:koin-ksp-compiler:1.3.0")
}

dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, libs.mockativeProcessor)
        }
}

ksp {
    arg("mockative.stubsUnitByDefault", "true")
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

android {
    namespace = "com.amit.kmp.poc.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

sqldelight {
    databases {
        create("KmpPocDb") {
            packageName.set("com.amit.kmp.poc.shared")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))
            verifyMigrations.set(true)
        }
    }
    linkSqlite.set(true)
}

kover {
    filters {
        classes {
            excludes += listOf()
        }
    }
}
