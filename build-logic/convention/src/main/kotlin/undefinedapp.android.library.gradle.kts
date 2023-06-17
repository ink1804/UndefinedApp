plugins {
    id("com.android.library")
    id("undefinedapp.android.base")
    kotlin("android")
}

android {
    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }
}
