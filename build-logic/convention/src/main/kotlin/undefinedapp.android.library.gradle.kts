plugins {
    id("com.android.library")
    id("android-library-base")
    kotlin("android")
}

android {
    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }
}
