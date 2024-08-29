plugins {
    id("ondosee.android.application")
    id("ondosee.android.hilt")
    id("ondosee.android.firebase")
}

android {
    namespace = "com.ohnalmwo.ondosee"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
        }
    }
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))

    implementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.haze)
    implementation(libs.accompanist.systemuicontroller)
}