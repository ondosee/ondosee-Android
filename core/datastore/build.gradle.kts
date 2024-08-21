@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("goms.android.core")
    id("goms.android.hilt")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.ohnalmwo.datastore"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.androidx.dataStore.core)
    implementation(libs.protobuf.kotlin.lite)
}