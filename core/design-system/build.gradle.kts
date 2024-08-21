@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("goms.android.core")
    id("goms.android.compose")
    id("goms.android.lint")
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
}
