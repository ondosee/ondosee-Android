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
}