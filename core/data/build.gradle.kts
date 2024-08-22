@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("ondosee.android.core")
    id("ondosee.android.hilt")
}

android {
    namespace = "com.ohnalmwo.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
    implementation(project(":core:domain"))

    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}