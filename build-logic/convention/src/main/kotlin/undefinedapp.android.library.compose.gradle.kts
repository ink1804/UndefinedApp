plugins {
    id("com.android.library")
    id("undefinedapp.android.base")
    id("undefinedapp.compose")
    kotlin("android")
}

android {

    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.COMPOSE_KOTLIN_COMPILER
    }
}
