import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "ondosee.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidHilt") {
            id = "ondosee.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidLint") {
            id = "ondosee.android.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }

        register("androidCore") {
            id = "ondosee.android.core"
            implementationClass = "AndroidCoreConventionPlugin"
        }

        register("androidCompose") {
            id = "ondosee.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("jvmLibrary") {
            id = "ondosee.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "ondosee.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidFirebase") {
            id = "ondosee.android.firebase"
            implementationClass = "AndroidFirebaseConventionPlugin"
        }
    }
}