plugins {
    id("undefinedapp.android.application")
}

android {
    defaultConfig {
        applicationId = "com.ink1804.dev.undefinedapp"
        setProperty("archivesBaseName", rootProject.name + "_v." + versionName)
    }
}

dependencies {
    implementation(project(":common-network"))
    implementation(project(":common-ui"))
    implementation(project(":feature-test"))

    implementation(libs.composeActivityLib)
}