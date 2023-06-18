plugins {
    id("undefinedapp.android.application")
}

android {
    defaultConfig {
        applicationId = "com.ink1804.dev.undefinedapp"
        setProperty("archivesBaseName", rootProject.name + "_v." + versionName)
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.COMPOSE_KOTLIN_COMPILER
    }
}

dependencies {
    implementation(project(":common-network"))
    implementation(project(":common-ui"))
    implementation(project(":feature-test"))

    implementation(libs.composeActivityLib)
}