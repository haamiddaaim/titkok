import org.jetbrains.kotlin.gradle.utils.toSetOrEmpty

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")



}

android {
    namespace = "com.example.tiktok"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.example.tiktok"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        /** you have to up the number everytime you add a update of your app to google play **/

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.google.services)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.glide)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("com.firebaseui:firebase-ui-firestore:8.0.2")
    implementation ("com.firebaseui:firebase-ui-storage:8.0.2")
    implementation("com.google.android.gms:play-services-auth:21.1.1")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation ("com.facebook.android:facebook-android-sdk:latest.release")
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}