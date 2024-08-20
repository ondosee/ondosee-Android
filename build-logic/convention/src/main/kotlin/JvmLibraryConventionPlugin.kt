import com.ohnalmwo.ondosee.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply("ondosee.android.lint")
                apply("com.google.devtools.ksp")
            }
            configureKotlinJvm()
        }
    }
}