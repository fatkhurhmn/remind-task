plugins {
    alias(libs.plugins.androidLibrary)
}

android {
    namespace = "com.muffar.remindtask.resources"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
}