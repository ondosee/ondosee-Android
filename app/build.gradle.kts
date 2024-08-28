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
    implementation(libs.junit)
    implementation(project(":core:design-system"))
    implementation(project(":core:model"))
    androidTestImplementation(libs.androidx.test.ext)
    implementation(libs.androidx.core.splashscreen)
}