import com.android.build.api.dsl.ApplicationExtension
import com.ohnalmwo.ondosee.configureKotlinAndroid
import com.ohnalmwo.ondosee.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.android.application")
                apply("ondosee.android.lint")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    applicationId = "com.ohnalmwo.ondosee"
                    minSdk = 26
                    targetSdk = 35
                    versionCode = 1
                    versionName = "1.0.0"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                    vectorDrawables.useSupportLibrary = true
                }

                buildFeatures.compose = true

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        isDebuggable = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }

                extensions.getByType<ComposeCompilerGradlePluginExtension>().apply {
                    enableStrongSkippingMode.set(true)
                    includeSourceInformation.set(true)
                }

                dependencies {
                    add("implementation", libs.findBundle("kotlinx-coroutines").get())
                    add("implementation", libs.findBundle("compose").get())
                }
            }
        }
    }
}
