plugins {
    id("undefinedapp.android.application")
}

android {
    defaultConfig {
        applicationId = "com.ink1804.dev.undefinedapp"
        setProperty("archivesBaseName", rootProject.name + "_v." + versionName)
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "undefinedapp"
            keyPassword = "undefinedapp"
            storeFile = file("../undefinedapp.key.jks")
            storePassword = "undefinedapp"
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.COMPOSE_KOTLIN_COMPILER
    }
}

dependencies {
    implementation(project(":shared-common-network"))
    implementation(project(":common-network"))
    implementation(project(":common-ui"))
    implementation(project(":feature-test"))
    implementation(project(":feature-splash"))
    implementation(project(":feature-main"))
    implementation(project(":feature-map"))
    implementation(project(":feature-profile"))

    implementation(libs.composeActivityLib)
    implementation(libs.composeAccompanistLib)
}