import com.android.build.gradle.LibraryExtension
import com.ohnalmwo.ondosee.configureAndroidCompose
import com.ohnalmwo.ondosee.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin :Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")

                extensions.configure<LibraryExtension> {
                    configureAndroidCompose(this)
                }

                dependencies {
                    add("implementation", libs.findBundle("compose").get())
                }
            }
        }
    }
}