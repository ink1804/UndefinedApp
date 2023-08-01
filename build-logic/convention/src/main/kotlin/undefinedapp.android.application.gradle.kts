plugins {
    id("com.android.application")
    id("android-library-base")
    kotlin("android")
    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        create("test") {
            keyAlias = "undefinedapp"
            keyPassword = "undefinedapp"
            storeFile = file("../undefinedapp.key.jks")
            storePassword = "undefinedapp"
        }
    }

    buildTypes {
        val debug by getting {
            signingConfig = signingConfigs.getByName("test")
        }
        val release by getting {
            signingConfig = signingConfigs.getByName("test")
        }
    }
}