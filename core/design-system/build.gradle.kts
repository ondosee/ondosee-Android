@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("ondosee.android.core")
    id("ondosee.android.compose")
    id("ondosee.android.lint")
}

android {
    namespace = "com.ohnalmwo.design_system"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {}
}

dependencies {
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.immutable)
    implementation(libs.androidx.wear.compose)
    implementation(libs.lottie.compose)

    implementation(project(":core:model"))
}
