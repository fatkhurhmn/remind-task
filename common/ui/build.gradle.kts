plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.muffar.remindtask.ui"
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
    }
}

dependencies {
    implementation(projects.common.resources)
    implementation(projects.common.utils)
    implementation(projects.common.theme)
    implementation(projects.domain)

    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))

    //calendar
    implementation(libs.calendar)

    //lottie
    implementation(libs.lottie)

    implementation (libs.multidex)
    coreLibraryDesugaring(libs.desugar.jdk)
}