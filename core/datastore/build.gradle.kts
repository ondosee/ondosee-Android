@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("ondosee.android.core")
    id("ondosee.android.hilt")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.ohnalmwo.datastore"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.androidx.dataStore.core)
    implementation(libs.protobuf.kotlin.lite)
}