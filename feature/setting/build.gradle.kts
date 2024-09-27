plugins {
    id("ondosee.android.feature")
    id("ondosee.android.hilt")
}

android {
    namespace = "com.ohnalmwo.setting"
}

dependencies {
    implementation(libs.haze)
}