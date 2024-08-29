@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("ondosee.jvm.library")
    id("kotlinx-serialization")
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.retrofit.kotlin.serialization)
}