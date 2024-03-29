plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.muffar.remindtask.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.common.theme)

    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation (libs.multidex)
    coreLibraryDesugaring(libs.desugar.jdk)
}

kapt {
    correctErrorTypes = true
}