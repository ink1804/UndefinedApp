plugins {
    `kotlin-dsl`
}

group = "com.ink1804.dev.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlinGradle)
    implementation(libs.googleServicesGradle)
}