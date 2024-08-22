@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("ondosee.android.core")
    id("ondosee.android.hilt")
}

android {
    namespace = "com.ohnalmwo.domain"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kotlinx.datetime)
    implementation(libs.okhttp3)
}