@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "com.muffar.remindtask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.muffar.remindtask"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(projects.common.ui)
    implementation(projects.common.resources)
    implementation(projects.common.utils)
    implementation(projects.common.theme)
    implementation(projects.domain)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

    //compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))

    //room
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)
    annotationProcessor(libs.room.compiler)

    //dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //calendar
    implementation(libs.calendar)

    //data store
    implementation(libs.datastore.preferences)

    //desugar
    coreLibraryDesugaring(libs.desugar.jdk)

    //lottie
    implementation(libs.lottie)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.test)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}