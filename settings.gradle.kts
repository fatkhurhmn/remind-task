enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
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
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
}

rootProject.name = "RemindTask"
include(":app")
include(":common:ui")
include(":common:resources")
include(":common:utils")
include(":common:theme")
include(":domain")
include(":data")
include(":screens:tasks")
include(":screens:notes")
include(":screens:main")
include(":service")
include(":common:navigation")
