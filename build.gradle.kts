@file:Suppress("DSL_SCOPE_VIOLATION")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.gms.google.service) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose) apply false
}

allprojects {
    tasks.withType(KotlinCompilationTask::class.java).configureEach {
        compilerOptions {
            if (project.findProperty("enableMultiModuleComposeReports") == "true") {
                freeCompilerArgs.addAll(
                    listOf(
                        "-P", "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${rootProject.layout.buildDirectory}/compose_metrics/",
                        "-P", "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${rootProject.layout.buildDirectory}/compose_metrics/"
                    )
                )
            }
        }
    }
}