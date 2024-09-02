plugins {
    id("ondosee.android.feature")
    id("ondosee.android.hilt")
}

android {
    namespace = "com.ohnalmwo.main"
}

dependencies {
    implementation(libs.swiperefresh)
    implementation(libs.accompanist.permission)
    implementation(libs.haze)
    implementation(libs.androidx.constraintlayout.compose)
}