plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.muffar.remindtask.main"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(projects.common.ui)
    implementation(projects.common.resources)
    implementation(projects.common.utils)
    implementation(projects.common.theme)
    implementation(projects.common.navigation)
    implementation(projects.domain)
    implementation(projects.screens.tasks)
    implementation(projects.screens.notes)

    //compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))

    //desugar
    implementation(libs.multidex)
    coreLibraryDesugaring(libs.desugar.jdk)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}

kapt{
    correctErrorTypes = true
}