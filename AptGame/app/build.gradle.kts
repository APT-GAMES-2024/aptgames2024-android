plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.onemonth.aptgame"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.onemonth.aptgame"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //view
    implementation(libs.recycler)

    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.remote)
    implementation (libs.firebase.dynamic.links.ktx)


    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //lifecycle
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    //retrofit2
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.scalars)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //timber
    implementation(libs.timber)

    //glide
    implementation(libs.glide)

    //google
    implementation (libs.generativeai)

}

kapt {
    correctErrorTypes = true
}