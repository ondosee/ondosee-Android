@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("ondosee.android.core")
    id("ondosee.android.compose")
}

android {
    namespace = "com.ohnalmwo.ui"
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:model"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.datetime)
    implementation(libs.accompanist.permission)
    implementation(libs.haze)
}