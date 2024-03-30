plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.muffar.remindtask.service"
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
    implementation(projects.common.resources)
    implementation(projects.domain)

    implementation(libs.core.ktx)

    //dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //desugar
    implementation(libs.multidex)
    coreLibraryDesugaring(libs.desugar.jdk)
}

kapt{
    correctErrorTypes = true
}