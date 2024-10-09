plugins {
    id("ondosee.android.feature")
    id("ondosee.android.hilt")
}

android {
    namespace = "com.ohnalmwo.weekly_weather"
}

dependencies {
    implementation(libs.haze)
}