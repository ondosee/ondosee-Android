pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io" )
    }
}

rootProject.name = "ondosee"
include(":app")

include(":core")
include(":feature")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:network")
include(":core:model")
include(":core:datastore")
include(":core:ui")
include(":core:design-system")
