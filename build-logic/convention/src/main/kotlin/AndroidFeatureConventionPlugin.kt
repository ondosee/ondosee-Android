import com.ohnalmwo.ondosee.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("ondosee.android.core")
                apply("ondosee.android.hilt")
                apply("ondosee.android.compose")
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:datastore"))
                add("implementation", project(":core:design-system"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:ui"))

                add("implementation", libs.findLibrary("coil.kt").get())

                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("kotlinx.datetime").get())
                add("implementation", libs.findLibrary("kotlinx.immutable").get())
            }
        }
    }
}