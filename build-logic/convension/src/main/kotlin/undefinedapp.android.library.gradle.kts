import gradle.kotlin.dsl.accessors._84a1b18aedf59756dc9c37decf885dc8.android
import gradle.kotlin.dsl.accessors._84a1b18aedf59756dc9c37decf885dc8.kotlinOptions

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
