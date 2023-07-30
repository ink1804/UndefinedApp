plugins {
    id("com.android.library")
    id("android-library-base")
    id("undefinedapp.compose")
    kotlin("android")
}

android {
    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }
}
